package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import domain.ErrorHandling;
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
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is aangemaakt.", "Account aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Account niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                }
            }
            else
            {
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Account niet verwijderd", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
