package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import domain.ErrorHandling;
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
                    !accountManager.empty(this.ui.getTxtUpdateAccountResidence().getText()) && this.ui.getCbUpdateAccount().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbUpdateAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                account.setName(this.ui.getTxtUpdateAccountName().getText());
                account.setAddress(this.ui.getTxtUpdateAccountAdres().getText());
                account.setResidence(this.ui.getTxtUpdateAccountResidence().getText());
                boolean updated = accountManager.update(this.account.getId(), account);
                if(updated)
                {
                    this.accountManager.initializeAccountComboBoxes(ui);
                    this.ui.getCbUpdateAccount().setSelectedItem(null);
                    this.ui.getTxtUpdateAccountName().setText(null);
                    this.ui.getTxtUpdateAccountAdres().setText(null);
                    this.ui.getTxtUpdateAccountResidence().setText(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Account is gewijzigd.", "Account gewijzigd", JOptionPane.INFORMATION_MESSAGE);
                }
                else{
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Account niet gewijzigd", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }
    }
}
