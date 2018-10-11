package domain.Listeners.SerieListeners;
import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieDeleteListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;

    public SerieDeleteListener(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
                Serie serie = (Serie)cbSelectedSerie.getSelectedItem();
                boolean deleted = serieManager.delete(serie.getId());

                if(deleted){
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + serie.getTitle() + " is succesvol verwijderd.", "Serie is verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                }
                else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het verwijderen van de serie" + serie.getTitle() + ". Probeer het nog eens.", "Serie niet verwijderd", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "U heeft nog geen serie geselecteerd. Selecteer eerst een serie en probeer het dan opnieuw.", "Serie niet verwijderd", JOptionPane.INFORMATION_MESSAGE);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
