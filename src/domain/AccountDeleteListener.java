package domain;

import application.AccountManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AccountDeleteListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;

    public AccountDeleteListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
