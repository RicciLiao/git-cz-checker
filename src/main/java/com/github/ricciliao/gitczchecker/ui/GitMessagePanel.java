package com.github.ricciliao.gitczchecker.ui;

import com.github.ricciliao.gitczchecker.util.ChangeType;
import com.intellij.openapi.ui.DialogWrapper;

import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


public class GitMessagePanel {

    private JPanel mainPanel;
    private JComboBox changeType;
    private JComboBox changeScope;
    private JTextField shortDescription;
    private JTextField jiraNum;
    private JCheckBox outOfJira;
    private JLabel changeTypeError;
    private JLabel changeScopeError;
    private JLabel shortDescriptionError;
    private JLabel jiraNumError;

    GitMessagePanel(DialogWrapper dialogWrapper) {
        for (ChangeType type : ChangeType.values()) {
            changeType.addItem(type);
        }
        outOfJira.addActionListener(actionEvent -> {
            if (outOfJira.isSelected()) {
                jiraNum.setText("");
                jiraNum.setEnabled(false);
            } else {
                jiraNum.setText("");
                jiraNum.setEnabled(true);
            }
        });
    }

    public JPanel getMainPanel() {
        return mainPanel;
    }

    public String getChangeTypeValue() {
        ChangeType type = (ChangeType) changeType.getSelectedItem();
        return type.label();
    }

    public String getChangeScopeValue() {
        if (null == changeScope.getSelectedItem()) {
            return "";
        }
        return changeScope.getSelectedItem().toString();
    }

    public String getShortDescriptionValue() {
        return shortDescription.getText().trim();
    }

    public String getJiraNumValue() {
        return jiraNum.getText().trim();
    }

    public boolean getOutOfJiraValue() {
        return outOfJira.isSelected();
    }

    public JComboBox getChangeType() {
        return changeType;
    }

    public JComboBox getChangeScope() {
        return changeScope;
    }

    public JTextField getShortDescription() {
        return shortDescription;
    }

    public JTextField getJiraNum() {
        return jiraNum;
    }

    public JCheckBox getOutOfJira() {
        return outOfJira;
    }

    public JLabel getChangeTypeError() {
        return changeTypeError;
    }

    public JLabel getChangeScopeError() {
        return changeScopeError;
    }

    public JLabel getShortDescriptionError() {
        return shortDescriptionError;
    }

    public JLabel getJiraNumError() {
        return jiraNumError;
    }
}
