package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            if(cbSelectedEpisode.getSelectedItem() != "Selecteer serie" && cbSelectedEpisode.getSelectedItem() != null) {
                Episode episode = (Episode)cbSelectedEpisode.getSelectedItem();

                boolean created = episodeManager.delete(episode.getId());

                if (created) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De aflevering " + episode.getTitle() + " is succesvol verwijderd.", "Aflevering is verwijderd", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                    cbSelectedEpisode.setSelectedIndex(-1);
                    cbSelectedEpisode.setEnabled(false);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het verwijderen van de serie" + episode.getTitle() + ". Probeer het nog eens.", "Aflevering niet verwijderd", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Aflevering niet verwijderd", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
