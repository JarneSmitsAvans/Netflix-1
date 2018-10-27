package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EpisodeDeleteListener.java
 * This ActionListener will delete the selected episode.
 * Author: Marc Verwijmeren
 */

public class EpisodeDeleteListener implements ActionListener {
    private GUI ui;
    private EpisodeManagerlmpl episodeManager ;
    private SerieManagerImpl serieManager ;
    private JComboBox cbSelectedEpisode;

    // Constructor
    public EpisodeDeleteListener(GUI ui, JComboBox cbSelectedEpisode) {
        this.ui = ui;
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
        this.cbSelectedEpisode = cbSelectedEpisode;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // Check if the combobox isn't empty
            if(cbSelectedEpisode.getSelectedItem() != "Selecteer serie" && cbSelectedEpisode.getSelectedItem() != null) {
                // Get the selected episode
                Episode episode = (Episode)cbSelectedEpisode.getSelectedItem();

                // Delete the selected episode
                boolean deleted = episodeManager.delete(episode.getId());

                // Checked if an episode had bin deleted
                if (deleted) {
                    // Show MessageDialog the the episode has bin deleted
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De aflevering " + episode.getTitle() + " is succesvol verwijderd.", "Aflevering is verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                    cbSelectedEpisode.setSelectedIndex(-1);
                    cbSelectedEpisode.setEnabled(false);
                } else {
                    // Show MessageDialog the the episode hasn't bin deleted
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het verwijderen van de serie" + episode.getTitle() + ". Probeer het nog eens.", "Aflevering niet verwijderd", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                // Shows an info MessageDialog that not all the fields are filled in
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Aflevering niet verwijderd", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
