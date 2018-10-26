import application.AccountManagerImpl;
import application.MovieManagerImpl;
import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Account;
import domain.Movie;
import domain.Profile;
import domain.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * WatchBehaviourTest.java
 * This class has methods that test if a method that deals with watchedbehaviour works as intended.
 * <p>
 * Author: Dylan ten Böhmer
 */

class WatchBehaviourTest {
    // Test if watched behaviour (movies) can be created (and deleted)
    @Test
    void testCreateWatchedProgram() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
        AccountManagerImpl accountManager = new AccountManagerImpl();
        MovieManagerImpl movieManager = new MovieManagerImpl();
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        int profileID = 0;

        // New movie
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);
        boolean createdMovie = movieManager.create(movie);
        if (!createdMovie) Assertions.assertFalse(true, "Arrange Failed: Movie could not be created.");

        // New account
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        boolean createdAccount = accountManager.create(account);
        if (!createdAccount) Assertions.assertFalse(true, "Arrange Failed: Account could not be created.");
        Account testedAccount = accountManager.getAccountByName(account.getName());

        // New profile
        String dateOfBirth = "15-01-1998";
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(testedAccount.getId());
        boolean profileCreated = profileManager.create(profile);
        if (!profileCreated) Assertions.assertFalse(true, "Arrange Failed: Profile could not be created.");

        ArrayList<Account> accounts = accountManager.getAccounts();
        for (Account acc : accounts) {
            if (acc.getId() == testedAccount.getId()) {
                profileID = profileManager.getIdOfProfile(profile.getProfileName(), acc.getName());
            }
        }
        // New program
        Program programTest = new Movie();
        programTest.setTitle("12 Years A Slave");
        programTest.setDuration(25);

        // Act
        boolean created = watchBehaviourManager.create(programTest, profileID, "Wed Oct 17 16:04:20 CEST 2018");
        movieManager.delete(movie);  // On cascade: deletes the watched behaviour too
        accountManager.delete(testedAccount.getId());
        profileManager.delete(profileID);
        // Assert
        Assertions.assertTrue(created);
    }

    // Test if watched behaviour (movies) can be updated (and deleted)
    @Test
    void testUpdateWatchedProgram() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
        MovieManagerImpl movieManager = new MovieManagerImpl();
        AccountManagerImpl accountManager = new AccountManagerImpl();
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        int profileID = 0;
        int movieID = 0;

        // New movie
        Movie movie = new Movie();
        movie.setTitle("DitIsEenTest");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);
        boolean createdMovie = movieManager.create(movie);
        if (!createdMovie) Assertions.assertFalse(true, "Arrange Failed: Movie could not be created.");

        // New account
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        boolean createdAccount = accountManager.create(account);
        if (!createdAccount) Assertions.assertFalse(true, "Arrange Failed: Account could not be created.");
        // Retrieve the ID of the created Account
        Account testedAccount = accountManager.getAccountByName(account.getName());

        // New profile
        String dateOfBirth = "15-01-1998";
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(testedAccount.getId());
        boolean profileCreated = profileManager.create(profile);
        if (!profileCreated) Assertions.assertFalse(true, "Arrange Failed: Profile could not be created.");

        // Retrieve the ID of the created Profile
        ArrayList<Account> accounts = accountManager.getAccounts();
        for (Account acc : accounts) {
            if (acc.getId() == testedAccount.getId()) {
                profileID = profileManager.getIdOfProfile(profile.getProfileName(), acc.getName());
            }
        }
        // New program
        Program programTest = new Movie();
        programTest.setTitle("DitIsEenTest");
        programTest.setDuration(25);
        boolean watchedProgram = watchBehaviourManager.create(programTest, profileID, "Wed Oct 17 16:04:20 CEST 2018");
        if (!watchedProgram) Assertions.assertFalse(true, "Arrange Failed: Watched program could not be created.");
        // Retrieve the created Movie
        ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
        for (Movie watchedMovie : watchedMovies){
            if (watchedMovie.getTitle().equals("DitIsEenTest")){
                movieID = watchedMovie.getId();
            }
        }
        // Updated program
        Movie watchedMovieUpdate = new Movie();
        watchedMovieUpdate.setWatchedDuration(40);
        watchedMovieUpdate.setWatchedOn("Wed Oct 24 16:04:25 CEST 2018");
        watchedMovieUpdate.setId(movieID);
        // Act
        boolean updated = watchBehaviourManager.updateWatchedMovie(watchedMovieUpdate, profileID);
        movieManager.delete(movie);  // On cascade: deletes the watched behaviour too
        accountManager.delete(testedAccount.getId());
        profileManager.delete(profileID);
        // Assert
        Assertions.assertTrue(updated);
    }
}
