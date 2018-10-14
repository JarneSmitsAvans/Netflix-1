package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * WatchBehaviourMovieListener.java
 * This ActionListener fires should the user decide to add a newly watched movie.
 * It makes all the components required for inserting a newly watched movie visible,
 * and hides all the components for inserting an episode.
 * <p>
 * It then adds all the available movies to the movies comboBox
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private int duration;

    // Constructor
    public WatchBehaviourMovieListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.duration = 0;
            // Make serie components invisible
            ui.getLblWatchedEpisode().setVisible(false);
            ui.getLblSerieTitle().setVisible(false);
            ui.getCbAddWatchedMediaSerieTitle().setVisible(false);
            ui.getCbAddWatchedMediaEpisode().setVisible(false);

            // Make movie components visible
            ui.getLblMovieTitle().setVisible(true);
            ui.getCbAddWatchedMediaMovieTitle().setVisible(true);

            // Clear before adding
            ui.getCbAddWatchedMediaMovieTitle().removeAllItems();
            ArrayList<Movie> movieArrayList = this.movieManager.getMovies();

            // Add all available movies to the movies comboBox.
            movieManager.addMoviesToComboBox(ui.getCbAddWatchedMediaMovieTitle(), movieArrayList);
            ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
            ui.getCbAddWatchedMediaMovieTitle().setSelectedItem(null);

        } catch (Exception el) {
            el.printStackTrace();
        }
    }
}
