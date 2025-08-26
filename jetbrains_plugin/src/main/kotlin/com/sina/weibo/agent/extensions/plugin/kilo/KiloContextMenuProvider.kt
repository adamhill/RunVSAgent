// SPDX-FileCopyrightText: 2025 Weibo, Inc.
//
// SPDX-License-Identifier: Apache-2.0

package com.sina.weibo.agent.extensions.plugin.kilo

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.project.Project
import com.sina.weibo.agent.extensions.ui.contextmenu.ExtensionContextMenuProvider
import com.sina.weibo.agent.extensions.ui.contextmenu.ContextMenuConfiguration
import com.sina.weibo.agent.extensions.ui.contextmenu.ContextMenuActionType
import com.sina.weibo.agent.webview.WebViewManager
import com.sina.weibo.agent.extensions.plugin.kilo.KiloSupportPrompt

/**
 * Kilo Code extension context menu provider.
 * Provides context menu actions specific to Kilo Code extension.
 */
class KiloContextMenuProvider : ExtensionContextMenuProvider {

    override fun getExtensionId(): String = "kilo-code"

    override fun getDisplayName(): String = "Kilo Code"

    override fun getDescription(): String = "Context menu actions for Kilo Code"

    override fun isAvailable(project: Project): Boolean {
        // Availability can be refined if needed (e.g., check extension presence)
        return true
    }

    override fun getContextMenuActions(project: Project): List<AnAction> {
        return listOf(
            ExplainCodeAction(),
            FixCodeAction(),
            FixLogicAction(),
            ImproveCodeAction(),
            AddToContextAction(),
            NewTaskAction(),
        )
    }

    override fun getContextMenuConfiguration(): ContextMenuConfiguration {
        return KiloContextMenuConfiguration()
    }

    /**
     * Kilo Code context menu configuration - shows all actions for parity with Roo.
     */
    private class KiloContextMenuConfiguration : ContextMenuConfiguration {
        override fun isActionVisible(actionType: ContextMenuActionType): Boolean = true
        override fun getVisibleActions(): List<ContextMenuActionType> = ContextMenuActionType.values().toList()
    }

    /** Explain Code action: kilo.explainCode.InCurrentTask */
    class ExplainCodeAction : AnAction("Explain Code") {
        private val logger: Logger = Logger.getInstance(ExplainCodeAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.explainCode.InCurrentTask", "EXPLAIN", args, project)
        }
    }

    /** Fix Code action: kilo.fixCode.InCurrentTask */
    class FixCodeAction : AnAction("Fix Code") {
        private val logger: Logger = Logger.getInstance(FixCodeAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.fixCode.InCurrentTask", "FIX", args, project)
        }
    }

    /** Fix Logic action: kilo.fixCode.InCurrentTask (same command as Fix Code) */
    class FixLogicAction : AnAction("Fix Logic") {
        private val logger: Logger = Logger.getInstance(FixLogicAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.fixCode.InCurrentTask", "FIX", args, project)
        }
    }

    /** Improve Code action: kilo.improveCode.InCurrentTask */
    class ImproveCodeAction : AnAction("Improve Code") {
        private val logger: Logger = Logger.getInstance(ImproveCodeAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.improveCode.InCurrentTask", "IMPROVE", args, project)
        }
    }

    /** Add to Context action: kilo.addToContext */
    class AddToContextAction : AnAction("Add to Context") {
        private val logger: Logger = Logger.getInstance(AddToContextAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.addToContext", "ADD_TO_CONTEXT", args, project)
        }
    }

    /** New Task action: kilo.newTask */
    class NewTaskAction : AnAction("New Task") {
        private val logger: Logger = Logger.getInstance(NewTaskAction::class.java)
        override fun actionPerformed(e: AnActionEvent) {
            val project = e.project ?: return
            val editor = e.getData(CommonDataKeys.EDITOR) ?: return
            val file = e.dataContext.getData(CommonDataKeys.VIRTUAL_FILE) ?: return

            val effectiveRange = getEffectiveRange(editor) ?: return

            val args = mutableMapOf<String, Any?>()
            args["filePath"] = file.path
            args["selectedText"] = effectiveRange.text
            args["startLine"] = effectiveRange.startLine + 1
            args["endLine"] = effectiveRange.endLine + 1

            handleCodeAction("kilo.newTask", "NEW_TASK", args, project)
        }
    }

    data class EffectiveRange(
        val text: String,
        val startLine: Int,
        val endLine: Int
    )

    companion object {
        fun getEffectiveRange(editor: com.intellij.openapi.editor.Editor): EffectiveRange? {
            val document = editor.document
            val selectionModel = editor.selectionModel

            return if (selectionModel.hasSelection()) {
                val selectedText = selectionModel.selectedText ?: ""
                val startLine = document.getLineNumber(selectionModel.selectionStart)
                val endLine = document.getLineNumber(selectionModel.selectionEnd)
                EffectiveRange(selectedText, startLine, endLine)
            } else {
                null
            }
        }

        fun handleCodeAction(command: String, promptType: String, params: Any, project: Project?) {
            val latestWebView = project?.getService(WebViewManager::class.java)?.getLatestWebView()
            if (latestWebView == null) {
                return
            }

            val messageContent = when {
                command.contains("addToContext") -> {
                    val promptParams = if (params is Map<*, *>) params as Map<String, Any?> else emptyMap()
                    mapOf(
                        "type" to "invoke",
                        "invoke" to "setChatBoxMessage",
                        "text" to KiloSupportPrompt.create("ADD_TO_CONTEXT", promptParams)
                    )
                }
                command.endsWith("InCurrentTask") -> {
                    val promptParams = if (params is Map<*, *>) params as Map<String, Any?> else emptyMap()
                    val basePromptType = when {
                        command.contains("explain") -> "EXPLAIN"
                        command.contains("fix") -> "FIX"
                        command.contains("improve") -> "IMPROVE"
                        else -> promptType
                    }
                    mapOf(
                        "type" to "invoke",
                        "invoke" to "sendMessage",
                        "text" to KiloSupportPrompt.create(basePromptType, promptParams)
                    )
                }
                else -> {
                    val promptParams = if (params is List<*>) {
                        val argsList = params as List<Any>
                        if (argsList.size >= 4) {
                            mapOf(
                                "filePath" to argsList[0],
                                "selectedText" to argsList[1],
                                "startLine" to argsList[2],
                                "endLine" to argsList[3]
                            )
                        } else {
                            emptyMap()
                        }
                    } else if (params is Map<*, *>) {
                        params as Map<String, Any?>
                    } else {
                        emptyMap()
                    }

                    val basePromptType = when {
                        command.contains("explain") -> "EXPLAIN"
                        command.contains("fix") -> "FIX"
                        command.contains("improve") -> "IMPROVE"
                        else -> promptType
                    }

                    mapOf(
                        "type" to "invoke",
                        "invoke" to "initClineWithTask",
                        "text" to KiloSupportPrompt.create(basePromptType, promptParams)
                    )
                }
            }

            val messageJson = com.google.gson.Gson().toJson(messageContent)
            latestWebView.postMessageToWebView(messageJson)
        }
    }
}
