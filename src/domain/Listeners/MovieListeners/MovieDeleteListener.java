package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieDeleteListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    public MovieDeleteListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (ui.getCbDeleteMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbDeleteMovie().getSelectedItem().toString();
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                boolean deleted = movieManager.delete(movie);
                if(deleted) {
                    movieManager.initializeMovieComponents(ui);
                    ui.getCbDeleteMovie().setSelectedIndex(-1);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Film is verwijderd", "Film verwijderd", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het verwijderen van de film", "Film niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het verwijderen van de film", "Film niet verwijderd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
