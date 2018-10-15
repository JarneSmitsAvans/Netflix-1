import application.MovieManagerImpl;
import domain.Movie;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;

class MovieTest {

    @Test
    public void testDeleteMovie() throws SQLException, ClassNotFoundException {
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
}