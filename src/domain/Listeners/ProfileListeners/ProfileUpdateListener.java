package domain.Listeners.ProfileListeners;

import application.ProfileManagerImpl;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ProfileUpdateListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Profile profile;

    public ProfileUpdateListener(GUI ui) {
        this.ui = ui;
        this.profile = new Profile();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbUpdateSelectedProfile().getSelectedItem() != null && !profileManager.empty(this.ui.getTxtUpdateProfileName().getText()) && this.ui.getjDPnewDateOfBirth().getDate() != null) {
                String strSelectedProfile = this.ui.getCbUpdateSelectedProfile().getSelectedItem().toString();

                java.util.Date oldDate = this.ui.getjDPnewDateOfBirth().getDate();
                java.sql.Date convertedDate = new java.sql.Date(oldDate.getTime());

                profile.setProfileName(this.ui.getTxtUpdateProfileName().getText());
                profile.setDateOfBirth(convertedDate);
                boolean updated = profileManager.update(strSelectedProfile, profile);
                if (updated) {
                    this.profileManager.initializeProfileComboBoxes(ui);
                    this.ui.getCbUpdateSelectedProfile().setSelectedItem(null);
                    this.ui.getTxtUpdateProfileName().setText(null);
                    this.ui.getjDPnewDateOfBirth().setDate(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profile has been updated.", "Profile updated", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occurred when trying to update the selected profile.", "Profile has not been updated", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "One or more fields were found empty when trying to update the profile. Please validate your input.", "Missing information", JOptionPane.ERROR_MESSAGE);
            }
        } catch (SQLException e1) {
            e1.printStackTrace();
        } catch (ClassNotFoundException e1) {
            e1.printStackTrace();
        }

    }
}
