package domain.Listeners.EpisodeListeners;

import application.*;
import domain.Episode;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EpisodeGetAllInformationListener.java
 * This abstract class has generic variables that every averageListener uses in Netflix application
 * Has a function to show the average watchtime of episodes and series.
 * Author: Marc Verwijmeren
 */

public abstract class EpisodeGetAvg {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private int percent;

    // Constructor
    public EpisodeGetAvg(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    // Show the average of the watched episode
    public void getWatchedAvgOfEpisodes(Episode episode, ArrayList<Profile> profileList, StringBuilder sb)throws SQLException, ClassNotFoundException{
        try{
            // Creat buffers
            int bufferTimesWatched = 0;
            int bufferWatchTime = 0;

            // Checked if there is an episode
            if(episode != null){
                sb.append("Aflevering "+ episode.getEpisodeNumber() + ": " + episode.getTitle() + "\n");
            }

            // Get all the watcheddurations from profiles from a episode
            for (Profile profile : profileList)
            {
                // Creates an arryList with al watched data based on the profile and episode
                ArrayList<Episode> watchedEpisode = watchBehaviourManager.getWatchedEpisodesFromAccount(profile.getProfileID(),episode.getId());

                // Checked if there are watched data
                if(watchedEpisode != null)
                {
                    // For every watched episode count the watchedTime and how many times it had bin watched
                    for (Episode watchedEpisodeFromProfile : watchedEpisode)
                    {
                        bufferWatchTime += watchedEpisodeFromProfile.getWatchedDuration();
                        bufferTimesWatched++;
                    }
                }
            }
            // Checked if someone did watched the episode
            if(bufferTimesWatched > 0 ){

                // She the average of the episode
                int percentViewed = (bufferWatchTime * 100) / (episode.getDuration() * bufferTimesWatched);
                sb.append("Deze aflevering is in totaal " + bufferTimesWatched + " keer bekeken. \n") ;
                sb.append("Met een gemiddelde kijktijd van " + percentViewed + "%.") ;
                percent += percentViewed;
            }
            else{
                // Show a massage that no one had watched the episode
                sb.append("Nog niemand heeft de aflevering '" + episode.getTitle() + "' gekeken" );
            }
            sb.append("\n \n");

        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    // Get the episode manager
    public EpisodeManagerlmpl getEpisodeManager() {
        return episodeManager;
    }

    // Get the episode manager
    public AccountManagerImpl getAccountManager() {
        return accountManager;
    }

    // Get the serie manager
    public SerieManagerImpl getSerieManager() {
        return serieManager;
    }

    // Get the watchbehaviour manager
    public WatchBehaviourManagerImpl getWatchBehaviourManager() {
        return watchBehaviourManager;
    }

    // Get the profile manager
    public ProfileManagerImpl getProfileManager() {
        return profileManager;
    }

    // Get the gui
    public GUI getUi() {
        return ui;
    }

    // Get the average of an episode
    public int getPercent() {
        return percent;
    }

    // Set the episode average of an episode
    public void setPercent(int percent) {
        this.percent = percent;
    }
}
