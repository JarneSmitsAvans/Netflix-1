package application;

import datastorage.MovieDAO;
import domain.Movie;

import java.sql.SQLException;

public class MovieManagerImpl {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }
}
