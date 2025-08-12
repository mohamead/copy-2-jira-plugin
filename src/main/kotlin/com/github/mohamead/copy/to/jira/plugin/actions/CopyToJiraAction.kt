package com.github.mohamead.copy.to.jira.plugin.actions

import com.intellij.openapi.actionSystem.ActionUpdateThread
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.ide.CopyPasteManager
import java.awt.datatransfer.StringSelection

internal abstract class CopyToJiraAction : AnAction() {

    override fun getActionUpdateThread(): ActionUpdateThread = ActionUpdateThread.EDT

    protected fun copy(valueToCopy: String) = CopyPasteManager.getInstance().setContents(StringSelection(valueToCopy))

}
