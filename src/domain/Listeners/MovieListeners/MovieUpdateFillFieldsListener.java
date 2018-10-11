package domain.Listeners.MovieListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MovieUpdateFillFieldsListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private Movie movie;

    public MovieUpdateFillFieldsListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        movie = new Movie();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
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

        }
    }
}
