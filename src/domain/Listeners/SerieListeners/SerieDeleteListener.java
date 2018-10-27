package domain.Listeners.SerieListeners;
import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * SerieDeleteListener.java
 * This ActionListener will delete the selected serie.
 * Author: Marc Verwijmeren
 */

public class SerieDeleteListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;

    // Constructor
    public SerieDeleteListener(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    // Delete the selected serie
    public void actionPerformed(ActionEvent e) {
        try{
            // Check if the combobox isn't empty
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
                // Get the selected serie
                Serie serie = (Serie)cbSelectedSerie.getSelectedItem();

                // Delete the selected serie
                boolean deleted = serieManager.delete(serie.getId());

                // Checked if the serie has bin deleted
                if(deleted){
                    // Shows a MessageDialog with the error that the serie has bin deleted
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De serie " + serie.getTitle() + " is succesvol verwijderd.", "Serie is verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    // Empty combobox
                    serieManager.fillAllSerieCbx();
                }
                else {
                    // Shows a MessageDialog with the error that the serie hasn't bin deleted
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het verwijderen van de serie" + serie.getTitle() + ". Probeer het nog eens.", "Serie niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                // Shows a MessageDialog with the error that the serie hasn't bin deleted
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "U heeft nog geen serie geselecteerd. Selecteer eerst een serie en probeer het dan opnieuw.", "Serie niet verwijderd", JOptionPane.ERROR_MESSAGE);
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
