package domain.Listeners.WatchBehaviourListeners;

import application.MovieManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WatchBehaviourMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private int duration;

    public WatchBehaviourMovieListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            this.duration = 0;
            // Make serie components invisible
            ui.getLblWatchedEpisode().setVisible(false);
            ui.getLblSerieTitle().setVisible(false);
            ui.getCbAddWatchedMediaSerieTitle().setVisible(false);
            ui.getCbAddWatchedMediaEpisode().setVisible(false);

            // Make movie components visible
            ui.getLblMovieTitle().setVisible(true);
            ui.getCbAddWatchedMediaMovieTitle().setVisible(true);

            // Clear before adding
            ui.getCbAddWatchedMediaMovieTitle().removeAllItems();
            ArrayList<Movie> movieArrayList = this.movieManager.getMovies();

            movieManager.addMoviesToComboBox(ui.getCbAddWatchedMediaMovieTitle(), movieArrayList);
            ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
            ui.getCbAddWatchedMediaMovieTitle().setSelectedItem(null);


        } catch (Exception el) {
            el.printStackTrace();
        }
    }
}
