package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import datastorage.SerieDAO;
import domain.Serie;
import presentation.GUI;
import datastorage.SerieDAO;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SerieGetValuesToUpdateListener.java
 * This ActionListener will get all the values that needs to be updated and place them in a textfield.
 * Author: Marc Verwijmeren
 */

public class SerieGetValuesToUpdateListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;
    private SerieDAO serieDAO ;

    // Constructor
    public SerieGetValuesToUpdateListener(GUI ui, JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
        this.serieDAO = new SerieDAO();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Get the selected serie
        if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null && cbSelectedSerie.getItemCount() >0){
            Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

            ui.getTxtUpdateSerieTitle().setText(serie.getTitle());
            ui.getTxtUpdateSerieGenre().setText(serie.getGenre());
            ui.getTxtUpdateSerieLanguage().setText(serie.getLanguage());
            String minAge = Integer.toString(serie.getMinAge());
            ui.getTxtUpdateSerieAge().setText(minAge);


            try {
                ArrayList<Serie> serieList = serieDAO.getSeries();
                ui.getCbUpdateSerieRecomendedSerie().removeAllItems();
                serieManager.appendComboBox(ui.getCbUpdateSerieRecomendedSerie(),serieList);

                for(Serie serie1 : serieList){
                    if(serie1.getId() == serie.getRecommendedSerie()){
                        ui.getCbUpdateSerieRecomendedSerie().setSelectedItem(serie1);
                    }
                }

            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }

        }
        else {
            ui.getTxtUpdateSerieTitle().setText(null);
            ui.getTxtUpdateSerieGenre().setText(null);
            ui.getTxtUpdateSerieLanguage().setText(null);
            ui.getTxtUpdateSerieAge().setText(null);
            ui.getCbUpdateSerieRecomendedSerie().removeAllItems();
        }
    }
}
