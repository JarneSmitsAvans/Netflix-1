package domain.Listeners.MovieListeners;

import application.AccountManagerImpl;
import application.MovieManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class MovieGetCbValueWatchedByAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private MovieManagerImpl movieManager;
    private Account account;

    public MovieGetCbValueWatchedByAccountListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(ui.getCbWatchedByAccount().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbUpdateSelectedAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = account.getId();
//                ArrayList<String> list = movieManager.
//                this.ui.getTxtUpdateAccountName().setText(this.account.getName());

            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
