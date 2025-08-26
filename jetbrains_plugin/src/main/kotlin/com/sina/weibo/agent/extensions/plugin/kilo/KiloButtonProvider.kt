// SPDX-FileCopyrightText: 2025 Weibo, Inc.
//
// SPDX-License-Identifier: Apache-2.0

package com.sina.weibo.agent.extensions.plugin.kilo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.icons.AllIcons
import com.sina.weibo.agent.actions.executeCommand
import com.sina.weibo.agent.extensions.ui.buttons.ExtensionButtonProvider
import com.sina.weibo.agent.extensions.ui.buttons.ButtonType
import com.sina.weibo.agent.extensions.ui.buttons.ButtonConfiguration

/**
 * Kilo Code extension button provider.
 * Provides button configuration specific to Kilo Code extension.
 */
class KiloButtonProvider : ExtensionButtonProvider {

    override fun getExtensionId(): String = "kilo-code"

    override fun getDisplayName(): String = "Kilo Code"

    override fun getDescription(): String = "Powered by 1000's. Built for you."

    override fun isAvailable(project: Project): Boolean {
        // Kilo button provider availability check (can be refined later)
        return true
    }

    override fun getButtons(project: Project): List<AnAction> {
        return listOf(
            PlusButtonClickAction(),
            PromptsButtonClickAction(),
            MCPButtonClickAction(),
            HistoryButtonClickAction(),
            MarketplaceButtonClickAction(),
            SettingsButtonClickAction()
        )
    }

    override fun getButtonConfiguration(): ButtonConfiguration {
        return KiloButtonConfiguration()
    }

    /**
     * Kilo Code button configuration - show all buttons for parity with Roo.
     */
    private class KiloButtonConfiguration : ButtonConfiguration {
        override fun isButtonVisible(buttonType: ButtonType): Boolean = true
        override fun getVisibleButtons(): List<ButtonType> = ButtonType.values().toList()
    }

    /** Plus button action: kilo.plusButtonClicked */
    class PlusButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(PlusButtonClickAction::class.java)
        private val commandId: String = "kilo.plusButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Add
            templatePresentation.text = "New Task"
            templatePresentation.description = "New task"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo Plus button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }

    /** Prompts button action: kilo.promptsButtonClicked */
    class PromptsButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(PromptsButtonClickAction::class.java)
        private val commandId: String = "kilo.promptsButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Information
            templatePresentation.text = "Prompt"
            templatePresentation.description = "Prompts"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo Prompts button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }

    /** MCP button action: kilo.mcpButtonClicked */
    class MCPButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(MCPButtonClickAction::class.java)
        private val commandId: String = "kilo.mcpButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Webreferences.Server
            templatePresentation.text = "MCP"
            templatePresentation.description = "MCP"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo MCP button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }

    /** History button action: kilo.historyButtonClicked */
    class HistoryButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(HistoryButtonClickAction::class.java)
        private val commandId: String = "kilo.historyButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Vcs.History
            templatePresentation.text = "History"
            templatePresentation.description = "History"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo History button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }

    /** Marketplace button action: kilo.marketplaceButtonClicked */
    class MarketplaceButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(MarketplaceButtonClickAction::class.java)
        private val commandId: String = "kilo.marketplaceButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Nodes.Plugin
            templatePresentation.text = "Market"
            templatePresentation.description = "Marketplace"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo Marketplace button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }

    /** Settings button action: kilo.settingsButtonClicked */
    class SettingsButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(SettingsButtonClickAction::class.java)
        private val commandId: String = "kilo.settingsButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Settings
            templatePresentation.text = "Settings"
            templatePresentation.description = "Setting"
        }

        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Kilo Settings button clicked")
            executeCommand(commandId, e.project, hasArgs = false)
        }
    }
}
