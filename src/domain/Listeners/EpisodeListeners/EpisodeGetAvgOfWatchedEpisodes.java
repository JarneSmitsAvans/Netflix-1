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
    public void actionPerformed(ActionEvent e) {
        try{
            ArrayList<Profile> profileList = getProfileManager().getProfiles();
            if(cbSelectedSerie.getItemCount() > 0){
                Episode episode = (Episode)cbSelectedSerie.getSelectedItem();
                StringBuilder sb = new StringBuilder();

                getWatchedAvgOfEpisodes(episode, profileList,sb);

                getUi().getTxtAvgWatchedEpisode().setText(sb.toString());
            }
            else{
                getUi().getTxtAvgWatchedEpisode().setText(null);
            }


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
