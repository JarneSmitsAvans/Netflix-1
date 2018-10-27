import application.*;
import com.sun.org.apache.bcel.internal.generic.NEW;
import domain.*;
import domain.Listeners.EpisodeListeners.EpisodeGetAvgOfWatchedEpisodes;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

class EpisodeTest {
    private GUI ui = new GUI();
    private SerieManagerImpl serieManager = new SerieManagerImpl(ui);
    private AccountManagerImpl accountManager = new AccountManagerImpl();
    private ProfileManagerImpl profileManager = new ProfileManagerImpl();
    private EpisodeManagerlmpl episodeManager = new EpisodeManagerlmpl(ui);
    private WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
    private EpisodeGetAvgOfWatchedEpisodes episodeGetAvgOfWatchedEpisodes = new EpisodeGetAvgOfWatchedEpisodes(ui, new JComboBox());


    @Test
    void testCreateEpisodeTrue() throws SQLException, ClassNotFoundException {

        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        serieManager.create(serie);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());

        // Set values for new episode
        Episode newEpisode = new Episode();
        newEpisode.setTitle("Pilot");
        newEpisode.setDuration(50);
        newEpisode.setEpisodeNumber(1);
        newEpisode.setSerieNumber(getSerie.getId());

        // Act
        // Create new episode
        Boolean newsEpisode = episodeManager.create(newEpisode);

