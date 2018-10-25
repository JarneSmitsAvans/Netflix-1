import application.MovieManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Movie;
import domain.Program;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
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
    void testCreateWatchedProgram() throws SQLException, ClassNotFoundException {
        // Arrange
        WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
        MovieManagerImpl movieManager = new MovieManagerImpl();
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);
        movieManager.create(movie);
        Program programTest = new Movie();
        programTest.setTitle("12 Years A Slave");
        programTest.setDuration(25);
        // Act
        boolean created = watchBehaviourManager.create(programTest, 1, "Wed Oct 17 16:04:20 CEST 2018");
        movieManager.delete(movie);  // Referential integrity deletes the watched behaviour too.
        // Assert
        Assertions.assertTrue(created);
    }
    // Test if watched behaviour (movies) can be updated (and deleted)
    @Test
    void testUpdateWatchedProgram() throws SQLException, ClassNotFoundException{
        // Arrange
        WatchBehaviourManagerImpl watchBehaviourManager = new WatchBehaviourManagerImpl();
        MovieManagerImpl movieManager = new MovieManagerImpl();
        int movieID = 0;
        Movie movie = new Movie();
        movie.setTitle("DitIsEenTest");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);
        movieManager.create(movie);
        Program programTest = new Movie();
        programTest.setTitle("DitIsEenTest");
        programTest.setDuration(25);
        watchBehaviourManager.create(programTest, 1, "Wed Oct 17 16:04:20 CEST 2018");

        ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(1);
        for (Movie watchedMovie : watchedMovies){
            if (watchedMovie.getTitle().equals("DitIsEenTest")){
                movieID = watchedMovie.getId();
            }
        }
        Movie watchedMovieUpdate = new Movie();
        watchedMovieUpdate.setWatchedDuration(40);
        watchedMovieUpdate.setWatchedOn("Wed Oct 24 16:04:25 CEST 2018");
        watchedMovieUpdate.setId(movieID);
        // Act
        boolean updated = watchBehaviourManager.updateWatchedMovie(watchedMovieUpdate, 1);
        movieManager.delete(movie); // Referential integrity deletes the watched behaviour too.
        // Assert
        Assertions.assertTrue(updated);
    }
}
