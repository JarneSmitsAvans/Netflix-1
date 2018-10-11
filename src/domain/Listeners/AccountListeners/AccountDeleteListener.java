package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountDeleteListener implements ActionListener  {
    private GUI ui;
    private AccountManagerImpl accountManager;
    public AccountDeleteListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbDeleteAccount().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbDeleteAccount().getSelectedItem().toString();
                if (!accountManager.empty(strSelectedAccount)) {
                    Account account = accountManager.getAccountByName(strSelectedAccount);
                    boolean deleted = accountManager.delete(account.getId());
                    if (deleted) {
                        this.accountManager.initializeAccountComboBoxes(ui);
                        this.ui.getCbDeleteAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account has been deleted.", "Account deleted", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account has not been deleted due to an unexpected error.", "Account not deleted", JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "No account selected for deletion", "Account not deleted", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
