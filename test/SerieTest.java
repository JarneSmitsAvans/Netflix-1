import application.*;
import domain.*;
import domain.Listeners.EpisodeListeners.EpisodeGetAvgOfWatchedEpisodes;
import domain.Listeners.SerieListeners.SerieCreateListener;
import domain.Listeners.SerieListeners.SerieGetAvgOfSerie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;

/**
 * SerieTest.java
 * This class contains methods to test the functionality for series
 * Author: Marc Verwijmeren
 */


class SerieTest {
    private GUI ui = new GUI();
    private SerieManagerImpl serieManager = new SerieManagerImpl(ui);
    private AccountManagerImpl accountManager = new AccountManagerImpl();
    private ProfileManagerImpl profileManager = new ProfileManagerImpl();
    private EpisodeManagerlmpl episodeManager = new EpisodeManagerlmpl(ui);
    private WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
    private SerieGetAvgOfSerie serieGetAvgOfSerie = new SerieGetAvgOfSerie(ui,new JComboBox());

    @Test
    void testCreateNewSerieWithExistingSerieTitleReturningFalse()throws SQLException, ClassNotFoundException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Teen wolf");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Act
        // Create a new serie
        serieManager.create(serie);

        // Set values for a new serie
        Serie serie2 = new Serie();
        serie2.setTitle("Teen wolf");
        serie2.setGenre("Sciencefiction");
        serie2.setLanguage("Amerikaans");
        serie2.setMinAge(16);
        serie2.setRecommendedSerie(1);

        // Create a new serie
        Boolean newSerie = serieManager.create(serie);

        // Assert
        // Delete the serie
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        serieManager.delete(getSerie.getId());

