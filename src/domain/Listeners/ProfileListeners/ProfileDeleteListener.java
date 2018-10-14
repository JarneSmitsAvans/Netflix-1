package domain.Listeners.ProfileListeners;

import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.ErrorHandling;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileDeleteListener implements ActionListener {
    private GUI ui;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;

    public ProfileDeleteListener(GUI ui) {
        this.ui = ui;
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (this.ui.getCbDeleteProfile().getSelectedItem() != null && this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem() != null) {
                String strSelectedProfile = this.ui.getCbDeleteProfile().getSelectedItem().toString();
                String strSelectedAccount = this.ui.getCbDeleteProfileFromSelectedAccount().getSelectedItem().toString();
                if (!profileManager.empty(strSelectedProfile)) {
                    int id = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                    boolean deleted = profileManager.delete(id);
                    if (deleted) {
                        this.profileManager.initializeProfileComboBoxes(ui);
                        this.accountManager.initializeAccountComboBoxes(ui);
                        this.ui.getCbDeleteProfileFromSelectedAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Profiel is verwijderd.", "Profiel verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Profiel niet verwijderd", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Profiel niet verwijderd", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (Exception e1) {
            e1.printStackTrace();
        }

    }
}
