package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

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
 * WatchBehaviourLoadWatchedMediaForDeleteListener.java
 * This ActionListener loads the watched programs by the selected profile, and adds it to the watched programs comboBox
 * It does this so that the user can select a watched program, and delete the watched program.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourLoadWatchedMediaForDeleteListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    // Constructor
    public WatchBehaviourLoadWatchedMediaForDeleteListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbDeleteWatchedMediaProfile().getSelectedItem() != null) {
            // Check if input wasn't empty
            try {
                // Declare/initialize variables
                int profileID;
                this.ui.getCbDeleteWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbDeleteWatchedMediaProfile().getSelectedItem().toString(), ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString());
                ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
                ArrayList<Episode> watchedEpisodes = watchBehaviourManager.getWatchedEpisodes(profileID);
                // For each watched movie, create a new object of WatchedMovie type and place it into the watchedMedia comboBox.
                for (Movie movie : watchedMovies) {
                    ui.getCbDeleteWatchedMediaTitle().addItem(new WatchedMovie(movie.getWatchedDuration(), movie.getWatchedOn(), movie.getTitle(), movie.getDuration(), movie.getId()));
                }
                // For each watched episode, create a new object of WatchedEpisode type and place it into the watchedMedia comboBox.
                for (Episode episode : watchedEpisodes) {
                    ui.getCbDeleteWatchedMediaTitle().addItem(new WatchedEpisode(episode.getId(), episode.getWatchedDuration(), episode.getWatchedOn(), episode.getTitle(), episode.getSerieTitle(), episode.getDuration()));
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ui.getCbDeleteWatchedMediaTitle().setSelectedItem(null);
        }else{
            return;
        }
    }
}

