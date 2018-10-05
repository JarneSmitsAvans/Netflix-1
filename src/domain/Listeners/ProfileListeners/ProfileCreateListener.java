package domain.Listeners.ProfileListeners;
import application.AccountManagerImpl;
import domain.Profile;
import presentation.GUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ProfileCreateListener implements ActionListener {
    private GUI ui;
    private Profile profile;
    private AccountManagerImpl accountManager;
    public ProfileCreateListener(GUI ui, Profile profile) {
        this.ui = ui;
        this.profile = profile;
        this.accountManager = new AccountManagerImpl();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //if (!accountManager.empty(this.ui.getTxtProfileName().getText()) && this.ui.getProfileDoB().getDate() != null && this.ui.getCbAddProfileToSelectedAccount().getSelectedItem() != null) {
            try {

                //String strSelectedAccount = this.ui.getCbAddProfileToSelectedAccount().getSelectedItem().toString();
                //Account account = accountManager.getAccountByName(strSelectedAccount);
                //java.util.Date date = this.ui.getProfileDoB().getDate();
                //System.out.println(String.format("%1$tY-%1$tm-%1$td", date));
                //profile.setProfileName(this.ui.getTxtProfileName().getText());
                //profile.setDateOfBirth(date);
            } catch (Exception e1) {
                e1.printStackTrace();
            }
        }
    }
//}
