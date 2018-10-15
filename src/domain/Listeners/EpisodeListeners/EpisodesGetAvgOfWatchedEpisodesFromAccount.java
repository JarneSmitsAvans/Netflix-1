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

public class EpisodesGetAvgOfWatchedEpisodesFromAccount implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedSerie;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    //Constructor
    public EpisodesGetAvgOfWatchedEpisodesFromAccount(GUI ui, JComboBox cbSelectedSerie) {
        this.ui = ui;
        this.cbSelectedSerie = cbSelectedSerie;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            // Get te selected serie
            Serie selectedSerie = (Serie)cbSelectedSerie.getSelectedItem();
            // Returned a list with all the episodes from a serie
            episodeManager.setEpisodeList(selectedSerie.getId());
            ArrayList<Episode> episodeList = episodeManager.getEpisode();

            String selectedAccountName = ui.getCbAccountAvgWatchedBySerie().getSelectedItem().toString();
            Account selectedAccount = accountManager.getAccountByName(selectedAccountName);
            ArrayList<Profile> profileList = profileManager.getMatchingProfiles(selectedAccount.getId());

            StringBuilder sb = new StringBuilder();

            for (Episode episode : episodeList)
            {
                int bufferTimesWatched = 0;
                int bufferWatchTime = 0;
                sb.append(episode.getTitle() + "\n");
                for (Profile profile : profileList)
                {
                    ArrayList<Episode> watchedEpisode = watchBehaviourManager.getWatchedEpisodesFromAccount(profile.getProfileID(),episode.getId());
                    if(watchedEpisode != null)
                    {
                        for (Episode watchedEpisodeFromProfile : watchedEpisode)
                        {
                            bufferWatchTime += watchedEpisodeFromProfile.getWatchedDuration();
                            bufferTimesWatched++;
                        }
                    }
                }
                if(bufferTimesWatched > 0 ){
                    int percentViewed = (bufferWatchTime * 100) / (episode.getDuration() * bufferTimesWatched);
                    sb.append("Is in totaal " + bufferTimesWatched + " Keer bekeken. Met een gemiddelde van " + percentViewed + "% bekeken is.") ;
                }
                else{
                    sb.append("Heeft nog niemand bekeken");
                }
                sb.append("\n \n");
            }


            ui.getTxtAvgWatchedSeries().setText(sb.toString());
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
