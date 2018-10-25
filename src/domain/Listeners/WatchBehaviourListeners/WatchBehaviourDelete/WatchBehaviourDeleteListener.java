package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

import application.MovieManagerImpl;
import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.ErrorHandling;
import domain.WatchedEpisode;
import domain.WatchedMovie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

/**
 * WatchBehaviourDeleteListener.java
 * This ActionListener handles the "Delete" functionality of the WatchBehaviour CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourDeleteListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private GUI ui;

    // Constructor
    public WatchBehaviourDeleteListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem() != null && this.ui.getCbDeleteWatchedMediaProfile().getSelectedItem() != null
                && this.ui.getCbDeleteWatchedMediaTitle().getSelectedItem() != null) {
            try {
                // Declare/initialize variables
                int deletionId = 0;
                String strSelectedAccount = this.ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbDeleteWatchedMediaProfile().getSelectedItem().toString();
                int profileId = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                Object comboBoxItem = ui.getCbDeleteWatchedMediaTitle().getSelectedItem();
                // If the selected program is of type Movie, get the movie id.
                if (comboBoxItem instanceof WatchedMovie) {
                    deletionId = ((WatchedMovie) comboBoxItem).getId();
                    // else if the selected program is of type Episode, get the episode id.
                } else if (comboBoxItem instanceof WatchedEpisode) {
                    deletionId = ((WatchedEpisode) comboBoxItem).getEpisodeId();
                }
                // Delete the watched media.
                boolean deleted = watchBehaviourManager.delete(deletionId, profileId);
                /* If the delete was successful, empty the fields and display a success message.
                   After that, reinitialize the movie components in the application. */
                if (deleted) {
                    ui.getCbDeleteWatchedMediaTitle().removeAllItems();
                    ui.getCbEditWatchedMediaTitle().removeAllItems();
                    ui.getCbDeleteWatchedMediaProfile().removeAllItems();
                    ui.getCbDeleteWatchedMediaAccount().setSelectedItem(null);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Kijkgedrag is verwijderd", "Kijkgedrag verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    movieManager.initializeMovieComponents(ui);
                } else {
                    // Something went wrong, throw an error message.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Kijkgedrag niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        } else {
            // Some fields were left empty or have invalid values, throw an error if that is the case.
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}




