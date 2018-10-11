package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.*;
import domain.ComboBoxItem;
import domain.Episode;
import domain.Movie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviourLoadDurationListener implements ActionListener {
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private GUI ui;
    private int watchedDuration;

    public WatchBehaviourLoadDurationListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbEditWatchedMediaTitle().getSelectedItem() != null) {
            Object comboBoxItem = ui.getCbEditWatchedMediaTitle().getSelectedItem();
            ComboBoxItem cbSelectedMedia = (ComboBoxItem) comboBoxItem;
            watchedDuration = cbSelectedMedia.getWatchedDuration();
            ui.getTxtEditWatchedMediaDuration().setText((Integer.toString(watchedDuration)));
            int programDuration = 0;
            if (!cbSelectedMedia.getEpisodeTitle().isEmpty()) {
                episodeManager.setEpisodeList(cbSelectedMedia.getId());
                ArrayList<Episode> episodes = episodeManager.getEpisode();
                for (Episode episode : episodes) {
                    if (episode.getId() == cbSelectedMedia.getId()) {
                        programDuration = episode.getDuration();
                        ui.getLblEditWatchedMediaDuration().setText(Integer.toString(programDuration));
                    }
                }
            } else {
                try {
                    Movie movie = movieManager.getMovieByTitle(cbSelectedMedia.getMediaTitle());
                    ui.getLblEditWatchedMediaDuration().setText((Integer.toString(movie.getDuration())));
                } catch (SQLException e1) {
                    e1.printStackTrace();
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

            }
        }
    }
}
