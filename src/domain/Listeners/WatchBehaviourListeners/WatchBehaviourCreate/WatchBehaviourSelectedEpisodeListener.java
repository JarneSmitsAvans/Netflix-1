package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;
import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * WatchBehaviourSelectedMovieListener.java
 * This ActionListener fires when the user selects an episode that he/she has watched, and would like to add it to the database.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourSelectedEpisodeListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManagerlmpl;
    private int duration;

    // Constructor
    public WatchBehaviourSelectedEpisodeListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManagerlmpl = new EpisodeManagerlmpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null) {
            // Get all episodes in the database matching the selected serie.
            ArrayList<Episode> episodes = this.episodeManagerlmpl.getAllEpisodes();
            String selectedSerie = ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
            Serie serie = serieManager.getSerieByName(selectedSerie);
            for (Episode episode : episodes) {
                if (episode.getTitle().equals(ui.getCbAddWatchedMediaEpisode().getSelectedItem().toString())) {
                     /* Iterate through each episode, if an episode title and a serieid that matches the watched episode is found, get the max duration of that episode
                    and display it on the screen */
                    if (episode.getSerieNumber() == serie.getId()) {
                        duration = episode.getDuration();
                        ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
                         /* We want to add an episode, so we set the Action Command to a "insertEpisode" string, so that we can differentiate later on
                        when we want to insert the watched episode to the database.
                        */
                        ui.getBtnAddWatchBehaviour().setActionCommand("insertEpisode");
                    }
                }
            }
        }else{
            return;
        }
    }
}

