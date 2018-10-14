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
public class ProfileUpdateListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private Profile profile;
    private AccountManagerImpl accountManager;


    public ProfileUpdateListener(GUI ui) {
        this.ui = ui;
        this.profile = new Profile();
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbUpdateSelectedProfile().getSelectedItem() != null && this.ui.getCbSelectAccountForProfileEdit().getSelectedItem() != null && !profileManager.empty(this.ui.getTxtUpdateProfileName().getText()) && this.ui.getjDPnewDateOfBirth().getDate() != null) {
                String strSelectedAccount = this.ui.getCbSelectAccountForProfileEdit().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbUpdateSelectedProfile().getSelectedItem().toString();

                java.util.Date oldDate = this.ui.getjDPnewDateOfBirth().getDate();
                java.sql.Date convertedDate = new java.sql.Date(oldDate.getTime());

                profile.setProfileName(this.ui.getTxtUpdateProfileName().getText());
                profile.setDateOfBirth(convertedDate);
                int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                boolean updated = profileManager.update(id, profile);
                if (updated) {
                    this.profileManager.initializeProfileComboBoxes(ui);
                    this.accountManager.initializeAccountComboBoxes(ui);
                    this.ui.getCbUpdateSelectedProfile().setSelectedItem(null);
                    this.ui.getTxtUpdateProfileName().setText(null);
                    this.ui.getjDPnewDateOfBirth().setDate(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profiel is gewijzigd.", "Profiel is gewijzigd", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Profiel is niet gewijzigd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
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
