package domain.Listeners.EpisodeListeners;

import application.*;
import domain.Account;
import domain.Episode;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * EpisodeGetAvgOfWatchedEpisodes.java
 * This ActionListener will fill a texterea with the average watchtime of an episode that had bin watched based on the selected episode
 * Author: Marc Verwijmeren
 */

public class EpisodeGetAvgOfWatchedEpisodes extends EpisodeGetAvg implements ActionListener  {
    private JComboBox cbSelectedSerie;

    //Constructor
    public EpisodeGetAvgOfWatchedEpisodes(GUI ui, JComboBox cbSelectedSerie) {
        super(ui);
        this.cbSelectedSerie = cbSelectedSerie;
    }

    @Override
    // Get the average based on one episode
    public void actionPerformed(ActionEvent e) {
        try{
            // Create an arrayList with all te profiles
            ArrayList<Profile> profileList = getProfileManager().getProfiles();

            // Checked if the combobox isn't empty
            if(cbSelectedSerie.getItemCount() > 0){
                // Get the selected episode
                Episode episode = (Episode)cbSelectedSerie.getSelectedItem();

                // Create a stringbuilder
                StringBuilder sb = new StringBuilder();

                // Get the average of an episode
                getWatchedAvgOfEpisodes(episode, profileList,sb);

                // Set the average text in a pane
                getUi().getTxtAvgWatchedEpisode().setText(sb.toString());
            }
            else{
                // Epmty the pane
                getUi().getTxtAvgWatchedEpisode().setText(null);
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
