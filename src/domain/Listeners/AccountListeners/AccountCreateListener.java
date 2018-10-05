package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountCreateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    public AccountCreateListener(GUI ui, Account account) {
        this.ui = ui;
        this.account = account;
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!accountManager.empty(this.ui.getTxtAccountName().getText()) && !accountManager.empty(this.ui.getTxtAccountAddress().getText()) && !accountManager.empty(this.ui.getTxtAccountResidence().getText()))  {
                this.account.setName(this.ui.getTxtAccountName().getText());
                this.account.setAddress(this.ui.getTxtAccountAddress().getText());
                this.account.setResidence(this.ui.getTxtAccountResidence().getText());
                boolean created = this.accountManager.create(account);
                if (created)
                {
                    this.ui.getTxtAccountName().setText(null);
                    this.ui.getTxtAccountAddress().setText(null);
                    this.ui.getTxtAccountResidence().setText(null);
                    this.accountManager.initializeAccountComboBoxes(ui);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account has been created.", "Account created", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occurred when trying to create a new account.", "Account has not been created", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "One or more fields were found empty when trying to create a new account. Please validate your input.", "Missing information", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
