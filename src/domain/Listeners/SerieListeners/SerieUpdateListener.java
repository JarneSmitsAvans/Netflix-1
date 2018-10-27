package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SerieUpdateListener.java
 * This ActionListener will update the selected serie.
 * Author: Marc Verwijmeren
 */

public class SerieUpdateListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;

    // Constructor
    public SerieUpdateListener(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // Check if the combobox isn't empty
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
               Serie serie = (Serie) cbSelectedSerie.getSelectedItem();
               String serieTitle = ui.getTxtUpdateSerieTitle().getText();
               String serieGenre = ui.getTxtUpdateSerieGenre().getText();
               String serieLanguage = ui.getTxtUpdateSerieLanguage().getText();
               String serieAge = ui.getTxtUpdateSerieAge().getText();

               if(ui.getCbUpdateSerieRecomendedSerie().getSelectedItem() != "Selecteer serie" && ui.getCbUpdateSerieRecomendedSerie().getSelectedItem() != null && !serieTitle.isEmpty() && !serieGenre.isEmpty() && !serieLanguage.isEmpty() && !serieAge.isEmpty()) {
                   Serie checkSerie = serieManager.checkSerieTitle(serieTitle, serie.getId());
                   if (checkSerie.getTitle() == null) {
                       serie.setTitle(serieTitle);
                       serie.setGenre(serieGenre);
                       serie.setLanguage(serieLanguage);

                       int minAge = Integer.valueOf(serieAge);
                       serie.setMinAge(minAge);

                       Serie serieReference = (Serie) ui.getCbUpdateSerieRecomendedSerie().getSelectedItem();
                       serie.setRecommendedSerie(serieReference.getId());

                       boolean updated = serieManager.update(serie);

                       if (updated) {
                           JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + serie.getTitle() + " is succesvol geupdate.", "Serie is geupdate", JOptionPane.INFORMATION_MESSAGE);
                           serieManager.fillAllSerieCbx();
                           ui.getTxtUpdateSerieTitle().setText(null);
                           ui.getTxtUpdateSerieGenre().setText(null);
                           ui.getTxtUpdateSerieLanguage().setText(null);
                           ui.getTxtUpdateSerieAge().setText(null);
                       }
                       else {
                           JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het updaten van de serie " + serie.getTitle() + ". Probeer het nog eens.", "Serie niet geupdate", JOptionPane.INFORMATION_MESSAGE);
                       }
                   }
                   else {
                       JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is al een serie met deze titel.", "Serie niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                   }
               }
               else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Serie niet geupdate", JOptionPane.ERROR_MESSAGE);
               }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "U heeft nog geen serie geselecteerd. Selecteer eerst een serie en probeer het dan opnieuw.", "Serie niet geupdate", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
