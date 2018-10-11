package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieCreateListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    public MovieCreateListener(GUI ui, Movie movie) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        this.movie = movie;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            String title = ui.getTxtMovieTitle().getText();
            int duration = Integer.parseInt(ui.getTxtMovieDuration().getText());
            String genre = ui.getTxtMovieGenre().getText();
            String language = ui.getTxtMovieLanguage().getText();
            int minumumAge = Integer.parseInt(ui.getTxtMovieMinAge().getText());
            if (!title.isEmpty() || duration != 0 || !genre.isEmpty() || !language.isEmpty() || minumumAge != 0) {
                movie.setTitle(title);
                movie.setDuration(duration);
                movie.setGenre(genre);
                movie.setLanguage(language);
                movie.setMinAge(minumumAge);
                boolean movieCreated = movieManager.create(movie);
                try {
                    if (movieCreated) {
                        movieManager.initializeMovieComponents(ui);
                        ui.getTxtMovieTitle().setText(null);
                        ui.getTxtMovieDuration().setText(null);
                        ui.getTxtMovieGenre().setText(null);
                        ui.getTxtMovieLanguage().setText(null);
                        ui.getTxtMovieMinAge().setText(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Film is toegevoegd", "Film toegevoegd", JOptionPane.INFORMATION_MESSAGE);

                    }
                } catch (Exception exx) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het toevoegen van de film", "Film niet toegevoegd", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het toevoegen van de film", "Film niet toegevoegd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
