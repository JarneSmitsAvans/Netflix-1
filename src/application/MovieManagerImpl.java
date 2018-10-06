package application;

import datastorage.MovieDAO;
import domain.Movie;

import java.sql.SQLException;
import java.util.ArrayList;

public class MovieManagerImpl {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }

//    getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
//        ArrayList<Profile> arrayList = profileDAO.getMatchingProfiles(id);

//    public ArrayList<String> watchedMovieByAccountArrayList() throws SQLException, ClassNotFoundException {
//        ArrayList<String> = movieDAO.getWatchedMoviesByAccount(account);
//    }
}
