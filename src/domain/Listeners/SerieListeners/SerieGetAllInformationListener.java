package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SerieGetAllInformationListener.java
 * This ActionListener will show all the information about a serie.
 * Author: Marc Verwijmeren
 */

public class SerieGetAllInformationListener implements ActionListener {
    private GUI ui;
    private JComboBox selectedSerie;
    private SerieManagerImpl serieManager;

    // Constructor
    public SerieGetAllInformationListener(GUI ui, JComboBox selectedSerie) {
        this.ui = ui;
        this.selectedSerie = selectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    // OnActionPerformed: Show and fill all the labels with the data from a serie
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if the combobox isn't empty
        if(selectedSerie.getSelectedItem() != "Selecteer serie" && selectedSerie.getSelectedItem() != null){
            // Get the selected serie
            Serie serie = (Serie)selectedSerie.getSelectedItem();

            // Fill the labels with data
            ui.getLblSerieViewTitlelb().setText(serie.getTitle());
            ui.getLblSerieGenre().setText(serie.getGenre());
            ui.getLblSerieLanguage().setText(serie.getLanguage());
            String minAge = Integer.toString(serie.getMinAge());
            ui.getLblSerieMinAge().setText(minAge);

            Serie recommendedSerie = serieManager.getSerieById(serie.getRecommendedSerie());
            ui.getLblSerieRecommended().setText(recommendedSerie.toString());

            // Make all the lables visible
            ui.getLblSerieViewTitlelb().setVisible(true);
            ui.getLblSerieGenre().setVisible(true);
            ui.getLblSerieLanguage().setVisible(true);
            ui.getLblSerieMinAge().setVisible(true);
            ui.getLblSerieRecommended().setVisible(true);

            ui.getLblSerieTitleLabel().setVisible(true);
            ui.getLblSerieGenreLabel().setVisible(true);
            ui.getLblSerieLanguageLabel().setVisible(true);
            ui.getLblSerieMinAgeLabel().setVisible(true);
            ui.getLblSerieRecommendedLabel().setVisible(true);
        }
        else{
            // Make all the labels invisible
            serieManager.hideSerieLabels();
        }
    }
}
