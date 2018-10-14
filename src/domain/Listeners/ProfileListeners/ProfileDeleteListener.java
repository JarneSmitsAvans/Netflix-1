package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.ErrorHandling;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * ProfileCreateListener.java
 * This ActionListener handles the "Delete" functionality of the Profile CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileDeleteListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;

    // Constructor
    public ProfileDeleteListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // Check if input wasn't empty or invalid
            if (this.ui.getCbDeleteProfile().getSelectedItem() != null && this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem() != null) {
                // Declare/initialize variables
                String strSelectedProfile = this.ui.getCbDeleteProfile().getSelectedItem().toString();
                String strSelectedAccount = this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem().toString();
                // Check if selected profile wasn't null/empty
                if (!profileManager.empty(strSelectedProfile)) {
                    // Get the ID of that the selected profile
                    int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                    // Delete the profile
                    boolean deleted = profileManager.delete(id);
                    if (deleted) {
                        /* If the deleted was successful, empty the fields and display a success message.
                        reinitialize the account and profile components in the application. */
                        this.profileManager.initializeProfileComboBoxes(ui);
                        this.accountManager.initializeAccountComboBoxes(ui);
                        this.ui.getCbDeleteProfileFromSelectedAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profiel is verwijderd.", "Profiel verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        // Something went wrong, throw an error message.
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Profiel niet verwijderd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                // Some fields were left empty or have invalid values, throw an error if that is the case.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Profiel niet verwijderd", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
