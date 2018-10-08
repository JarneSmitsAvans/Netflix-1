package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieViewListener implements ActionListener {
    private GUI gui;

    public SerieViewListener(GUI gui){
        this.gui = gui;
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        //get the selected serie
        Serie serie =  (Serie)gui.getCbAvgOfWatchedSerie().getSelectedItem();

        EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(gui);
        episodeManagerlmpl.fillAllEpisodesCbx(serie.getId());
    }
}
