package com.sina.weibo.agent.extensions.common

/**
 * Extension type enum for Roo Code
 * Defines different types of extensions that can be supported
 */
enum class ExtensionType(val code: String, val displayName: String, val description: String) {
    ROO_CODE("roo-code", "Roo Code", "AI-powered code assistant"),
    CLINE("cline", "Cline AI", "Open Source AI coding assistant for planning, building, and fixing code."),
    KILO_CODE("kilo-code", "Kilo Code", "AI coding assistant for efficient development");
    companion object {
        /**
         * Get extension type by code
         * @param code Extension code
         * @return Extension type or null if not found
         */
        fun fromCode(code: String): ExtensionType? {
            return values().find { it.code == code }
        }

        /**
         * Get default extension type
         * @return Default extension type
         */
        fun getDefault(): ExtensionType {
            return ROO_CODE
        }

        /**
         * Get all supported extension types
         * @return List of all extension types
         */
        fun getAllTypes(): List<ExtensionType> {
            return values().toList()
        }
    }
}