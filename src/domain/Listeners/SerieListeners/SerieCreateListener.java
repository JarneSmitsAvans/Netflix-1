package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SerieCreateListener.java
 * This ActionListener will create a new serie.
 * Author: Marc Verwijmeren
 */

public class SerieCreateListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;

    //Constructor
    public SerieCreateListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    // Create a new serie
    public void actionPerformed(ActionEvent e) {
        try{
            // Get the values of the textfields
            String serieTitle = ui.getTxtSerieCreateTitle().getText();
            String serieGenre = ui.getTxtSerieCreateGenre().getText();
            String serieLanguage = ui.getTxtSerieCreateLanguage().getText();
            String serieAge = ui.getTxtSerieCreateAge().getText();

            //Check if the combobox is empty or not
            if(ui.getCbCreateSerieRecomendedSerie().getSelectedItem() != "Selecteer serie" && ui.getCbCreateSerieRecomendedSerie().getSelectedItem() != null && !serieTitle.isEmpty() && !serieGenre.isEmpty() && !serieLanguage.isEmpty() && !serieAge.isEmpty()) {
                if( serieManager.getSerieByName(serieTitle).getTitle() == null){
                    Serie newSerie = new Serie();

                    // Set all te values for a new serie
                    newSerie.setTitle(serieTitle);
                    newSerie.setGenre(serieGenre);
                    newSerie.setLanguage(serieLanguage);
                    int minAge = Integer.valueOf(serieAge);
                    newSerie.setMinAge(minAge);
                    Serie serieReference = (Serie) ui.getCbCreateSerieRecomendedSerie().getSelectedItem();
                    newSerie.setRecommendedSerie(serieReference.getId());

                    // Create a new serie
                    boolean created = serieManager.create(newSerie);

                    // Checks if the seire has bin created
                    if (created) {
                        // Shows a MessageDialog with the succes message that the serie has bin created with the created serie in it
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + newSerie.getTitle() + " is succesvol aangemaakt.", "Serie is aangemaakt", JOptionPane.INFORMATION_MESSAGE);

                        //Empty all the textfields and combobox
                        serieManager.fillAllSerieCbx();
                        ui.getTxtSerieCreateTitle().setText(null);
                        ui.getTxtSerieCreateGenre().setText(null);
                        ui.getTxtSerieCreateLanguage().setText(null);
                        ui.getTxtSerieCreateAge().setText(null);
                    } else {
                        // Shows a MessageDialog with the error that the serie hasn't bin created
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het aanmaken van de serie" + newSerie.getTitle() + ". Probeer het nog eens.", "Serie niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                    }
                }
                else{
                    // Shows a MessageDialog with the error that the serie hasn't bin created
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is al een serie met deze titel.", "Serie niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                // Shows a MessageDialog that not all the fields have been completed
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Serie niet aangemaakt", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
