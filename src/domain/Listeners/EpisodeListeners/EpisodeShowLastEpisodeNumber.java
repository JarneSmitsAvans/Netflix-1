package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpisodeShowLastEpisodeNumber implements ActionListener {
    private GUI ui;
    private EpisodeManagerlmpl episodeManager ;
    private SerieManagerImpl serieManager ;
    private JComboBox selectedCb;

    // Constructor
    public EpisodeShowLastEpisodeNumber(GUI ui, JComboBox selectedCb) {
        this.ui = ui;
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
        this.selectedCb = selectedCb;
    }

    @Override
    // Show the last episodeNumber
    public void actionPerformed(ActionEvent e) {
        try{
            // Checked if the combocox isn't empty
            if(selectedCb.getSelectedItem() != "Selecteer serie" && selectedCb.getSelectedItem() != null && selectedCb.getItemCount() > 0){

                // Get the selected serie
                Serie serie = (Serie)selectedCb.getSelectedItem();

                // Get the last episodeNumber of the selected serie
                Episode lastEpisodeOfSerie = episodeManager.getEpisodeBySerieID(serie.getId());
                String lastNumber = Integer.toString(lastEpisodeOfSerie.getEpisodeNumber());
                ui.getLblEpisodeLastNumber().setText("Vorige: " + lastNumber);

                // Make the label visible
                ui.getLblEpisodeLastNumber().setVisible(true);
            }
        }
        catch (Exception ex){
            ex.printStackTrace();
        }

    }
}
