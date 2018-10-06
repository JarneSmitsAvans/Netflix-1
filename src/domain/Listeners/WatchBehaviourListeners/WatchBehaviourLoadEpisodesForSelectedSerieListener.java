package domain.Listeners.WatchBehaviourListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class WatchBehaviourLoadEpisodesForSelectedSerieListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;

    public WatchBehaviourLoadEpisodesForSelectedSerieListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();
        if (command.equals("fillWithEpisodes")) {
            if (ui.getCbAddWatchedMediaTitle().getSelectedItem() != null) {
                this.ui.getCbAddWatchedMediaEpisode().removeAllItems();
                String strSelectedSerie = this.ui.getCbAddWatchedMediaTitle().getSelectedItem().toString();
                Serie serie = serieManager.getSerieByName(strSelectedSerie);
                int id = serie.getId();
                episodeManager.setEpisodeList(id);
                episodeManager.appendComboBox(this.ui.getCbAddWatchedMediaEpisode(), episodeManager.setEpisodeList(id));
            }
        }
    }
}
