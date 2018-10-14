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
 * WatchBehaviourLoadEpisodesForSelectedSerieListener.java
 * This ActionListener gets all the episodes matching the selected series and places it into the episode comboBox.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourLoadEpisodesForSelectedSerieListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private int duration;

    // Constructor
    public WatchBehaviourLoadEpisodesForSelectedSerieListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
    }

    // OnActionPerformed: Get all the episode matching the serie and place it into the episode comboBox.
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (ui.getCbAddWatchedMediaSerieTitle().getSelectedItem() != null) {
            // Declare/initialize variables
            this.duration = 0;
            String strSelectedSerie = this.ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
            // Empty the comboBox to avoid double data
            this.ui.getCbAddWatchedMediaEpisode().removeAllItems();
            // Get the serie that matches the selected serie title.
            Serie serie = serieManager.getSerieByName(strSelectedSerie);
            int id = serie.getId();
            // Get all the episodes matching the selected serie.
            episodeManager.setEpisodeList(id);
            ArrayList<Episode> episodes = episodeManager.getEpisode();
            // Place all the found episodes in the comboBox.
            episodeManager.appendComboBox(this.ui.getCbAddWatchedMediaEpisode(), episodes);
            // Display the max duration of the selected episode.
            ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
            ui.getCbAddWatchedMediaEpisode().setSelectedItem(null);
        }else{
            return;
        }
    }
}

