package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

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
        try {
            if (this.ui.getCbAddWatchedMediaAccount().getSelectedItem() != null) {
                ui.getJSpinWatchedDate().setEnabled(true);
                ui.getRbMovie().setEnabled(true);
                ui.getRbSerie().setEnabled(true);
                ui.getTxtAddWatchedMediaDuration().setEnabled(true);
                ui.getCbAddWatchedMediaProfile().setEnabled(true);
                profileManager.initializeProfileComboBoxes(ui);
                String strSelectedAccount = this.ui.getCbAddWatchedMediaAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                this.ui.getCbAddWatchedMediaProfile().setEnabled(true);
                profileManager.addProfilesToComboBox(this.ui.getCbAddWatchedMediaProfile(), profileManager.getMatchingProfiles(id));
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
