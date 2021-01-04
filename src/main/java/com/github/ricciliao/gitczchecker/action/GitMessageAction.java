package com.github.ricciliao.gitczchecker.action;

import com.github.ricciliao.gitczchecker.ui.GitMessageDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.vcs.CommitMessageI;
import com.intellij.openapi.vcs.VcsDataKeys;
import com.intellij.openapi.vcs.ui.Refreshable;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class GitMessageAction extends AnAction implements DumbAware {

    @Override
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final CommitMessageI commitPanel = getCommitPanel(anActionEvent);
        if (commitPanel == null) {
            return;
        }
        GitMessageDialog dialog = new GitMessageDialog(anActionEvent.getProject());
        dialog.show();
        if (dialog.getExitCode() == DialogWrapper.OK_EXIT_CODE) {
            commitPanel.setCommitMessage(dialog.getCommitMessage());
        }
    }

    private static CommitMessageI getCommitPanel(@Nullable AnActionEvent anActionEvent) {
        if (anActionEvent == null) {
            return null;
        }
        Refreshable data = Refreshable.PANEL_KEY.getData(anActionEvent.getDataContext());
        if (data instanceof CommitMessageI) {
            return (CommitMessageI) data;
        }
        return VcsDataKeys.COMMIT_MESSAGE_CONTROL.getData(anActionEvent.getDataContext());
    }

}
