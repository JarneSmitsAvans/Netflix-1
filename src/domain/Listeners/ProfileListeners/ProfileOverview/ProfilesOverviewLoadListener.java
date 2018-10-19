package domain.Listeners.ProfileListeners.ProfileOverview;

import application.ProfileManagerImpl;
import domain.Account;
import domain.Profile;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfilesOverviewLoadListener.java
 * This ActionListener gets the selected profile information and displays that with labels.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfilesOverviewLoadListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Account account;

    // Constructor
    public ProfilesOverviewLoadListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty
            if (this.ui.getCbProfileOverviewSelectProfile().getSelectedItem() != null && this.ui.getCbProfileOverviewSelectAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbProfileOverviewSelectAccount().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbProfileOverviewSelectProfile().getSelectedItem().toString();
                // Get the profile id that matches the selected account and profile
                int profileID = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                // Get the profile that matches the requested profile as an object of type Profile.
                Profile profile = profileManager.getProfileById(profileID);
                // Making sure that the labels are now visible
                this.ui.getLblForOverviewProfileName().setVisible(true);
                this.ui.getLblForOverviewProfileDateOfBirth().setVisible(true);
                this.ui.getLblOverviewProfileName().setVisible(true);
                this.ui.getLblOverviewProfileDateOfBirth().setVisible(true);
                // Place the requested profile data as the value for labels that represent the data of the requested profile.
                this.ui.getLblOverviewProfileName().setText(profile.getProfileName());
                this.ui.getLblOverviewProfileDateOfBirth().setText(profile.getDateOfBirth().toString());
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
