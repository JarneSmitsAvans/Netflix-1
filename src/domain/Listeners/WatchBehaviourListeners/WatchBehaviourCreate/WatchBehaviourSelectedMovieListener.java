package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * WatchBehaviourSelectedMovieListener.java
 * This ActionListener fires when the user selects a movie that he/she has watched, and would like to add it to the database.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourSelectedMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private int duration;

    // Constructor
    public WatchBehaviourSelectedMovieListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null) {
            try {
                // Get all movies in the database.
                ArrayList<Movie> movies = movieManager.getMovies();
                for (Movie movie : movies) {
                   /* Iterate through each movie, if a movie title that matches the watched movie is found, get the max duration of that movie
                    and display it on the screen */
                    if (movie.getTitle().equals(ui.getCbAddWatchedMediaMovieTitle().getSelectedItem())){
                        duration =  movie.getDuration();
                        ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
                      /* We want to add a movie, so we set the Action Command to a "insertMovie" string, so that we can differentiate later on
                      when we want to insert the watched movie to the database.
                       */
                        ui.getBtnAddWatchBehaviour().setActionCommand("insertMovie");
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }else{
            return;
        }
    }
}
