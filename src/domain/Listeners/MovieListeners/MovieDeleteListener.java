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

    // Constructor
    public MovieDeleteListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If the selected item from the combobox is not null we can delete the movie.
            if (ui.getCbDeleteMovie().getSelectedItem() != null) {
                // Get selected movie title
                String strSelectedMovie = ui.getCbDeleteMovie().getSelectedItem().toString();
                // Get movie by title
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                // Delete movie
                boolean deleted = movieManager.delete(movie);
                // If the movie has been deleted, fill all comboboxes and textpanes again, set selected item to null and show 'Film verwijderd' dialog.
                if(deleted) {
                    movieManager.initializeMovieComponents(ui);
                    ui.getCbDeleteMovie().setSelectedIndex(-1);
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Film is verwijderd", "Film verwijderd", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // If the movie is not deleted, show 'Film niet verwijderd' dialog.
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het verwijderen van de film", "Film niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                // If selected movie is null, show 'Film niet verwijderd' dialog.
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het verwijderen van de film", "Film niet verwijderd", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception ex) {
            // If something went wrong, show 'Film niet verwijderd' dialog.
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het verwijderen van de film", "Film niet verwijderd", JOptionPane.ERROR_MESSAGE);
        }
    }
}
