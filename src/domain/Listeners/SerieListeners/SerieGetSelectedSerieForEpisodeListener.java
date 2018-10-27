package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SerieGetSelectedSerieForEpisodeListener.java
 * This ActionListener will fill the episode combobox based on the selected serie.
 * Author: Marc Verwijmeren
 */

public class SerieGetSelectedSerieForEpisodeListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private JComboBox episodeCbToFill;

    // Constructor
    public SerieGetSelectedSerieForEpisodeListener(GUI ui , JComboBox cbSelectedSerie,JComboBox episodeCbToFill){
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.episodeCbToFill = episodeCbToFill;
    }
    @Override
    // Fill de combobox with episodes based on the selected serie
    public void actionPerformed(ActionEvent e) {
        // Check if the combobox isn't empty
        if(cbSelectedSerie.getSelectedItem() != "Selecteer serie"  && cbSelectedSerie.getSelectedItem() != null){
            // Get the selected serie
            Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

            // Fill the combobox
            EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(ui);
            episodeManagerlmpl.fillAllEpisodesCbx(serie.getId(),episodeCbToFill);
        }

        else{
            // Empty the combobox
            episodeCbToFill.removeAllItems();
            episodeCbToFill.setSelectedIndex(-1);
        }
    }
}
