package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MovieUpdateFillFieldsListener.java
 * This ActionListener will fill the update fields by chosen movie.
 * Author: Kim van den Berg
 */

public class MovieUpdateFillFieldsListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    // Constructor
    public MovieUpdateFillFieldsListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If selected combobox item is not null, fill update textboxes with selected movie data.
            if (ui.getCbUpdateMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbUpdateMovie().getSelectedItem().toString();
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                ui.getTxtUpdateMovieTitle().setText(movie.getTitle());
                ui.getTxtUpdateMovieDuration().setText(Integer.toString(movie.getDuration()));
                ui.getTxtUpdateMovieGenre().setText(movie.getGenre());
                ui.getTxtUpdateMovieLanguage().setText(movie.getLanguage());
                ui.getTxtUpdateMovieMinimumAge().setText(Integer.toString(movie.getMinAge()));
            }
        } catch (Exception ex) {
            // If something went wrong..
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het ophalen van de gegevens van de film", "Filmgegevens konden niet worden opgehaald", JOptionPane.ERROR_MESSAGE);
        }
    }
}
