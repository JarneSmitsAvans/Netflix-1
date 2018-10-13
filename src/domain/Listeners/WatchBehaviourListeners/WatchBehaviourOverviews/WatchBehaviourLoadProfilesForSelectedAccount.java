package domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourLoadProfilesForSelectedAccount implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    public WatchBehaviourLoadProfilesForSelectedAccount(GUI ui) {
        this.ui = ui;
        accountManager = new AccountManagerImpl();
        profileManager = new ProfileManagerImpl();
        account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem() != null) {
                profileManager.initializeProfileComboBoxes(ui);
                String strSelectedAccount = this.ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                this.ui.getCbWatchedProgramsBySelectedProfile().setEnabled(true);
                profileManager.addProfilesToComboBox(ui.getCbWatchedProgramsBySelectedProfile(), profileManager.getMatchingProfiles(id));
                this.ui.getCbWatchedProgramsBySelectedProfile().setSelectedItem(null);
                ui.getTxtWatchedProgramsBySelectedProfile().setText(null);
            } else {
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }
    }
}