        Assertions.assertFalse(newSerie,"De title van een serie moet uniek zijn");
    }
    @Test
    void testCreateNewSerieWithUniqueSerieTitleReturningFalse()throws SQLException, ClassNotFoundException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Act
        // Create a new serie
        Boolean newSerie = serieManager.create(serie);

        // Assert
        // Delete the serie
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        serieManager.delete(getSerie.getId());

        Assertions.assertTrue(newSerie);

    }


    @Test
    void TestUpdateSerieToUniqueSerieTitleReturningTrue()throws SQLException, ClassNotFoundException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("Flash");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Act
        // Create a new serie
        serieManager.create(serie);

        // Set the new values for the serie
        Serie updatedSerie = serieManager.getSerieByName(serie.getTitle());
        updatedSerie.setTitle("The Shannara Cronicles");
        updatedSerie.setGenre("Sciencefiction");
        updatedSerie.setLanguage("Amerikaans");
        updatedSerie.setMinAge(16);
        updatedSerie.setRecommendedSerie(3);

        // Update the serie
        Boolean updateSerie = serieManager.update(updatedSerie);

        // Assert
        // Delete the serie
        Serie getSerie = serieManager.getSerieByName(updatedSerie.getTitle());
        serieManager.delete(getSerie.getId());

        Assertions.assertTrue(updateSerie);

    }

    @Test
    void testUpdateNewSerieWithExistingSerieTitleReturningFalse()throws SQLException, ClassNotFoundException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("The Shannara Cronicles");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Set values for a new serie
        Serie serie2 = new Serie();
        serie2.setTitle("Flash");
        serie2.setGenre("Sciencefiction");
        serie2.setLanguage("Amerikaans");
        serie2.setMinAge(16);
        serie2.setRecommendedSerie(1);


        // Act
        // Create a new serie
        serieManager.create(serie);
        serieManager.create(serie2);

        // Set the new values for the serie
        Serie updatedSerie = serieManager.getSerieByName(serie.getTitle());
        updatedSerie.setTitle("Flash");
        updatedSerie.setGenre("Sciencefiction");
        updatedSerie.setLanguage("Amerikaans");
        updatedSerie.setMinAge(16);
        updatedSerie.setRecommendedSerie(3);

        // Update te serie
        Boolean updateSerie = serieManager.update(updatedSerie);

        // Assert
        // Delete the serie
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        serieManager.delete(getSerie.getId());

        Assertions.assertFalse(updateSerie, "Een serie titel moet uniek zijn");

    }

    @Test
    void testDeleteSerieRetrunTrue()throws SQLException, ClassNotFoundException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("The Shannara Cronicles");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Act
        // Create a new serie
        serieManager.create(serie);

        // Delete the serie
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        Boolean deletedSerie = serieManager.delete(getSerie.getId());

        // Assert
        Assertions.assertTrue(deletedSerie);
    }

    @Test
    void testRecommendedSerieReturnTheShannaraCronicles() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        // Set values for a new serie
        Serie serie = new Serie();
        serie.setTitle("The Shannara Cronicles");
        serie.setGenre("Sciencefiction");
        serie.setLanguage("Amerikaans");
        serie.setMinAge(16);
        serie.setRecommendedSerie(1);

        // Create a new serie
        boolean createdserie = serieManager.create(serie);

        // Set values for a new episode
        Episode episode = new Episode();
        episode.setTitle("Pilot");
        episode.setDuration(16);
        episode.setEpisodeNumber(1);
        Serie getSerie = serieManager.getSerieByName(serie.getTitle());
        episode.setSerieNumber(getSerie.getId());

        // Set values for a new serie
        Serie serie2 = new Serie();
        serie2.setTitle("Flash");
        serie2.setGenre("Sciencefiction");
        serie2.setLanguage("Amerikaans");
        serie2.setMinAge(16);
        serie2.setRecommendedSerie(getSerie.getId());

        // Create a new serie
        boolean createdserie2 = serieManager.create(serie2);

        // Set values for a new episode
        Episode episode2 = new Episode();
        episode2.setTitle("Pilot");
        episode2.setDuration(50);
        episode2.setEpisodeNumber(1);
        Serie getSerie2 = serieManager.getSerieByName(serie2.getTitle());
        episode2.setSerieNumber(getSerie2.getId());

        // Set values for a new account
        Account account = new Account();
        account.setName("Account 1");
        account.setAddress("Address 1");
        account.setResidence("residence 1");

        // Create new account
        Boolean createdAccount = accountManager.create(account);
        Account getAccount = accountManager.getAccountByName(account.getName());

        // Set values for a new profile
        SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date dt1 = format.parse("15-05-1995");
        java.sql.Date convertedDateOfBirth = new java.sql.Date(dt1.getTime());
        Profile profile1 = new Profile();
        profile1.setProfileName("Profile 1");
        profile1.setDateOfBirth(convertedDateOfBirth);
        profile1.setAccountNumber(getAccount.getId());

        // Act
        // Create episode
        episodeManager.create(episode);
        episodeManager.create(episode2);
        Episode getEpisode = episodeManager.getEpisodeByNameAndSerieID(episode2.getTitle(),getSerie2.getId());
        Program newWatch = getEpisode;
        newWatch.setWatchedDuration(25);

        // Create a new profiles
        profileManager.create(profile1);
        int profileID = profileManager.getIdOfProfile(profile1.getProfileName(),account.getName());

        Boolean createdWatch = watchBehaviourManager.create(newWatch, profileID,"Thu Oct 18 14:24:00 CEST 2018");

        HashSet<Serie> watchedSeries = watchBehaviourManager.getWatchedSerie(profileID);

        Serie serieRecommended = new Serie();

        if(!watchedSeries.isEmpty()){
            for(Serie watchedSerie : watchedSeries) {
                serieRecommended = serieManager.getSerieById(watchedSerie.getRecommendedSerie());
            }
        }

        // Assert
        accountManager.delete(getAccount.getId());
        serieManager.delete(getSerie.getId());
        serieManager.delete(getSerie2.getId());

        Assertions.assertEquals("The Shannara Cronicles",serieRecommended.getTitle());


    }

    @Test
    void testAvgOfWatchedSeriesReturn75() throws SQLException, ClassNotFoundException, ParseException {
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

        Episode episodedruation = episodeManager.getEpisodeBySerieID(getSerie.getId());

        System.out.println(episode.getEpisodeNumber());

        Serie avgSerie = serieManager.serieGetAvg(getSerie.getId());
        int totalPercent;
        if(avgSerie.getDuration() > 0) {
            totalPercent = (avgSerie.getWatchedDuration() * 100) / (episode.getDuration() * avgSerie.getDuration());
        }
        else{
            totalPercent = 0;
        }

        // Assert
        // Delete accounts and serie
        accountManager.delete(getAccount.getId());
        accountManager.delete(getAccount2.getId());
        serieManager.delete(getSerie.getId());

        Assertions.assertEquals(75,totalPercent,37);
    }
}
