import application.AccountManagerImpl;
import application.MovieManagerImpl;
import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import datastorage.MovieDAO;
import domain.Account;
import domain.Movie;
import domain.Profile;
import domain.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;

class MovieTest {

    @Test
    // Test to create en delete movie
    void testAddAndDeleteMovie() throws SQLException, ClassNotFoundException {
        // Arrange
        MovieManagerImpl movieManager = new MovieManagerImpl();
        // Create movie
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);

        // Act
        movieManager.create(movie);
        // Delete movie
        boolean deleteMovie = movieManager.delete(movie);

        // Assert
        Assertions.assertTrue(deleteMovie);
    }

    @Test
    // Test if the returned movie is the movie with the longest duration and is below 16 years
    void testGetMovieWithLongestDurationBelow16Years() throws SQLException, ClassNotFoundException {
        // Arrange
        MovieDAO movieDAO = new MovieDAO();

        // Act
        // Get result from method
        ArrayList<Movie> getMoviesWithLongestDurationsAndBelow16 = movieDAO.getMovieWithLongestDurationAndAgeUnder16();
        // Fill arraylist with all movies from the whole database
        ArrayList<Movie> getAllMovies = movieDAO.getAllMovies();
        boolean testMovie = false;

        // Check if there are movies in the database.
        if(!getAllMovies.isEmpty()) {
            ArrayList<Movie> allMoviesWithAgeBelow16 = new ArrayList<Movie>();
            // Foreach movie from the database, check if the minimumage is below 16.
            for (Movie m : getAllMovies) {
                // Test if age is below 16 and if so, add movie to another arraylist.
                if(m.getMinAge() < 16) {
                    allMoviesWithAgeBelow16.add(m);
                }
            }

            // Get the movie from the arraylist with the highest Duration.
            Movie movie = allMoviesWithAgeBelow16.stream().max(Comparator.comparing(v -> v.getDuration())).get();
            for( Movie movieFromList : getMoviesWithLongestDurationsAndBelow16) {
                // If result from method is equal to result from Comparator testMovie is true
                if(movieFromList.getTitle().equals(movie.getTitle())) {
                    testMovie = true;
                }
            }
        }

        // Assert
        Assertions.assertTrue(testMovie);
    }

    @Test
    void testGetMoviesWatchedByAccount() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        AccountManagerImpl accountManager = new AccountManagerImpl();
        // Add account
        Account account = new Account();
        account.setName("Test_account");
        account.setAddress("Test_address");
        account.setResidence("Test_residence");

        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        String dateOfBirth = "15-01-1998";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        // Add profile
        Profile profile = new Profile();
        profile.setProfileName("Test_profile");
        profile.setDateOfBirth(convertedDateOfBirth);

        // Add movie
        MovieManagerImpl movieManager = new MovieManagerImpl();
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);

        // Add watched_behaviour
        WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
        Program testProgram = new Movie();
        testProgram.setTitle("12 Years A Slave");
        testProgram.setDuration(130);

        // Act
        boolean createdAccount = accountManager.create(account);
        ArrayList<String> getWatchedMovieTitlesByAccount = new ArrayList<String>();
        if(createdAccount) {
            Account acc = accountManager.getAccountByName("Test_account");
            int accountId = acc.getId();
            profile.setAccountNumber(accountId);
            boolean createdProfile = profileManager.create(profile);
            if(createdProfile) {
                int profileId = profileManager.getIdOfProfile("Test_profile", "Test_account");
                movieManager.create(movie);
                boolean createdWatchedBehaviour = watchBehaviourManager.create(testProgram, profileId, "Fri Oct 26 10:36:55 CEST 2018");
                if(createdWatchedBehaviour) {
                    MovieDAO movieDAO = new MovieDAO();
                    getWatchedMovieTitlesByAccount = movieDAO.getWatchedMoviesByAccount(profileId);
                    accountManager.delete(accountId);
                    movieManager.delete(movie);
                }
            }
        }

        // Assert
        Assertions.assertEquals(getWatchedMovieTitlesByAccount.get(0), "12 Years A Slave");
    }


}