package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieViewListener implements ActionListener {
    private GUI ui;

    public SerieViewListener(GUI ui){
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //Get the selected serie
        Serie serie = (Serie)ui.getCbAvgOfWatchedSerie().getSelectedItem();

        EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(ui);
        episodeManagerlmpl.fillAllEpisodesCbx(serie.getId());
    }
}
