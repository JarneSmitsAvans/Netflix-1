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

    // CRUD
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
        // Create movie
        movieManager.create(movie);
        // Delete movie
        boolean deleteMovie = movieManager.delete(movie);

        // Assert
        Assertions.assertTrue(deleteMovie);
    }

    @Test
    //
    void testUpdateMovie() throws SQLException, ClassNotFoundException {
        // Arrange
        MovieManagerImpl movieManager = new MovieManagerImpl();
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);

        // Act
        // Create movie
        movieManager.create(movie);
        // Place new values
        Movie newMovie = new Movie();
        newMovie.setTitle("12 Years A Slave");
        newMovie.setDuration(104);
        newMovie.setGenre("Historie");
        newMovie.setLanguage("Engels");
        newMovie.setMinAge(16);
        // Update movie
        boolean updateMovie = movieManager.update(newMovie);
        // Delete movie. If we don't delete the movie here, the next test will fail.
        movieManager.delete(newMovie);

        // Assert
        Assertions.assertTrue(updateMovie);
    }

    // Overviews
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

        // Add profile
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        String dateOfBirth = "26-10-1999";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
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
        // If account is created
        if(createdAccount) {
            // Get account by name
            Account acc = accountManager.getAccountByName("Test_account");
            // Get accountId by account
            int accountId = acc.getId();
            // Set accountnumber for the profile
            profile.setAccountNumber(accountId);
            // Add profile
            boolean createdProfile = profileManager.create(profile);
            // If profile is created
            if(createdProfile) {
                // Get id of inserted profile
                int profileId = profileManager.getIdOfProfile("Test_profile", "Test_account");
                // Create movie
                movieManager.create(movie);
                // Add watched_behaviour
                boolean createdWatchedBehaviour = watchBehaviourManager.create(testProgram, profileId, "Fri Oct 26 10:36:55 CEST 2018");
                // If watched_behaviour is created
                if(createdWatchedBehaviour) {
                    MovieDAO movieDAO = new MovieDAO();
                    // Get watched movies from the function
                    getWatchedMovieTitlesByAccount = movieDAO.getWatchedMoviesByAccount(profileId);
                    // Delete account & movie. Watched_behaviour and Profils will be deleted automatically
                    // If we don't delete this values here, the next test will fail.
                    accountManager.delete(accountId);
                    movieManager.delete(movie);
                }
            }
        }

        // Assert
        // Test if the first result from the Arraylist is equal to 12 years a slave.
        Assertions.assertEquals(getWatchedMovieTitlesByAccount.get(0), "12 Years A Slave");
    }

    @Test
    // Test to check of the amount of viewers method is doing what is supposed to do
    void testAmountOfViewersByMovie() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        AccountManagerImpl accountManager = new AccountManagerImpl();
        // Add account
        Account account = new Account();
        account.setName("Test_account");
        account.setAddress("Test_address");
        account.setResidence("Test_residence");

        // Add profile
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        String dateOfBirth = "26-10-1999";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("Test_profile");
        profile.setDateOfBirth(convertedDateOfBirth);

        // Add another profile
        String dateOfBirth2 = "25-10-1999";
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date2 = sdf2.parse(dateOfBirth2);
        java.sql.Date convertedDateOfBirth2 = new java.sql.Date(date2.getTime());
        Profile profile2 = new Profile();
        profile2.setProfileName("Test_profile2");
        profile2.setDateOfBirth(convertedDateOfBirth2);

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

        // Watch another watched_behaviour
        Program testProgram2 = new Movie();
        testProgram2.setTitle("12 Years A Slave");
        testProgram2.setDuration(134);

        // Act
        // Create account
        boolean createdAccount = accountManager.create(account);
        String amountOfViewersFromDatabase = "";
        // If account is created
        if(createdAccount) {
            // Get account by accountname
            Account acc = accountManager.getAccountByName("Test_account");
            // Get accountid from account
            int accountId = acc.getId();
            // Set accountnumber for both profiles
            profile.setAccountNumber(accountId);
            profile2.setAccountNumber(accountId);
            // Create first profile
            boolean createdProfile = profileManager.create(profile);
            // If first profile is created
            if(createdProfile) {
                // Create seconds profile
                profileManager.create(profile2);
                // Get id's from profiles
                int profileId1 = profileManager.getIdOfProfile("Test_profile", "Test_account");
                int profileId2 = profileManager.getIdOfProfile("Test_profile2", "Test_account");
                // Create movie
                movieManager.create(movie);
                // Add first watched_Behaviour
                boolean createdWatchedBehaviour = watchBehaviourManager.create(testProgram, profileId1, "Fri Oct 26 10:36:55 CEST 2018");
                // If first watched_behaviour is created
                if(createdWatchedBehaviour) {
                    // Create seconds watched_behaviour
                    watchBehaviourManager.create(testProgram2, profileId2, "Fri Oct 26 10:36:55 CEST 2018");
                    MovieDAO movieDAO = new MovieDAO();
                    // Get amount of viewers from method
                    amountOfViewersFromDatabase = movieDAO.getAmountOfViewers(movie);
                    // Delete account & movie. Watched_behaviour and Profils will be deleted automatically
                    // If we don't delete this values here, the next test will fail.
                    accountManager.delete(accountId);
                    movieManager.delete(movie);
                }
            }
        }

        // Assert
        // Test if the output from the method is equal to 2
        Assertions.assertEquals(amountOfViewersFromDatabase, "2");
    }

    @Test
    void testAmountOfViewersWhoWatchedWholeMovie() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        AccountManagerImpl accountManager = new AccountManagerImpl();
        // Add account
        Account account = new Account();
        account.setName("Test_account");
        account.setAddress("Test_address");
        account.setResidence("Test_residence");

        // Add profile
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        String dateOfBirth = "26-10-1999";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("Test_profile");
        profile.setDateOfBirth(convertedDateOfBirth);

        // Add another profile
        String dateOfBirth2 = "25-10-1999";
        SimpleDateFormat sdf2 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date2 = sdf2.parse(dateOfBirth2);
        java.sql.Date convertedDateOfBirth2 = new java.sql.Date(date2.getTime());
        Profile profile2 = new Profile();
        profile2.setProfileName("Test_profile2");
        profile2.setDateOfBirth(convertedDateOfBirth2);

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

        // Add another watched_behaviour
        Program testProgram2 = new Movie();
        testProgram2.setTitle("12 Years A Slave");
        testProgram2.setDuration(134);

        // Act
        // Create account
        boolean createdAccount = accountManager.create(account);
        String amountOfViewersFromDatabase = "";
        // If account is created
        if(createdAccount) {
            // Get account by accountname
            Account acc = accountManager.getAccountByName("Test_account");
            // Get accountid by account
            int accountId = acc.getId();
            // Set accountnumbers for profiles
            profile.setAccountNumber(accountId);
            profile2.setAccountNumber(accountId);
            // Create first profile
            boolean createdProfile = profileManager.create(profile);
            // If first profile is created
            if(createdProfile) {
                // Create seconds profile
                profileManager.create(profile2);
                // Get profile Id's
                int profileId1 = profileManager.getIdOfProfile("Test_profile", "Test_account");
                int profileId2 = profileManager.getIdOfProfile("Test_profile2", "Test_account");
                // Create movie
                movieManager.create(movie);
                // Add first watched_behaviour
                boolean createdWatchedBehaviour = watchBehaviourManager.create(testProgram, profileId1, "Fri Oct 26 10:36:55 CEST 2018");
                // If first watched_behaviour is added
                if(createdWatchedBehaviour) {
                    // Add second watched_behaviour
                    watchBehaviourManager.create(testProgram2, profileId2, "Fri Oct 26 10:36:55 CEST 2018");
                    MovieDAO movieDAO = new MovieDAO();
                    // Get output of method
                    amountOfViewersFromDatabase = movieDAO.getViewersByMovie(movie.getTitle());
                    /// Delete account & movie. Watched_behaviour and Profils will be deleted automatically
                    // If we don't delete this values here, the next test will fail.
                    accountManager.delete(accountId);
                    movieManager.delete(movie);
                }
            }
        }

        // Assert
        // Check if output from method is equal to what is supposed to be.
        Assertions.assertEquals(amountOfViewersFromDatabase, "12 Years A Slave\n" +
                "De film is 2x bekeken.\n" +
                "1 van de 2 (50.0%) kijkers hebben de film '12 Years A Slave' volledig bekeken.");
    }
}