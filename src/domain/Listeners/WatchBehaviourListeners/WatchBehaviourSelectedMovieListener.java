package domain.Listeners.WatchBehaviourListeners;

import application.MovieManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviourSelectedMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private int duration;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    public WatchBehaviourSelectedMovieListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.movieManager = new MovieManagerImpl();

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null) {
            try {
               ArrayList<Movie> movies = movieManager.getMovies();
               for (Movie movie : movies){
                   if (movie.getTitle().equals(ui.getCbAddWatchedMediaMovieTitle().getSelectedItem())){
                      duration =  movie.getDuration();
                      ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
                      ui.getBtnAddWatchBehaviour().setActionCommand("insertMovie");
                   }
               }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
