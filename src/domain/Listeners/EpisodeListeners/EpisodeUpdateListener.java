package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpisodeUpdateListener implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedEpisode;
    private EpisodeManagerlmpl episodeManager ;
    private SerieManagerImpl serieManager ;

    // Constructor
    public EpisodeUpdateListener(GUI ui,JComboBox cbSelectedEpisode) {
        this.ui = ui;
        this.cbSelectedEpisode = cbSelectedEpisode;
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(ui.getCbUpdateEpisodeOfSerie().getSelectedItem() != "Selecteer serie" && ui.getCbUpdateEpisodeOfSerie().getSelectedItem() != null){
                String episodeTitle = ui.getTxtUpdateEpisodeTitle().getText();
                int episodeNumber = Integer.valueOf(ui.getTxtUpdateEpisodeNumber().getText());
                int episodeDuration = Integer.valueOf(ui.getTxtUpdateEpisodeDuration().getText());

                if(cbSelectedEpisode.getSelectedItem() != "Selecteer serie" && cbSelectedEpisode.getSelectedItem() != null && !episodeTitle.isEmpty() && episodeNumber != 0 && episodeDuration !=0) {
                    Episode episode = (Episode)cbSelectedEpisode.getSelectedItem();
                    episode.setTitle(episodeTitle);
                    episode.setEpisodeNumber(episodeNumber);
                    episode.setDuration(episodeDuration);

                    boolean updated = episodeManager.update(episode);

                    if (updated) {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De aflevering " + episode.getTitle() + " is succesvol geupdate.", "Serie is geupdate", JOptionPane.INFORMATION_MESSAGE);
                        serieManager.fillAllSerieCbx();
                        cbSelectedEpisode.setSelectedIndex(-1);
                        cbSelectedEpisode.setEnabled(false);
                        ui.getTxtUpdateEpisodeTitle().setText(null);
                        ui.getTxtUpdateEpisodeNumber().setText(null);
                        ui.getTxtUpdateEpisodeDuration().setText(null);
                    }
                    else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het updaten van de aflvering " + episode.getTitle() + ". Probeer het nog eens.", "Serie niet geupdate", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Serie niet geupdate", JOptionPane.INFORMATION_MESSAGE);
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
