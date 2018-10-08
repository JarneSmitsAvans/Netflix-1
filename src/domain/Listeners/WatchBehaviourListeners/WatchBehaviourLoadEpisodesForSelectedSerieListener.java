package domain.Listeners.WatchBehaviourListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;


public class WatchBehaviourLoadEpisodesForSelectedSerieListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private int duration;

    public WatchBehaviourLoadEpisodesForSelectedSerieListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbAddWatchedMediaSerieTitle().getSelectedItem() != null) {
            this.duration = 0;
            this.ui.getCbAddWatchedMediaEpisode().removeAllItems();
            String strSelectedSerie = this.ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
                Serie serie = serieManager.getSerieByName(strSelectedSerie);
                int id = serie.getId();
                episodeManager.setEpisodeList(id);
            ArrayList<Episode> episodes = episodeManager.setEpisodeList(id);
            episodeManager.appendComboBox(this.ui.getCbAddWatchedMediaEpisode(), episodes);

            // get time duration of selected episode
            /*for (Episode episode : episodes) {
                duration = duration + episode.getDuration();
            }*/
            ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
            ui.getCbAddWatchedMediaEpisode().setSelectedItem(null);
        }
    }
}

