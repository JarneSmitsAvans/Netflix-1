package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieSelectedForEpisodesListener implements ActionListener {
    private GUI ui;

    public SerieSelectedForEpisodesListener(GUI ui){
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Get the selected serie
        if(ui.getCbAvgOfWatchedSerie().getSelectedItem() != "Selecteer serie" && ui.getCbAvgOfWatchedSerie().getSelectedItem() != null){
            Serie serie = (Serie)ui.getCbAvgOfWatchedSerie().getSelectedItem();

            EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(ui);
            episodeManagerlmpl.fillAllEpisodesCbx(serie.getId());
        }

    }
}
