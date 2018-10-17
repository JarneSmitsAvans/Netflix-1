package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
            String episodeTitle = ui.getTxtCreateEpisodeTitle().getText();
            int episodeNumber = Integer.valueOf(ui.getTxtCreateEpisodeNumber().getText());
            int episodeDuration = Integer.valueOf(ui.getTxtCreateEpisodeDuration().getText());

            if(ui.getCbCreateEpisodeForSerie().getSelectedItem() != "Selecteer serie" && ui.getCbCreateEpisodeForSerie().getSelectedItem() != null && !episodeTitle.isEmpty() && episodeDuration != 0 && episodeNumber != 0) {
                Episode newEpisode = new Episode();

                newEpisode.setTitle(episodeTitle);
                newEpisode.setEpisodeNumber(episodeNumber);
                newEpisode.setDuration(episodeDuration);

                Serie serie = (Serie) ui.getCbCreateEpisodeForSerie().getSelectedItem();
                newEpisode.setSerieNumber(serie.getId());


                boolean created = episodeManager.create(newEpisode);

                if (created) {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "De aflevering " + newEpisode.getTitle() + " is succesvol aangemaakt.", "Aflevering is aangemaakt", JOptionPane.INFORMATION_MESSAGE);
                    serieManager.fillAllSerieCbx();
                    ui.getTxtCreateEpisodeTitle().setText(null);
                    ui.getTxtCreateEpisodeDuration().setText(null);
                    ui.getTxtCreateEpisodeNumber().setText(null);
                    ui.getTxtSerieCreateAge().setText(null);
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Er is iets fout gegaan tijdens het aanmaken van de serie" + newEpisode.getTitle() + ". Probeer het nog eens.", "Aflevering niet aangemaakt", JOptionPane.ERROR_MESSAGE);
                }
            }
            else{
                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Niet alle velden zijn ingevuld. Vul eerst alle velden in en probeer het dan opnieuw.", "Aflevering niet aangemaakt", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
