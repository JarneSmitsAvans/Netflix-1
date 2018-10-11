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

    public MovieUpdateListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(ui.getCbUpdateMovie().getSelectedItem() != null) {
                String strSelectedAccount = ui.getCbUpdateMovie().getSelectedItem().toString();
                Movie movie = movieManager.getMovieByTitle(strSelectedAccount);
                boolean movieUpdated = movieManager.update(strSelectedAccount, movie);
                try {
                    if (movieUpdated) {
                        ui.getTxtUpdateMovieTitle().setText(null);
                        ui.getTxtUpdateMovieDuration().setText(null);
                        ui.getTxtUpdateMovieGenre().setText(null);
                        ui.getTxtUpdateMovieLanguage().setText(null);
                        ui.getTxtUpdateMovieMinimumAge().setText(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Film is bewerkt", "Film bewerkt", JOptionPane.INFORMATION_MESSAGE);
                    }
                } catch (Exception exx) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het bewerken van de film", "Film niet bewerkt", JOptionPane.ERROR_MESSAGE);
                }
            }
        } catch (Exception ex) {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan bij het bewerken van de film", "Film niet bewerkt", JOptionPane.ERROR_MESSAGE);
        }
    }
}
