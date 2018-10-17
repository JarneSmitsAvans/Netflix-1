package domain.Listeners.EpisodeListeners;

import application.*;
import domain.Episode;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public abstract class EpisodeGetAvg {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    public EpisodeGetAvg(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    public void getWatchedAvgOfEpisodes(Episode episode, ArrayList<Profile> profileList, StringBuilder sb)throws SQLException, ClassNotFoundException{
        try{
            int bufferTimesWatched = 0;
            int bufferWatchTime = 0;
            if(episode != null){
                sb.append("Aflevering "+ episode.getEpisodeNumber() + ": " + episode.getTitle() + "\n");
            }
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
                sb.append("Is in totaal " + bufferTimesWatched + " Keer bekeken. \n") ;
                sb.append("Met een gemiddelde kijktijd van (" + percentViewed + "%).") ;
            }
            else{
                sb.append("Nog niemand heeft de aflevering '" + episode.getTitle() + "' gekeken" );
            }
            sb.append("\n \n");

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    public EpisodeManagerlmpl getEpisodeManager() {
        return episodeManager;
    }

    public AccountManagerImpl getAccountManager() {
        return accountManager;
    }

    public SerieManagerImpl getSerieManager() {
        return serieManager;
    }

    public WatchBehaviourManagerImpl getWatchBehaviourManager() {
        return watchBehaviourManager;
    }

    public ProfileManagerImpl getProfileManager() {
        return profileManager;
    }

    public GUI getUi() {
        return ui;
    }
}
