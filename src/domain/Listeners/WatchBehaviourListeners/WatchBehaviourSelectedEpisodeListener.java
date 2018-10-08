package domain.Listeners.WatchBehaviourListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WatchBehaviourSelectedEpisodeListener implements ActionListener {
    private GUI ui;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManagerlmpl;
    private int duration;

    public WatchBehaviourSelectedEpisodeListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManagerlmpl = new EpisodeManagerlmpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null) {
            ArrayList<Episode> episodes = this.episodeManagerlmpl.getAllEpisodes();
            String selectedSerie = ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
            Serie serie = serieManager.getSerieByName(selectedSerie);
            for (Episode episode : episodes) {
                if (episode.getTitle().equals(ui.getCbAddWatchedMediaEpisode().getSelectedItem().toString())) {
                    if (episode.getSerieNumber() == serie.getId()) {
                        duration = episode.getDuration();
                        ui.getLblDurationOfSelectedProgram().setText(Integer.toString(duration));
                        ui.getBtnAddWatchBehaviour().setActionCommand("insertEpisode");
                    }
                }
            }
        }
    }
}

