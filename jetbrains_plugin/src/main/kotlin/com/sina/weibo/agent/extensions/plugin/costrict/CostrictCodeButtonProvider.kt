// SPDX-FileCopyrightText: 2025 Weibo, Inc.
//
// SPDX-License-Identifier: Apache-2.0

package com.sina.weibo.agent.extensions.plugin.costrict

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.intellij.icons.AllIcons
import com.sina.weibo.agent.actions.*
import com.sina.weibo.agent.extensions.ui.buttons.ExtensionButtonProvider
import com.sina.weibo.agent.extensions.ui.buttons.ButtonType
import com.sina.weibo.agent.extensions.ui.buttons.ButtonConfiguration

/**
 * Costrict extension button provider.
 * Provides button configuration specific to Costrict extension.
 */
class CostrictCodeButtonProvider : ExtensionButtonProvider {
    
    override fun getExtensionId(): String = "costrict"
    
    override fun getDisplayName(): String = "Costrict"
    
    override fun getDescription(): String = "AI-powered code assistant with advanced capabilities"
    
    override fun isAvailable(project: Project): Boolean {
        // Check if costrict extension is available
        // This could include checking for extension files, dependencies, etc.
        return true
    }
    
    override fun getButtons(project: Project): List<AnAction> {
        // Note: project parameter kept for future extensibility
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
        return CostrictCodeButtonConfiguration()
    }
    
    /**
     * Costrict button configuration - shows all buttons (full-featured).
     */
    private class CostrictCodeButtonConfiguration : ButtonConfiguration {
        override fun isButtonVisible(buttonType: ButtonType): Boolean {
            return true // All buttons are visible for Costrict
        }
        
        override fun getVisibleButtons(): List<ButtonType> {
            return ButtonType.values().toList()
        }
    }

    /**
     * Action that handles clicks on the Plus button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class PlusButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(PlusButtonClickAction::class.java)
        private val commandId: String = "zgsm.plusButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Add
            templatePresentation.text = "New Task"
            templatePresentation.description = "New task"
        }

        /**
         * Performs the action when the Plus button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Plus button clicked")
            executeCommand(commandId,e.project)
        }
    }

    /**
     * Action that handles clicks on the Prompts button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class PromptsButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(PromptsButtonClickAction::class.java)
        private val commandId: String = "zgsm.promptsButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Information
            templatePresentation.text = "Prompt"
            templatePresentation.description = "Prompts"
        }

        /**
         * Performs the action when the Prompts button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Prompts button clicked")
            executeCommand(commandId, e.project)
        }
    }

    /**
     * Action that handles clicks on the MCP button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class MCPButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(MCPButtonClickAction::class.java)
        private val commandId: String = "zgsm.mcpButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Webreferences.Server
            templatePresentation.text = "MCP Server"
            templatePresentation.description = "MCP server"
        }

        /**
         * Performs the action when the MCP button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("MCP button clicked")
            executeCommand(commandId, e.project)
        }
    }

    /**
     * Action that handles clicks on the History button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class HistoryButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(HistoryButtonClickAction::class.java)
        private val commandId: String = "zgsm.historyButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Vcs.History
            templatePresentation.text = "History"
            templatePresentation.description = "History"
        }

        /**
         * Performs the action when the History button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("History button clicked")
            executeCommand(commandId, e.project)
        }
    }

    /**
     * Action that handles clicks on the Settings button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class SettingsButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(SettingsButtonClickAction::class.java)
        private val commandId: String = "zgsm.settingsButtonClicked"

        init {
            templatePresentation.icon = AllIcons.General.Settings
            templatePresentation.text = "Settings"
            templatePresentation.description = "Setting"
        }

        /**
         * Performs the action when the Settings button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Settings button clicked")
            executeCommand(commandId, e.project)
        }
    }

    /**
     * Action that handles clicks on the Marketplace button in the UI.
     * Executes the corresponding VSCode command when triggered.
     */
    class MarketplaceButtonClickAction : AnAction() {
        private val logger: Logger = Logger.getInstance(MarketplaceButtonClickAction::class.java)
        private val commandId: String = "zgsm.marketplaceButtonClicked"

        init {
            templatePresentation.icon = AllIcons.Actions.Install
            templatePresentation.text = "MCP Marketplace"
            templatePresentation.description = "Marketplace"
        }

        /**
         * Performs the action when the Marketplace button is clicked.
         *
         * @param e The action event containing context information
         */
        override fun actionPerformed(e: AnActionEvent) {
            logger.info("Marketplace button clicked")
            executeCommand(commandId, e.project)
        }
    }
}