        // Assert
        serieManager.delete(serie.getId());
        Assertions.assertTrue(newsEpisode);

    }

    @Test
    void testUpdateEpisodeReturnTrue()throws SQLException, ClassNotFoundException {

        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        serieManager.create(serie);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());

        // Set values for new episode
        Episode newEpisode = new Episode();
        newEpisode.setTitle("Pilot");
        newEpisode.setDuration(50);
        newEpisode.setEpisodeNumber(20);
        newEpisode.setSerieNumber(getSerie.getId());

        // Create new episode
        episodeManager.create(newEpisode);

        // Act
        Episode updateEpisode = episodeManager.getEpisodeByNameAndSerieID(newEpisode.getTitle(),newEpisode.getSerieNumber());
        updateEpisode.setTitle("new");
        updateEpisode.setDuration(40);
        updateEpisode.setEpisodeNumber(40);
        updateEpisode.setSerieNumber(2);

        Boolean updateddEpisode = episodeManager.update(updateEpisode);

        // Assert
        serieManager.delete(getSerie.getId());

        Assertions.assertTrue(updateddEpisode);

    }

    @Test
    void TestDeleteEpisodeReturnTrue()throws SQLException, ClassNotFoundException {

        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        serieManager.create(serie);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());

        // Set values for new episode
        Episode newEpisode = new Episode();
        newEpisode.setTitle("Pilot");
        newEpisode.setDuration(50);
        newEpisode.setEpisodeNumber(1);
        newEpisode.setSerieNumber(getSerie.getId());

        // Act
        // Create new episode
        episodeManager.create(newEpisode);

        // Assert
        // Delete the episode
        Episode deleteEpisode = episodeManager.getEpisodeByNameAndSerieID(newEpisode.getTitle(),newEpisode.getSerieNumber());
        Boolean deletedEpisode = episodeManager.delete(deleteEpisode.getId());

        serieManager.delete(getSerie.getId());
        Assertions.assertTrue(deletedEpisode);
    }

    @Test
    void testAvgOfWatchedEpisodesReturn75() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        boolean createdserie = serieManager.create(serie);

        // Set values for a new episode
        Episode episode = new Episode();
        episode.setTitle("Pilot");
        episode.setDuration(50);
        episode.setEpisodeNumber(1);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        episode.setSerieNumber(getSerie.getId());

        // Set values for a new episode
        Episode episode2 = new Episode();
        episode2.setTitle("Fast");
        episode2.setDuration(50);
        episode2.setEpisodeNumber(2);
        episode2.setSerieNumber(getSerie.getId());

        // Set values for a new account
        Account account = new Account();
        account.setName("Account 1");
        account.setAddress("Address 1");
        account.setResidence("residence 1");

        Account account2 = new Account();
        account2.setName("Account 2");
        account2.setAddress("Address 1");
        account2.setResidence("residence 1");

        // Create new account
        Boolean createdAccount = accountManager.create(account);
        Boolean createdAccount2 = accountManager.create(account2);
        Account getAccount = accountManager.getAccountByName(account.getName());
        Account getAccount2 = accountManager.getAccountByName(account2.getName());

        // Set values for a new profile
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dt1 = format.parse("15-05-1995");
        java.sql.Date Birth = new java.sql.Date(dt1.getTime());
        Profile profile1 = new Profile();
        profile1.setProfileName("Profile 1");
        profile1.setDateOfBirth(Birth);
        profile1.setAccountNumber(getAccount.getId());

        // Set values for a new profile
        Profile profile2 = new Profile();
        profile2.setProfileName("Profile 2");
        profile2.setDateOfBirth(Birth);
        profile2.setAccountNumber(getAccount.getId());

        // Set values for a new profile
        Profile profile3 = new Profile();
        profile3.setProfileName("Profile 3");
        profile3.setDateOfBirth(Birth);
        profile3.setAccountNumber(getAccount2.getId());

        // Set values for a new profile
        Profile profile4 = new Profile();
        profile4.setProfileName("Profile 4");
        profile4.setDateOfBirth(Birth);
        profile4.setAccountNumber(getAccount2.getId());

        // Act
        // Create episode
        episodeManager.create(episode);
        episodeManager.create(episode2);
        Episode getEpisode = episodeManager.getEpisodeByNameAndSerieID(episode.getTitle(),getSerie.getId());

        // Create a new program
        Program newWatch = getEpisode;
        newWatch.setDuration(25);
        profileManager.create(profile1);
        int profileID = profileManager.getIdOfProfile(profile1.getProfileName(),account.getName());
        Boolean createdWatch = watchBehaviourManager.create(newWatch, profileID,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch2 = getEpisode;
        newWatch2.setDuration(50);
        profileManager.create(profile2);
        int profileID2 = profileManager.getIdOfProfile(profile2.getProfileName(),account.getName());
        Boolean createdWatch2 = watchBehaviourManager.create(newWatch2, profileID2,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch3 = getEpisode;
        newWatch3.setDuration(25);
        profileManager.create(profile3);
        int profileID3 = profileManager.getIdOfProfile(profile3.getProfileName(),account2.getName());
        Boolean createdWatch3 = watchBehaviourManager.create(newWatch3, profileID3,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch4 = getEpisode;
        newWatch4.setDuration(50);
        profileManager.create(profile4);
        int profileID4 = profileManager.getIdOfProfile(profile4.getProfileName(),account2.getName());
        Boolean createdWatch4 = watchBehaviourManager.create(newWatch4, profileID4,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a ArrayList with profiles
        ArrayList<Profile> profileList = new ArrayList<>();
        profile1.setProfileID(profileID);
        profile2.setProfileID(profileID2);
        profile3.setProfileID(profileID3);
        profile4.setProfileID(profileID4);
        profileList.add(profile1);
        profileList.add(profile2);
        profileList.add(profile3);
        profileList.add(profile4);

        // Get avg of an episode
        StringBuilder sb = new StringBuilder();
        episodeGetAvgOfWatchedEpisodes.getWatchedAvgOfEpisodes(getEpisode, profileList,sb);

        // Assert
        // Delete accounts and serie
        accountManager.delete(getAccount.getId());
        accountManager.delete(getAccount2.getId());
        serieManager.delete(getSerie.getId());

        Assertions.assertEquals(75,episodeGetAvgOfWatchedEpisodes.getPercent(),"Aflevering 1: Pilot\n" +
                "Deze aflevering is in totaal 4 keer bekeken. \n" +
                "Met een gemiddelde kijktijd van 75%.");
    }


    @Test
    void testAvgOfWatchedEpisodesReturnFromAnAccount75() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        boolean createdserie = serieManager.create(serie);

        // Set values for a new episode
        Episode episode = new Episode();
        episode.setTitle("Pilot");
        episode.setDuration(50);
        episode.setEpisodeNumber(1);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        episode.setSerieNumber(getSerie.getId());

        // Set values for a new episode
        Episode episode2 = new Episode();
        episode2.setTitle("Fast");
        episode2.setDuration(50);
        episode2.setEpisodeNumber(2);
        episode2.setSerieNumber(getSerie.getId());

        // Set values for a new account
        Account account = new Account();
        account.setName("Account 1");
        account.setAddress("Address 1");
        account.setResidence("residence 1");

        Account account2 = new Account();
        account2.setName("Account 2");
        account2.setAddress("Address 1");
        account2.setResidence("residence 1");

        // Create new account
        Boolean createdAccount = accountManager.create(account);
        Boolean createdAccount2 = accountManager.create(account2);
        Account getAccount = accountManager.getAccountByName(account.getName());
        Account getAccount2 = accountManager.getAccountByName(account2.getName());

        // Set values for a new profile
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dt1 = format.parse("15-05-1995");
        java.sql.Date Birth = new java.sql.Date(dt1.getTime());
        Profile profile1 = new Profile();
        profile1.setProfileName("Profile 1");
        profile1.setDateOfBirth(Birth);
        profile1.setAccountNumber(getAccount.getId());

        // Set values for a new profile
        Profile profile2 = new Profile();
        profile2.setProfileName("Profile 2");
        profile2.setDateOfBirth(Birth);
        profile2.setAccountNumber(getAccount.getId());

        // Set values for a new profile
        Profile profile3 = new Profile();
        profile3.setProfileName("Profile 3");
        profile3.setDateOfBirth(Birth);
        profile3.setAccountNumber(getAccount2.getId());

        // Set values for a new profile
        Profile profile4 = new Profile();
        profile4.setProfileName("Profile 4");
        profile4.setDateOfBirth(Birth);
        profile4.setAccountNumber(getAccount2.getId());

        // Act
        // Create episode
        episodeManager.create(episode);
        episodeManager.create(episode2);

        Episode getEpisode = episodeManager.getEpisodeByNameAndSerieID(episode.getTitle(),getSerie.getId());
        Episode getEpisode2 = episodeManager.getEpisodeByNameAndSerieID(episode2.getTitle(),getSerie.getId());

        ArrayList<Episode> episodeArrayList = new ArrayList<Episode>();
        episodeArrayList.add(getEpisode);
        episodeArrayList.add(getEpisode2);

        // Create a new program
        Program newWatch = getEpisode;
        newWatch.setDuration(25);
        profileManager.create(profile1);
        int profileID = profileManager.getIdOfProfile(profile1.getProfileName(),account.getName());
        Boolean createdWatch = watchBehaviourManager.create(newWatch, profileID,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch2 = getEpisode;
        newWatch2.setDuration(50);
        profileManager.create(profile2);
        int profileID2 = profileManager.getIdOfProfile(profile2.getProfileName(),account.getName());
        Boolean createdWatch2 = watchBehaviourManager.create(newWatch2, profileID2,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch3 = getEpisode2;
        newWatch3.setDuration(25);
        profileManager.create(profile3);
        int profileID3 = profileManager.getIdOfProfile(profile3.getProfileName(),account2.getName());
        Boolean createdWatch3 = watchBehaviourManager.create(newWatch3, profileID3,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a new program
        Program newWatch4 = getEpisode2;
        newWatch4.setDuration(50);
        profileManager.create(profile4);
        int profileID4 = profileManager.getIdOfProfile(profile4.getProfileName(),account2.getName());
        Boolean createdWatch4 = watchBehaviourManager.create(newWatch4, profileID4,"Thu Oct 18 14:24:00 CEST 2018");

        // Create a ArrayList with profiles
        ArrayList<Profile> profileList = new ArrayList<>();
        profile1.setProfileID(profileID);
        profile2.setProfileID(profileID2);
        profile3.setProfileID(profileID3);
        profile4.setProfileID(profileID4);
        profileList.add(profile1);
        profileList.add(profile2);
        profileList.add(profile3);
        profileList.add(profile4);

        ArrayList<Profile> profileList2 = new ArrayList<>();
        for (Profile profile : profileList){
            if (profile.getAccountNumber() == getAccount.getId()){
                profileList2.add(profile);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Episode episodes : episodeArrayList){
            // Get avg of an episode
            episodeGetAvgOfWatchedEpisodes.getWatchedAvgOfEpisodes(episodes, profileList2,sb);
        }


        // Assert
        // Delete accounts and serie
        accountManager.delete(getAccount.getId());
        accountManager.delete(getAccount2.getId());
        serieManager.delete(getSerie.getId());

        Assertions.assertEquals(75,episodeGetAvgOfWatchedEpisodes.getPercent(),"Aflevering 1: Pilot\n" +
                "Deze aflevering is in totaal 4 keer bekeken. \n" +
                "Met een gemiddelde kijktijd van 75%.");
    }
}