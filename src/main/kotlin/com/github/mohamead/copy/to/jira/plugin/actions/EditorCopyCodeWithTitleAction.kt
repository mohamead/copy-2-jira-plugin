package com.github.mohamead.copy.to.jira.plugin.actions

import com.intellij.openapi.actionSystem.AnActionEvent

internal class EditorCopyCodeWithTitleAction : EditorCopyCodeAction() {

    override fun actionPerformed(e: AnActionEvent) {
        val filename = getFilename(e) ?: return
        val codeToCopy = getSelectedText(e) ?: return
        val valueToCopy = "{code:title=$filename}\n$codeToCopy\n{code}"
        copy(valueToCopy)
    }

    private fun getFilename(e: AnActionEvent): String? = getEditorFile(e)?.name

}
