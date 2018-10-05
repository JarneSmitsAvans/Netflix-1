package domain.Listeners.AccountListeners;

import application.AccountManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class AccountUpdateComboBoxListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private Account account;

    public AccountUpdateComboBoxListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbUpdateSelectedAccount().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbUpdateSelectedAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                this.ui.getTxtUpdateAccountName().setText(this.account.getName());
                this.ui.getTxtUpdateAccountAdres().setText(this.account.getAddress());
                this.ui.getTxtUpdateAccountResidence().setText(this.account.getResidence());
            } else {
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }
}
