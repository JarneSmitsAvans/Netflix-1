package domain.Listeners.WatchBehaviourListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourLoadProfilesForSelectedAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    public WatchBehaviourLoadProfilesForSelectedAccountListener(GUI ui) {
        this.ui = ui;
        accountManager = new AccountManagerImpl();
        profileManager = new ProfileManagerImpl();
        account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      /* try {
            if (this.ui.getComboBox1().getSelectedItem() != null) {
                profileManager.initializeProfileComboBoxes(ui);
                String strSelectedAccount = this.ui.getComboBox1().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                this.ui.getComboBox2().setEnabled(true);
                profileManager.addProfilesToComboBox(this.ui.getComboBox2(), profileManager.getMatchingProfiles(id));
            } else {
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }*/
    }
}
