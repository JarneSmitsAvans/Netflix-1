import application.MovieManagerImpl;
import datastorage.MovieDAO;
import domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;

class MovieTest {

    @Test
    // Test to create en delete movie
    void testAddAndDeleteMovie() throws SQLException, ClassNotFoundException {
        // Arrange
        MovieManagerImpl movieManager = new MovieManagerImpl();
        Movie movie = new Movie();
        movie.setTitle("12 Years A Slave");
        movie.setDuration(134);
        movie.setGenre("Drama");
        movie.setLanguage("Engels");
        movie.setMinAge(16);

        // Act
        movieManager.create(movie);
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
        ArrayList<Movie> getMoviesWithLongestDurationsAndBelow16 = movieDAO.getMovieWithLongestDurationAndAgeUnder16();
        ArrayList<Movie> getAllMovies = movieDAO.getAllMovies();
        boolean testMovie = false;

        if(!getAllMovies.isEmpty()) {
            ArrayList<Movie> getMoviesTest = new ArrayList<Movie>();
            for (Movie m : getAllMovies) {
                // Test if age is below 16
                if(m.getMinAge() < 16) {
                    getMoviesTest.add(m);
                }
            }

            Movie movie = getMoviesTest.stream().max(Comparator.comparing(v -> v.getDuration())).get();
            for( Movie movieFromList : getMoviesWithLongestDurationsAndBelow16) {
                if(movieFromList.getTitle().equals(movie.getTitle())) {
                    testMovie = true;
                }
            }
        }

        // Assert
        Assertions.assertTrue(testMovie);
    }


}