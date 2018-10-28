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
 * EpisodeCreateListener.java
 * This ActionListener will create a new episode.
 * Author: Marc Verwijmeren
 */

public class EpisodeCreateListener implements ActionListener {
    private GUI ui;
    private EpisodeManagerlmpl episodeManager ;
    private SerieManagerImpl serieManager ;

    // Constructor
    public EpisodeCreateListener(GUI ui) {
        this.ui = ui;
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // Create variables for the values from textfields
            String episodeTitle = ui.getTxtCreateEpisodeTitle().getText();
            String episodeNumberST = ui.getTxtCreateEpisodeNumber().getText();
            String episodeDurationST = ui.getTxtCreateEpisodeDuration().getText();


            // Check if the combobox and textfields aren't empty
            if(ui.getCbCreateEpisodeForSerie().getSelectedItem() != "Selecteer serie" && ui.getCbCreateEpisodeForSerie().getSelectedItem() != null && !episodeTitle.isEmpty() && !episodeNumberST.isEmpty() && !episodeDurationST.isEmpty()) {
                int episodeNumber = Integer.valueOf(ui.getTxtCreateEpisodeNumber().getText());
                int episodeDuration = Integer.valueOf(ui.getTxtCreateEpisodeDuration().getText());
                //Set values for a episode
                Episode newEpisode = new Episode();
                newEpisode.setTitle(episodeTitle);
                newEpisode.setEpisodeNumber(episodeNumber);
                newEpisode.setDuration(episodeDuration);

                Serie serie = (Serie) ui.getCbCreateEpisodeForSerie().getSelectedItem();
                newEpisode.setSerieNumber(serie.getId());

                // Create a new episode
                boolean created = episodeManager.create(newEpisode);

                // Checked if an episode had bin created
                if (created) {
                    // Shows an info MessageDialog that the episode has bin created
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De aflevering " + newEpisode.getTitle() + " is succesvol aangemaakt.", "Aflevering is aangemaakt", JOptionPane.INFORMATION_MESSAGE);

                    // Empty all the fields
                    serieManager.fillAllSerieCbx();
                    ui.getTxtCreateEpisodeTitle().setText(null);
                    ui.getTxtCreateEpisodeDuration().setText(null);
                    ui.getTxtCreateEpisodeNumber().setText(null);
                    ui.getTxtSerieCreateAge().setText(null);
                } else {
                    // Shows an error MessageDialog that the episode hasn't bin created
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het aanmaken van de serie" + newEpisode.getTitle() + ". Probeer het nog eens.", "Aflevering niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                // Shows an info MessageDialog that not all the fields are filled in
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Aflevering niet aangemaakt", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
