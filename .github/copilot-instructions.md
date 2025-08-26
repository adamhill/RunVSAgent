# RunVSAgent Development Instructions

**ALWAYS follow these instructions first and fallback to additional search and context gathering only if the information here is incomplete or found to be in error.**

RunVSAgent is a cross-platform development tool that enables VSCode-based coding agents to run within JetBrains IDEs. It consists of two main components: a Node.js Extension Host providing VSCode API compatibility, and a Kotlin-based JetBrains IDE plugin.

## Working Effectively

### System Requirements
**Validate these requirements FIRST before any development work:**
- Node.js 18.0+ (tested with 20.19.4)
- Java/JDK 17+ (tested with 17.0.16)  
- Git (any recent version)
- **Internet access** for downloading JetBrains dependencies (critical limitation in restricted environments)

### Initial Setup Commands
**Run these commands EXACTLY in this order for a fresh clone:**

```bash
# 1. Make scripts executable
chmod +x scripts/*.sh

# 2. Validate system requirements first
node --version  # Should be 18.0+
java -version   # Should be 17+
git --version

# 3. Initialize development environment
./scripts/setup.sh --verbose
# TIMEOUT: Set 30+ minutes. NEVER CANCEL this step.
# KNOWN ISSUE: May fail on Playwright browser download in restricted environments.
# If this fails, continue with manual dependency installation below.
```

### Alternative Setup (When Network Restricted)
**Use this approach when setup.sh fails due to network limitations:**

```bash
# 1. Initialize git submodules manually
git submodule init
git submodule update --recursive
# Takes ~2-5 minutes depending on network

# 2. Install extension host dependencies (ignore browser download failures)
cd extension_host
npm install --ignore-scripts
# Takes ~8 seconds. IGNORE Playwright/browser download errors.

# 3. Create minimal plugin structure for builds
cd ../jetbrains_plugin
mkdir -p plugins/roo-code/extension
echo "node_modules" > prodDep.txt
```

## Build Process

### Build Commands and Timeouts

**CRITICAL: NEVER CANCEL builds or long-running commands. Set appropriate timeouts.**

```bash
# Full build (when network available)
./scripts/build.sh --verbose
# TIMEOUT: Set 60+ minutes. NEVER CANCEL.
# Expected time: 15-45 minutes depending on dependencies.

# Individual component builds
./scripts/build.sh base         # Extension host only
./scripts/build.sh idea         # JetBrains plugin only  
./scripts/build.sh --skip-tests # Skip test execution

# Check build status
./scripts/build.sh --dry-run    # See what would be built
```

### Build Modes

```bash
# Debug build (development)
./scripts/build.sh --mode debug
# Release build (production) 
./scripts/build.sh --mode release
```

## Testing

### Test Commands and Expected Times

```bash
# Run all tests 
./scripts/test.sh
# TIMEOUT: Set 30+ minutes. NEVER CANCEL.
# Expected time: 5-15 minutes.

# Run specific test types
./scripts/test.sh env           # Environment validation (~30 seconds)
./scripts/test.sh unit          # Unit tests (~5 minutes)  
./scripts/test.sh integration   # Integration tests (~10 minutes)
./scripts/test.sh lint          # Code quality checks (~2 minutes)
./scripts/test.sh build         # Build validation (~5 minutes)

# Test with coverage
./scripts/test.sh --coverage
# TIMEOUT: Set 45+ minutes. NEVER CANCEL.
```

## Critical Build Limitations and Known Issues

### Network Dependency Issues
- **JetBrains Plugin Build**: REQUIRES internet access to download IntelliJ Platform dependencies from `cache-redirector.jetbrains.com`
- **Playwright Downloads**: May fail in restricted environments causing setup to fail
- **Workaround**: Use `npm install --ignore-scripts` and skip problematic dependencies

### Extension Host Dependencies  
- **VSCode Submodule**: Extension host build depends on VSCode source compilation
- **Build fails with**: "Cannot find module '../vscode/vs/workbench/api/node/extensionHostProcess.js'"
- **Root cause**: VSCode submodule needs to be built first before extension host can compile
- **Current status**: Extension host build does NOT work out of the box

### File Structure Requirements
- JetBrains plugin expects `plugins/roo-code/extension/` directory structure
- Missing `prodDep.txt` file causes build failures
- Create these manually if setup fails

## Manual Validation Scenarios

**ALWAYS perform these manual validation steps after making changes:**

### 1. Basic Environment Validation
```bash
# Verify system requirements
node --version && java -version && git --version
# Should complete in <1 second each

# Check project structure
ls -la extension_host/package.json jetbrains_plugin/build.gradle.kts
# Both files must exist
```

