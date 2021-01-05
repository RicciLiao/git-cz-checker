package com.github.ricciliao.gitczchecker.ui;

import com.github.ricciliao.gitczchecker.util.ServerConstants;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.Nullable;

import javax.swing.BorderFactory;
import javax.swing.JComponent;
import javax.swing.border.Border;
import java.awt.Color;

public class GitMessageDialog extends DialogWrapper {

    private final GitMessagePanel panel;
    private final Border redBorder = BorderFactory.createLineBorder(Color.RED);
    private final Border greenBorder = BorderFactory.createLineBorder(Color.GREEN);

    public GitMessageDialog(@Nullable Project project) {
        super(project);
        panel = new GitMessagePanel(this);
        setTitle(ServerConstants.TITLE_PANEL);
        setOKButtonText("OK");
        init();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        return panel.getMainPanel();
    }

    public String getCommitMessage() {
        if (panel.getOutOfJiraValue()) {
            if (StringUtils.isBlank(panel.getChangeScopeValue())) {
                return String.format(ServerConstants.FORMAT_GIT_MESSAGE_NONE_SCOPE,
                        panel.getChangeTypeValue(),
                        panel.getShortDescriptionValue());
            }

            return String.format(ServerConstants.FORMAT_GIT_MESSAGE,
                    panel.getChangeTypeValue(),
                    panel.getChangeScopeValue(),
                    panel.getShortDescriptionValue());
        } else {
            if (StringUtils.isBlank(panel.getChangeScopeValue())) {
                return String.format(ServerConstants.FORMAT_GIT_MESSAGE_NONE_SCOPE_WITH_JIRA,
                        panel.getChangeTypeValue(),
                        panel.getShortDescriptionValue(),
                        String.format(ServerConstants.FORMAT_JIRA_LINK_URL, panel.getJiraNumValue()));
            }

            return String.format(ServerConstants.FORMAT_GIT_MESSAGE_WITH_JIRA,
                    panel.getChangeTypeValue(),
                    panel.getChangeScopeValue(),
                    panel.getShortDescriptionValue(),
                    String.format(ServerConstants.FORMAT_JIRA_LINK_URL, panel.getJiraNumValue()));
        }
    }

    @Override
    protected void doOKAction() {
        if (passValidation()) {
            super.doOKAction();
        }
    }

    private boolean passValidation() {
        boolean result = true;
        if (StringUtils.isBlank(panel.getChangeTypeValue())) {
            panel.getChangeTypeError().setText("x");
            result = false;
        } else {
            panel.getChangeTypeError().setText("");
        }
        /*if (StringUtils.isBlank(panel.getChangeScopeValue())) {
            panel.getChangeScopeError().setText("x");
            result = false;
        } else {
            panel.getChangeScopeError().setText("");
        }*/
        if (StringUtils.isBlank(panel.getShortDescriptionValue())) {
            panel.getShortDescriptionError().setText("x");
            result = false;
        } else {
            panel.getShortDescriptionError().setText("");
        }
        if ((!panel.getOutOfJiraValue())
                && StringUtils.isBlank(panel.getJiraNumValue())) {
            panel.getJiraNumError().setText("x");
            result = false;
        } else {
            panel.getJiraNumError().setText("");
        }

        return result;
    }

}
