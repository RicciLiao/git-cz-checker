package com.github.ricciliao.gitczchecker.handler;

import com.github.ricciliao.gitczchecker.util.ServerConstants;
import com.intellij.openapi.project.DumbService;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vcs.CheckinProjectPanel;
import com.intellij.openapi.vcs.checkin.CheckinHandler;
import com.intellij.openapi.vcs.ui.RefreshableOnComponent;
import com.intellij.ui.NonFocusableCheckBox;
import org.jetbrains.annotations.Nullable;

import javax.swing.JComponent;
import javax.swing.JPanel;
import java.awt.BorderLayout;

public class CheckGitMeassgeHandler extends CheckinHandler {

    private Project project;
    private CheckinProjectPanel checkinProjectPanel;
    private static boolean checkFlag = true;


    public CheckGitMeassgeHandler(Project project, CheckinProjectPanel checkinProjectPanel) {
        this.project = project;
        this.checkinProjectPanel = checkinProjectPanel;
    }

    @Override
    public @Nullable RefreshableOnComponent getBeforeCheckinConfigurationPanel() {
        NonFocusableCheckBox checkBox = new NonFocusableCheckBox(ServerConstants.LABEL_CHECK_GIT_MESSAGE);
        return new RefreshableOnComponent() {
            @Override
            public JComponent getComponent() {
                JPanel panel = new JPanel(new BorderLayout());
                panel.add(checkBox);
                boolean dumb = DumbService.isDumb(project);
                checkBox.setEnabled(!dumb);
                return panel;
            }

            @Override
            public void refresh() {

            }

            @Override
            public void saveState() {
                checkFlag = checkBox.isSelected();
            }

            @Override
            public void restoreState() {
                checkBox.setSelected(checkFlag);
            }
        };

    }

    @Override
    public ReturnResult beforeCheckin() {
        String gitMessage = checkinProjectPanel.getCommitMessage();
        System.out.println(gitMessage);
        if (!checkFlag) {
            return ReturnResult.CANCEL;
        } else {
            return ReturnResult.COMMIT;
        }
    }
}
