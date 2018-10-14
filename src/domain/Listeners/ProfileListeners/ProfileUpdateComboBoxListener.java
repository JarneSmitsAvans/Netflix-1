package domain.Listeners.ProfileListeners;

import application.ProfileManagerImpl;
import domain.Profile;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileUpdateComboBoxListener.java
 * This ActionListener fires whenever the user selects a profile that he/she would like to edit.
 * The current name and date of birth is then displayed in editable fields.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileUpdateComboBoxListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Profile profile;

    // Constructor
    public ProfileUpdateComboBoxListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
        this.profile = new Profile();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null && this.ui.getCbUpdateSelectedProfile().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbUpdateSelectedProfile().getSelectedItem().toString();
                // Get the ID of the selected profile
                int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                // Get all the data matching the profile id and initialize it as the selected profile object.
                this.profile = profileManager.getProfileById(id);
                // Set the current profile name as the text of the update field.
                this.ui.getTxtUpdateProfileName().setText(profile.getProfileName());
                // Set the current profile dateOfBirth as the text of the update field.
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
