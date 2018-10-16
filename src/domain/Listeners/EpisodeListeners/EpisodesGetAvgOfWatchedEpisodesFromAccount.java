package domain.Listeners.EpisodeListeners;

import application.*;
import domain.Account;
import domain.Episode;
import domain.Profile;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class EpisodesGetAvgOfWatchedEpisodesFromAccount extends EpisodeGetAvg implements ActionListener {
    private JComboBox cbSelectedSerie;


    //Constructor
    public EpisodesGetAvgOfWatchedEpisodesFromAccount(GUI ui, JComboBox cbSelectedSerie) {
        super(ui);
        this.cbSelectedSerie = cbSelectedSerie;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // Get te selected serie
            Serie selectedSerie = (Serie)cbSelectedSerie.getSelectedItem();
            // Returned a list with all the episodes from a serie
            getEpisodeManager().setEpisodeList(selectedSerie.getId());
            ArrayList<Episode> episodeList = getEpisodeManager().getEpisode();

            String selectedAccountName = getUi().getCbAccountAvgWatchedBySerie().getSelectedItem().toString();
            Account selectedAccount = getAccountManager().getAccountByName(selectedAccountName);
            ArrayList<Profile> profileList = getProfileManager().getMatchingProfiles(selectedAccount.getId());

            StringBuilder sb = new StringBuilder();
            for (Episode episode : episodeList)
            {
                getWatchedAvgOfEpisodes(episode,profileList,sb);
            }

            getUi().getTxtAvgWatchedSeries().setText(sb.toString());


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
