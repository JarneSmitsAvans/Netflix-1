package domain.Listeners.SerieListeners;

import domain.Account;
import domain.Episode;
import domain.Listeners.EpisodeListeners.EpisodeGetAvg;
import domain.Profile;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * EpisodeGetAvgOfWatchedEpisodes.java
 * This ActionListener will fill a texterea with the average watchtime of the selected serie
 * Author: Marc Verwijmeren
 */

public class SerieGetAvgOfSerie extends EpisodeGetAvg implements ActionListener {
    private JComboBox cbSelectedSerie;

    //Constructor
    public SerieGetAvgOfSerie(GUI ui, JComboBox cbSelectedSerie) {
        super(ui);
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

                // Returned a list with all the episodes from a serie
                getEpisodeManager().setEpisodeList(selectedSerie.getId());
                ArrayList<Episode> episodeList = getEpisodeManager().getEpisode();

                // Create a arrayList with all the profiles
                ArrayList<Profile> profileList = getProfileManager().getProfiles();

                // Create a sringbuilder
                StringBuilder sb = new StringBuilder();

                // Set a buffer
                int bufferSerieTime = 0;

                // Set the average to 0
                setPercent(0);

                // Get the average from all the episodes
                for (Episode episode : episodeList)
                {
                    getWatchedAvgOfEpisodes(episode,profileList,sb);
                }

                // Empty the pane
                sb.setLength(0);

                // Calculate the total percent of a serie
                int totalPercent = getPercent() / episodeList.size();

                // Checked if someone has watched the serie
                if(totalPercent > 0){
                    sb.append("De gemiddelde kijktijd van de serie " + selectedSerie.getTitle() + " is:  " + totalPercent + "%.");
                }
                else{
                    sb.append("Nog niemand heeft de serie " + selectedSerie.getTitle() + " gekeken.");
                }

                // Fill the average form a serie in the pan
                getUi().getTxtGetAvgOfSerie().setText(sb.toString());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
