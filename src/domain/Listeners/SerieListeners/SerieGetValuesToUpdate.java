package domain.Listeners.SerieListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import com.sun.xml.internal.ws.commons.xmlutil.Converter;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieGetValuesToUpdate implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;

    //Constructor
    public SerieGetValuesToUpdate(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the selected serie
        if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
            Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

            ui.getTxtUpdateSerieTitle().setText(serie.getTitle());
            ui.getTxtUpdateSerieGenre().setText(serie.getGenre());
            ui.getTxtUpdateSerieLanguage().setText(serie.getLanguage());
            String minAge = Integer.toString(serie.getMinAge());
            ui.getTxtUpdateSerieAge().setText(minAge);

            Serie selectedSerie = serieManager.getSerieById(serie.getId());
            Serie serieReference = serieManager.getSerieById(selectedSerie.getReferenceNumber());

            //serieManager.fillAllSerieCbx();
            ui.getCbUpdateSerieReferenceNumber().addItem(serieReference);
            ui.getCbUpdateSerieReferenceNumber().setSelectedItem(serieReference);
        }
    }
}
