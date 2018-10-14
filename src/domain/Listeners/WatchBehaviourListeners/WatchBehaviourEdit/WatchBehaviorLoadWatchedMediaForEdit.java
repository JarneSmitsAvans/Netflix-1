package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Episode;
import domain.Listeners.WatchBehaviourListeners.EpisodeComboBoxItem;
import domain.Listeners.WatchBehaviourListeners.MovieComboBoxItem;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviorLoadWatchedMediaForEdit implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;
    public WatchBehaviorLoadWatchedMediaForEdit(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbEditWatchedMediaProfile().getSelectedItem() != null) {
            try {
                int profileID;
                this.ui.getCbEditWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbEditWatchedMediaProfile().getSelectedItem().toString(), ui.getCbEditWatchedMediaAccount().getSelectedItem().toString());
                ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
                ArrayList<Episode> watchedEpisodes = watchBehaviourManager.getWatchedEpisodes(profileID);

                for (Movie movie : watchedMovies) {
                    ui.getCbEditWatchedMediaTitle().addItem(new MovieComboBoxItem(movie.getWatchedDuration(), movie.getWatchedOn(), movie.getTitle(), movie.getDuration(), movie.getId()));
                }
                for (Episode episode : watchedEpisodes) {
                    ui.getCbEditWatchedMediaTitle().addItem(new EpisodeComboBoxItem(episode.getId(), episode.getWatchedDuration(), episode.getWatchedOn(), episode.getTitle(), episode.getSerieTitle(), episode.getDuration()));
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            ui.getCbEditWatchedMediaTitle().setSelectedItem(null);
            ui.getLblEditWatchedMediaDuration().setText("0");
            ui.getTxtEditWatchedMediaDuration().setText("0");
        }else{
            return;
        }
    }
}
