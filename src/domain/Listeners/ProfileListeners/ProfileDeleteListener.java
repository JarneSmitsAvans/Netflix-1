package domain.Listeners.ProfileListeners;

import application.ProfileManagerImpl;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileDeleteListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;

    public ProfileDeleteListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbDeleteProfile().getSelectedItem() != null) {
                String strSelectedProfile = this.ui.getCbDeleteProfile().getSelectedItem().toString();
                if (!profileManager.empty(strSelectedProfile)) {
                    Profile profile = profileManager.getProfileByName(strSelectedProfile);
                    boolean deleted = profileManager.delete(profile.getProfileName());
                    if (deleted) {
                        this.profileManager.initializeProfileComboBoxes(ui);
                        this.ui.getCbDeleteProfileFromSelectedAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profile has been deleted.", "Profile deleted", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profile has not been deleted due to an unexpected error.", "Profile not deleted", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "No profile selected for deletion", "Profile not deleted", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
