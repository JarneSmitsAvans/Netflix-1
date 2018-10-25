package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Episode;
import domain.Movie;
import domain.WatchedEpisode;
import domain.WatchedMovie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * WatchBehaviorLoadWatchedMediaForEditListener.java
 * This ActionListener loads the watched programs by the selected profile, and adds it to the watched programs comboBox
 * It does this so that the user can select a watched program, and edit the information of the watched program.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviorLoadWatchedMediaForEditListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    // Constructor
    public WatchBehaviorLoadWatchedMediaForEditListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbEditWatchedMediaProfile().getSelectedItem() != null) {
            // Check if input wasn't empty
            try {
                // Declare/initialize variables
                int profileID;
                this.ui.getCbEditWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbEditWatchedMediaProfile().getSelectedItem().toString(), ui.getCbEditWatchedMediaAccount().getSelectedItem().toString());
                ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
                ArrayList<Episode> watchedEpisodes = watchBehaviourManager.getWatchedEpisodes(profileID);
                // For each watched movie, create a new object of WatchedMovie type and place it into the watchedMedia comboBox.
                for (Movie movie : watchedMovies) {
                    ui.getCbEditWatchedMediaTitle().addItem(new WatchedMovie(movie.getWatchedDuration(), movie.getWatchedOn(), movie.getTitle(), movie.getDuration(), movie.getId()));
                }
                // For each watched episode, create a new object of WatchedEpisode type and place it into the watchedMedia comboBox.
                for (Episode episode : watchedEpisodes) {
                    ui.getCbEditWatchedMediaTitle().addItem(new WatchedEpisode(episode.getId(), episode.getWatchedDuration(), episode.getWatchedOn(), episode.getTitle(), episode.getSerieTitle(), episode.getDuration()));
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ui.getCbEditWatchedMediaTitle().setSelectedItem(null);
            ui.getLblEditWatchedMediaDuration().setText("0");
            ui.getTxtEditWatchedMediaDuration().setText("0");
        }else{
            return;
        }
    }
}
