package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieLoadAmountOfViewsListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    public MovieLoadAmountOfViewsListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(ui.getCbAmountOfViewsOfMovie().getSelectedItem() != null) {
                String strSelectedMovie = ui.getCbAmountOfViewsOfMovie().getSelectedItem().toString();
                movie = movieManager.getMovieByTitle(strSelectedMovie);
                String amountOfViewers = movieManager.amountOfViewers(movie);
                String textInPane = "";
                if(!amountOfViewers.equals("0")) {
                    textInPane = "De film '" + strSelectedMovie + "' is in totaal " + amountOfViewers + " keer bekeken.";
                } else {
                    textInPane = "De film '" + strSelectedMovie + "' is nog niet bekeken.";
                }
                this.ui.getTxtAmountOfViewersForMovie().setText(textInPane);
            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
