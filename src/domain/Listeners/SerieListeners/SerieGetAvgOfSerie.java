package domain.Listeners.SerieListeners;

import application.*;
import domain.Account;
import domain.Episode;
import domain.Listeners.EpisodeListeners.EpisodeGetAvg;
import domain.Profile;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EpisodeGetAvgOfWatchedEpisodes.java
 * This ActionListener will fill a texterea with the average watchtime of the selected serie
 * Author: Marc Verwijmeren
 */

public class SerieGetAvgOfSerie implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private int percent;
    private int watchamount;
    private JComboBox cbSelectedSerie;
    private int totalPercent;
    private int totaalEpisodes;

    // Constructor
    public SerieGetAvgOfSerie(GUI ui,JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.cbSelectedSerie = cbSelectedSerie;

    }

    @Override
    // Get the average for al whole serie
    public void actionPerformed(ActionEvent e) {
        try{
            // Check if the combobox isn't empty
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null && cbSelectedSerie.getItemCount() > 0){
                // Get te selected serie
                Serie selectedSerie = (Serie)cbSelectedSerie.getSelectedItem();

                // Get the all te duractions from episodes from the selected serie
                Episode episode = episodeManager.getEpisodeBySerieID(selectedSerie.getId());

                // Get all te watchedDuratins from the selected serie
                Serie avgSerie = serieManager.serieGetAvg(selectedSerie.getId());

                // Check if someone has watched the serie
                if(avgSerie.getDuration() > 0) {
                    percent = (avgSerie.getWatchedDuration() * 100) / (episode.getDuration() * avgSerie.getDuration());
                }
                else{
                    percent = 0;
                }

                // Create a stringbuilder
                StringBuilder sb = new StringBuilder();

                // Checked if someone has watched the serie with the average
                if(percent > 0){
                    sb.append("De gemiddelde kijktijd van de serie " + selectedSerie.getTitle() + " is: " + percent + "%.");
                }
                else{
                    sb.append("Nog niemand heeft de serie " + selectedSerie.getTitle() + " gekeken.");
                }

                // Fill the average form a serie in the pane
                ui.getTxtGetAvgOfSerie().setText(sb.toString());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
   }
}