### 2. Dependencies Check
```bash
# Verify submodules are initialized  
ls -la deps/vscode/package.json deps/roo-code/package.json
# Both should exist and be non-empty

# Check extension host dependencies
cd extension_host && npm list --depth=0
# Should show installed packages without major errors
```

### 3. TypeScript Compilation Check
```bash
cd extension_host
npx tsc --noEmit --skipLibCheck
# EXPECTED: Will fail with VSCode dependency errors - this is normal
# Time: ~2 seconds
```

### 4. JetBrains Plugin Minimal Build
```bash
cd jetbrains_plugin
./gradlew build --no-daemon
# EXPECTED: May fail with network access errors - this is normal in restricted environments  
# Time: ~7 seconds if it fails on network, 45+ minutes if it succeeds
```

## Common Commands Reference

### Quick Validation Commands
```bash
# Check if build environment is ready (run these FIRST)
./scripts/test.sh env --verbose
# Time: ~30 seconds

# Validate project structure without building
find . -name "package.json" -o -name "build.gradle.kts" | head -5
# Should show both extension_host/package.json and jetbrains_plugin/build.gradle.kts
```

### Cleanup Commands
```bash
# Clean all build artifacts
./scripts/clean.sh all

# Clean specific components
./scripts/clean.sh extension_host
./scripts/clean.sh jetbrains_plugin
```

## Development Workflow

### Before Making Changes
1. **ALWAYS run environment validation**: `./scripts/test.sh env`
2. **Verify current build state**: `./scripts/build.sh --dry-run`
3. **Check for uncommitted changes**: `git status`

### After Making Changes  
1. **Run linting and formatting**: `./scripts/test.sh lint`
2. **Attempt build validation**: `./scripts/test.sh build`
3. **Manual functionality testing**: See validation scenarios above
4. **ALWAYS run**: `git diff` to review changes

### Pre-commit Checklist
- [ ] `./scripts/test.sh lint` passes
- [ ] Basic environment validation passes  
- [ ] Manual validation scenarios completed
- [ ] Git status reviewed

## Project Structure and Key Locations

### Important Directories
- `extension_host/` - Node.js/TypeScript VSCode API compatibility layer
- `jetbrains_plugin/` - Kotlin JetBrains IDE plugin source
- `deps/` - Git submodules (vscode, roo-code)
- `scripts/` - Build, test, and maintenance scripts
- `docs/` - Documentation and architecture diagrams

### Key Files to Monitor
- `extension_host/package.json` - Node.js dependencies and scripts
- `jetbrains_plugin/build.gradle.kts` - Plugin build configuration  
- `jetbrains_plugin/gradle.properties` - Platform versions and settings
- `BUILD.md` - Detailed build documentation
- `CONTRIBUTING.md` - Development guidelines

### Configuration Files
- `extension_host/tsconfig.json` - TypeScript compilation settings
- `jetbrains_plugin/detekt.yml` - Kotlin code quality rules  
- `.gitmodules` - Submodule configuration
- `.gitignore` - Version control exclusions

## Performance Expectations

### Command Timing Guidelines
- **Basic commands** (node --version, git status): <1 second
- **Environment validation**: ~30 seconds  
- **NPM install**: ~8 seconds with --ignore-scripts
- **TypeScript compilation check**: ~2 seconds (will fail, expected)
- **Gradle configuration**: ~7 seconds (before network failure)
- **Full build (if successful)**: 15-45 minutes
- **Full test suite**: 5-15 minutes

### Timeout Recommendations
- **Setup scripts**: 30+ minutes
- **Build commands**: 60+ minutes  
- **Test commands**: 30+ minutes
- **Individual validation**: 5+ minutes

**NEVER CANCEL long-running operations. Builds may legitimately take 45+ minutes.**

## Troubleshooting Quick Reference

### "Command not found" errors
```bash
chmod +x scripts/*.sh
echo $PATH
```

### "missing plugin dir" error
```bash
mkdir -p jetbrains_plugin/plugins/roo-code/extension
echo "node_modules" > jetbrains_plugin/prodDep.txt
```

### "Cannot find module '../vscode/vs/..." errors  
This is EXPECTED. Extension host build requires VSCode to be compiled first.

### Network access errors for JetBrains dependencies
This is EXPECTED in restricted environments. Document as limitation.

### Playwright browser download failures
Use `npm install --ignore-scripts` to bypass browser downloads.

---

**Remember: Follow these instructions exactly. Validate each step. Set appropriate timeouts. NEVER CANCEL long-running builds.**