package domain.Listeners.ProfileListeners;
import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileCreateListener implements ActionListener {
    private GUI ui;
    private Profile profile;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;

    public ProfileCreateListener(GUI ui, Profile profile) {
        this.ui = ui;
        this.profile = profile;
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (!profileManager.empty(this.ui.getTxtProfileName().getText()) && this.ui.getjDPdateOfBirth().getDate() != null && this.ui.getCbAddProfileToSelectedAccount().getSelectedItem() != null) {
            try {
                String strSelectedAccount = this.ui.getCbAddProfileToSelectedAccount().getSelectedItem().toString();
                Account account = accountManager.getAccountByName(strSelectedAccount);
                profile.setProfileName(this.ui.getTxtProfileName().getText());
                java.util.Date oldDate = this.ui.getjDPdateOfBirth().getDate();
                java.sql.Date convertedDate = new java.sql.Date(oldDate.getTime());
                profile.setDateOfBirth(convertedDate);
                profile.setAccountNumber(account.getId());
                int exists = profileManager.getIdOfProfile(this.profile.getProfileName(), account.getName());
                if (exists == 0) {
                    boolean created = this.profileManager.create(profile);
                    if (created) {
                        this.accountManager.initializeAccountComboBoxes(ui);
                        this.ui.getCbAddProfileToSelectedAccount().setSelectedItem(null);
                        this.ui.getTxtProfileName().setText(null);
                        this.ui.getjDPdateOfBirth().setDate(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profile has been created.", "Profile created", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occurred when trying to create a new profile.", "Profile has not been created", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "A profile with that name already exists for that account.", "Profile has not been created", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "One or more fields were found empty when trying to create a new profile. Please validate your input.", "Missing information", JOptionPane.ERROR_MESSAGE);
        }
    }
}
