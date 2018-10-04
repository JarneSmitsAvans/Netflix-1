package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountUpdateListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    public AccountUpdateListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (!accountManager.empty(this.ui.getTxtUpdateAccountName().getText()) &&
                    !accountManager.empty(this.ui.getTxtUpdateAccountAdres().getText()) &&
                    !accountManager.empty(this.ui.getTxtUpdateAccountAdres().getText()) && this.ui.getCbUpdateSelectedAccount().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbUpdateSelectedAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                account.setName(this.ui.getTxtUpdateAccountName().getText());
                account.setAddress(this.ui.getTxtUpdateAccountAdres().getText());
                account.setResidence(this.ui.getTxtUpdateAccountAdres().getText());
                boolean updated = accountManager.update(this.account.getId(), account);
                if(updated)
                {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account has been updated.", "Account updated", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occurred when trying to update the selected account.", "Account has not been updated", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "One or more fields were found empty when trying to update the account. Please validate your input.", "Missing information", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
