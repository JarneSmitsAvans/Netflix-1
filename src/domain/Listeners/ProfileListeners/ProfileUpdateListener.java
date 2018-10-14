package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.ErrorHandling;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * ProfileUpdateListener.java
 * This ActionListener handles the "Update" functionality of the Profile CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileUpdateListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Profile profile;
    private AccountManagerImpl accountManager;

    // Constructor
    public ProfileUpdateListener(GUI ui) {
        this.ui = ui;
        this.profile = new Profile();
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (this.ui.getCbUpdateSelectedProfile().getSelectedItem() != null && this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null && !profileManager.empty(this.ui.getTxtUpdateProfileName().getText()) && this.ui.getjDPnewDateOfBirth().getDate() != null) {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbUpdateSelectedProfile().getSelectedItem().toString();
                java.util.Date oldDate = this.ui.getjDPnewDateOfBirth().getDate();
                java.sql.Date convertedDate = new java.sql.Date(oldDate.getTime());
                // Set values for the to-be updated profile.
                profile.setProfileName(this.ui.getTxtUpdateProfileName().getText());
                profile.setDateOfBirth(convertedDate);
                // Get the ID of the to-be updated profile.
                int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                // Update the profile
                boolean updated = profileManager.update(id, profile);
                if (updated) {
                    /* If the update was successful, empty the fields and display a success message.
                     reinitialize the account and profile components in the application. */
                    this.profileManager.initializeProfileComboBoxes(ui);
                    this.accountManager.initializeAccountComboBoxes(ui);
                    this.ui.getCbUpdateSelectedProfile().setSelectedItem(null);
                    this.ui.getTxtUpdateProfileName().setText(null);
                    this.ui.getjDPnewDateOfBirth().setDate(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profiel is gewijzigd.", "Profiel is gewijzigd", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Something went wrong, throw an error message.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Profiel is niet gewijzigd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Some fields were left empty or have invalid values, throw an error if that is the case.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }
}
