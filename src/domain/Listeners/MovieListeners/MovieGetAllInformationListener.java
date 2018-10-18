package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MovieGetAllInformationListener.java
 * This ActionListener will show all information of the selected movie.
 * Author: Kim van den Berg
 */

public class MovieGetAllInformationListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    // Constructor
    public MovieGetAllInformationListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        this.movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If selected combobox item is not null
            if (ui.getCbReadMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbReadMovie().getSelectedItem().toString();
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                ui.getLblReadMovieTitleLabel().setVisible(true);
                ui.getLblReadMovieDurationLabel().setVisible(true);
                ui.getLblReadMovieLanguageLabel().setVisible(true);
                ui.getLblReadMovieGenreLabel().setVisible(true);
                ui.getLblReadMovieAgeLabel().setVisible(true);

                ui.getLblReadMovieTitle().setText(movie.getTitle());
                ui.getLblReadMovieDuration().setText(Integer.toString(movie.getDuration()));
                ui.getLblReadMovieGenre().setText(movie.getGenre());
                ui.getLblReadMovieLanguage().setText(movie.getLanguage());
                ui.getLblReadMovieAge().setText(Integer.toString(movie.getMinAge()));
            } else {
                // If selected combobox item is null, show error
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het ophalen van de film gegevens", "Fout bij ophalen film", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // If something went wrong, show error
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het ophalen van de film gegevens", "Fout bij ophalen film", JOptionPane.ERROR_MESSAGE);

        }
    }
}
