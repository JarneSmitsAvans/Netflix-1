package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourLoadProfilesForSelectedAccountDeleteListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    public WatchBehaviourLoadProfilesForSelectedAccountDeleteListener(GUI ui) {
        this.ui = ui;
        accountManager = new AccountManagerImpl();
        profileManager = new ProfileManagerImpl();
        account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem() != null) {
                profileManager.initializeProfileComboBoxes(ui);
                String strSelectedAccount = this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                this.ui.getCbDeleteWatchedMediaProfile().setEnabled(true);
                profileManager.addProfilesToComboBox(this.ui.getCbDeleteWatchedMediaProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbDeleteWatchedMediaProfile().setSelectedItem(null);
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
