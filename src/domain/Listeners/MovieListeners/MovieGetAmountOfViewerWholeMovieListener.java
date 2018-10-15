package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * MovieGetAmountOfViewerWholeMovieListener.java
 * This ActionListener will get the amount of viewers who watched the selected movie completely.
 * Author: Kim van den Berg
 */

public class MovieGetAmountOfViewerWholeMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;

    // Constructor
    public MovieGetAmountOfViewerWholeMovieListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If selected combobox item is not null, get amount of viewers who watched the whole movie.
            if (ui.getCbAmountOfViewerWholeMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbAmountOfViewerWholeMovie().getSelectedItem().toString();
                String amountOfViewer = movieManager.viewersByMovie(strSelectedMovie);
                // Place String amountOfViewer in the textPane.
                ui.getTxtAmountOfViewersWholeMovie().setText(amountOfViewer);
            } else {
                // If selected combobox item is null, place this in the textPane.
                ui.getTxtAmountOfViewersWholeMovie().setText("Er is iets fout gegaan met het ophalen van de gegevens.");
            }
        } catch (Exception ex) {
            // If something went wrong, place this in the textPane.
            ui.getTxtAmountOfViewersWholeMovie().setText("Er is iets fout gegaan met het ophalen van de gegevens.");
        }
    }
}
