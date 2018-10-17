package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieCreateListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;

    //Constructor
    public SerieCreateListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String serieTitle = ui.getTxtSerieCreateTitle().getText();
            String serieGenre = ui.getTxtSerieCreateGenre().getText();
            String serieLanguage = ui.getTxtSerieCreateLanguage().getText();
            String serieAge = ui.getTxtSerieCreateAge().getText();

            if(ui.getCbCreateSerieRecomendedSerie().getSelectedItem() != "Selecteer serie" && ui.getCbCreateSerieRecomendedSerie().getSelectedItem() != null && !serieTitle.isEmpty() && !serieGenre.isEmpty() && !serieLanguage.isEmpty() && !serieAge.isEmpty()) {
                Serie newSerie = new Serie();

                newSerie.setTitle(serieTitle);
                newSerie.setGenre(serieGenre);
                newSerie.setLanguage(serieLanguage);

                int minAge = Integer.valueOf(serieAge);
                newSerie.setMinAge(minAge);

                Serie serieReference = (Serie) ui.getCbCreateSerieRecomendedSerie().getSelectedItem();
                newSerie.setRecommendedSerie(serieReference.getId());


                boolean created = serieManager.create(newSerie);

                if (created) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + newSerie.getTitle() + " is succesvol aangemaakt.", "Serie is aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                    ui.getTxtSerieCreateTitle().setText(null);
                    ui.getTxtSerieCreateGenre().setText(null);
                    ui.getTxtSerieCreateLanguage().setText(null);
                    ui.getTxtSerieCreateAge().setText(null);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het aanmaken van de serie" + newSerie.getTitle() + ". Probeer het nog eens.", "Serie niet aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Serie niet aangemaakt", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
