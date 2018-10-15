package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieUpdateListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    // Constructor
    public MovieUpdateListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If selected combobox item is not null, update movie
            if(ui.getCbUpdateMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbUpdateMovie().getSelectedItem().toString();
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                // Set new values
                movie.setTitle(ui.getTxtUpdateMovieTitle().getText());
                movie.setDuration(Integer.parseInt(ui.getTxtUpdateMovieDuration().getText()));
                movie.setGenre(ui.getTxtUpdateMovieGenre().getText());
                movie.setLanguage(ui.getTxtUpdateMovieLanguage().getText());
                movie.setMinAge(Integer.parseInt(ui.getTxtUpdateMovieMinimumAge().getText()));
                boolean movieUpdated = movieManager.update(movie);
                // If movie has been updated, refresh combobox items and textpanes, set update values to null en do not select the first comboboxitem.
                if (movieUpdated) {
                    movieManager.initializeMovieComponents(ui);
                    ui.getCbUpdateMovie().setSelectedIndex(-1);
                    ui.getTxtUpdateMovieTitle().setText(null);
                    ui.getTxtUpdateMovieDuration().setText(null);
                    ui.getTxtUpdateMovieGenre().setText(null);
                    ui.getTxtUpdateMovieLanguage().setText(null);
                    ui.getTxtUpdateMovieMinimumAge().setText(null);
                    // Message that the movie is updated correctly.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Film is bewerkt", "Film bewerkt", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // Movie is not updated
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het bewerken van de film", "Film niet bewerkt", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // Combobox item is null.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het bewerken van de film", "Film niet bewerkt", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // Something went wrong..
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het bewerken van de film", "Film niet bewerkt", JOptionPane.ERROR_MESSAGE);
        }
    }
}
