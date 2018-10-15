package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieGetSelectedSerieForEpisodeListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;

    //Constructor
    public SerieGetSelectedSerieForEpisodeListener(GUI ui , JComboBox cbSelectedSerie){
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the selected serie
        if(cbSelectedSerie.getSelectedItem() != "Selecteer serie"  && cbSelectedSerie.getSelectedItem() != null){
            Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

            EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(ui);
            episodeManagerlmpl.fillAllEpisodesCbx(serie.getId());
        }
    }
}
