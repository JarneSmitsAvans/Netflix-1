package domain.Listeners.SerieListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieUpdateListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;

    public SerieUpdateListener(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
                Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

                serie.setTitle(ui.getTxtUpdateSerieTitle().getText());
                serie.setGenre(ui.getTxtUpdateSerieGenre().getText());
                serie.setLanguage(ui.getTxtUpdateSerieLanguage().getText());
                serie.setLanguage(ui.getTxtUpdateSerieLanguage().getText());

                Serie serieReference = (Serie)ui.getCbUpdateSerieReferenceNumber().getSelectedItem();
                serie.setReferenceNumber(serieReference.getId());

                int minAge = Integer.valueOf(ui.getTxtUpdateSerieAge().getText());
                serie.setMinAge(minAge);

                boolean update = serieManager.update(serie);

                if(update){
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + serie.getTitle() + " is succesvol geupdate.", "Serie is geupdate", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                }
                else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het updaten van de serie" + serie.getTitle() + ". Probeer het nog eens.", "Serie niet geupdate", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "U heeft nog geen serie geselecteerd. Selecteer eerst een serie en probeer het dan opnieuw.", "Serie niet geupdate", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
