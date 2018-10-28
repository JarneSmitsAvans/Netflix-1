package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MovieLoadAmountOfViewsListener.java
 * This ActionListener will get the amount of viewers by the selected movie.
 * Author: Kim van den Berg
 */

public class MovieLoadAmountOfViewsListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    // Constructor
    public MovieLoadAmountOfViewsListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If combobox item is not null, get amount of viewers
            if(ui.getCbAmountOfViewsOfMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbAmountOfViewsOfMovie().getSelectedItem().toString();
                // Get movie by title.
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                // Get amount of viewers by selected movie.
                String amountOfViewers = movieManager.amountOfViewers(movie);
                String textInPane = "";
                // If amount of viewers is not 0, textInPane is this.
                if(!amountOfViewers.equals("0")) {
                    textInPane = "De film '" + strSelectedMovie + "' is in totaal (deels of volledig) " + amountOfViewers + " keer bekeken.";
                } else {
                    // If amount of viewers is 0, textInPane is this. (Movie isn't watched)
                    textInPane = "De film '" + strSelectedMovie + "' is nog niet bekeken.";
                }
                // Place textInPane variable in textpane.
                this.ui.getTxtAmountOfViewersForMovie().setText(textInPane);
            } else {
                this.ui.getTxtMoviesWatchedByAccount().setText("Er is geen juist item gekozen.");
            }
        } catch (Exception ex) {
            // If something went wrong..
            this.ui.getTxtMoviesWatchedByAccount().setText("Er is iets fout gegaan bij het ophalen van het aantal kijkers.");
        }
    }
}
