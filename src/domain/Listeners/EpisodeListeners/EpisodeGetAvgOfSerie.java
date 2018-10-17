package domain.Listeners.EpisodeListeners;

import domain.Account;
import domain.Episode;
import domain.Profile;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EpisodeGetAvgOfSerie extends EpisodeGetAvg implements ActionListener {
    private JComboBox cbSelectedSerie;

    //Constructor
    public EpisodeGetAvgOfSerie(GUI ui, JComboBox cbSelectedSerie) {
        super(ui);
        this.cbSelectedSerie = cbSelectedSerie;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            if(cbSelectedSerie.getSelectedItem() != "Selecteer serie" && cbSelectedSerie.getSelectedItem() != null){
                // Get te selected serie
                Serie selectedSerie = (Serie)cbSelectedSerie.getSelectedItem();
                // Returned a list with all the episodes from a serie
                getEpisodeManager().setEpisodeList(selectedSerie.getId());
                ArrayList<Episode> episodeList = getEpisodeManager().getEpisode();

                ArrayList<Profile> profileList = getProfileManager().getProfiles();

                StringBuilder sb = new StringBuilder();
                for (Episode episode : episodeList)
                {
                    getWatchedAvgOfEpisodes(episode,profileList,sb);
                }

                getUi().getTxtGetAvgOfSerie().setText(sb.toString());
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
