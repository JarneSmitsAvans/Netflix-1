package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

import application.MovieManagerImpl;
import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.ErrorHandling;
import domain.Listeners.WatchBehaviourListeners.EpisodeComboBoxItem;
import domain.Listeners.WatchBehaviourListeners.MovieComboBoxItem;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WatchBehaviourDeleteListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private GUI ui;

    public WatchBehaviourDeleteListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem() != null && this.ui.getCbDeleteWatchedMediaProfile().getSelectedItem() != null
                && this.ui.getCbDeleteWatchedMediaTitle().getSelectedItem() != null) {
            try {
                int deletionId = 0;
                String strSelectedAccount = this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbDeleteWatchedMediaProfile().getSelectedItem().toString();
                int profileId = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                Object comboBoxItem = ui.getCbDeleteWatchedMediaTitle().getSelectedItem();
                if (comboBoxItem instanceof MovieComboBoxItem) {
                    deletionId = ((MovieComboBoxItem) comboBoxItem).getId();
                } else if (comboBoxItem instanceof EpisodeComboBoxItem) {
                    deletionId = ((EpisodeComboBoxItem) comboBoxItem).getEpisodeId();
                }
                boolean deleted = watchBehaviourManager.delete(deletionId, profileId);
                if (deleted) {
                    ui.getCbDeleteWatchedMediaTitle().removeAllItems();
                    ui.getCbEditWatchedMediaTitle().removeAllItems();
                    ui.getCbDeleteWatchedMediaProfile().removeAllItems();
                    ui.getCbDeleteWatchedMediaAccount().setSelectedItem(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Kijkgedrag is verwijderd", "Kijkgedrag verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    movieManager.initializeMovieComponents(ui);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Kijkgedrag niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}




