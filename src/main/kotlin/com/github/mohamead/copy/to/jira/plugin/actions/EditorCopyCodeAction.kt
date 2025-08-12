package com.github.mohamead.copy.to.jira.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.editor.Caret
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.vfs.VirtualFile

internal open class EditorCopyCodeAction : CopyToJiraAction() {

    private val logger: Logger = Logger.getInstance(EditorCopyCodeAction::class.java)

    private val validExtension: List<String> = listOf(
        "as",
        "ada",
        "applescript",
        "bash",
        "c",
        "cs",
        "c++",
        "css",
        "erl",
        "go",
        "groovy",
        "hs",
        "html",
        "java",
        "js",
        "json",
        "kt",
        "lua",
        "pl",
        "php",
        "py",
        "r",
        "rb",
        "scala",
        "sql",
        "swift",
        "vb",
        "xml",
        "yaml"
    )

    override fun update(e: AnActionEvent) {
        val extension = getExtension(e)
        logger.info("extension: $extension")
        val isValidExtension = validExtension.contains(extension)
        val hasSelection = getCurrentCaret(e)?.hasSelection() ?: false
        logger.info("hasSelection: $hasSelection")
        e.presentation.isEnabledAndVisible = isValidExtension && hasSelection
    }

    override fun actionPerformed(e: AnActionEvent) {
        val extension = getExtension(e) ?: return
        val codeToCopy = getSelectedText(e) ?: return
        val valueToCopy = "{code:$extension}\n$codeToCopy\n{code}"
        copy(valueToCopy)
    }

    private fun getCurrentCaret(e: AnActionEvent): Caret? = getEditor(e)?.caretModel?.currentCaret

    private fun getExtension(e: AnActionEvent): String? = getEditorFile(e)?.extension

    protected fun getSelectedText(e: AnActionEvent): String? = getCurrentCaret(e)?.caretModel?.currentCaret?.selectedText

    protected fun getEditorFile(e: AnActionEvent): VirtualFile? = getEditor(e)?.virtualFile

    private fun getEditor(e: AnActionEvent): Editor? = e.getData(CommonDataKeys.EDITOR)

}
