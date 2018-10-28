package domain.Listeners.ProfileListeners;
import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import domain.ErrorHandling;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * ProfileCreateListener.java
 * This ActionListener handles the "Create" functionality of the Profile CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileCreateListener implements ActionListener {
    private GUI ui;
    private Profile profile;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;

    // Constructor
    public ProfileCreateListener(GUI ui) {
        this.ui = ui;
        this.profile = new Profile();
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty or invalid
        if (!profileManager.empty(this.ui.getTxtProfileName().getText()) && this.ui.getjDPdateOfBirth().getDate() != null && this.ui.getCbAddProfileToSelectedAccount().getSelectedItem() != null) {
            try {
                // Declare/initialize variables
                String strSelectedAccount = this.ui.getCbAddProfileToSelectedAccount().getSelectedItem().toString();
                java.util.Date oldDate = this.ui.getjDPdateOfBirth().getDate();
                java.sql.Date convertedDate = new java.sql.Date(oldDate.getTime());
                // Get the selected account as an object
                Account account = accountManager.getAccountByName(strSelectedAccount);
                ArrayList<Profile> profileArrayList = profileManager.getMatchingProfiles(account.getId());
                // Check if account has not exceeded the maximum amount of profiles allowed per account
                if (profileArrayList.size() >= 5) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Dit account heeft het maximum aantal profielen bereikt. (5)", "Profiel niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Set values for the to-be created profile.
                profile.setProfileName(this.ui.getTxtProfileName().getText());
                profile.setDateOfBirth(convertedDate);
                profile.setAccountNumber(account.getId());
                // Check if the profile already exists
                int exists = profileManager.getIdOfProfile(this.profile.getProfileName(), account.getName());
                if (exists == 0) {
                    // if it doesn't, create the profile.
                    boolean created = this.profileManager.create(profile);
                    if (created) {
                        /* If the create was successful, empty the fields and display a success message.
                        reinitialize the account components in the application. */
                        this.profileManager.initializeProfileComboBoxes(ui);
                        this.accountManager.initializeAccountComponents(ui);
                        this.ui.getCbAddProfileToSelectedAccount().setSelectedItem(null);
                        this.ui.getTxtProfileName().setText(null);
                        this.ui.getjDPdateOfBirth().setDate(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profiel is aangemaakt.", "Profiel aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Something went wrong, throw an error message.
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Profiel is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // The profile already exists, throw that error message.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.ALREADYEXISTS.getError(), "Profiel is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            // Some fields were left empty or have invalid values, throw an error if that is the case.
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
