package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfilesLoadProfilesForSelectedAccountUpdate implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private Account account;

    public ProfilesLoadProfilesForSelectedAccountUpdate(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null) {
                profileManager.initializeProfileComboBoxes(ui);
                this.ui.getTxtUpdateProfileName().setText(null);
                this.ui.getjDPnewDateOfBirth().setDate(null);
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = this.account.getId();
                this.ui.getCbUpdateSelectedProfile().setEnabled(true);
                profileManager.addProfilesToComboBox(this.ui.getCbUpdateSelectedProfile(), profileManager.getMatchingProfiles(id));
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
