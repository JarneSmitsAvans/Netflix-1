package domain.Listeners.ProfileListeners;

import application.ProfileManagerImpl;
import domain.Profile;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileUpdateComboBoxListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Profile profile;

    public ProfileUpdateComboBoxListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
        this.profile = new Profile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null && this.ui.getCbUpdateSelectedProfile().getSelectedItem() != null) {
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbUpdateSelectedProfile().getSelectedItem().toString();
                int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                this.profile = profileManager.getProfileById(id);
                this.ui.getTxtUpdateProfileName().setText(profile.getProfileName());
                this.ui.getjDPnewDateOfBirth().setDate(profile.getDateOfBirth());
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
