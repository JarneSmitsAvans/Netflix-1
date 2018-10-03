package domain;

import application.AccountManagerImpl;
import presentation.GUI;

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
        /* TO DO: Error handling */
        try {
            this.account.setName(this.ui.getTxtAccountName().getText());
            this.account.setResidence(this.ui.getTxtAccountResidence().getText());
            this.account.setAddress(this.ui.getTxtAccountAddress().getText());
            this.accountManager.create(account);
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
