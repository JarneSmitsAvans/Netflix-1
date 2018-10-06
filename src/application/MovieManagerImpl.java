package application;

import datastorage.MovieDAO;
import domain.Movie;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieManagerImpl {
    private MovieDAO movieDAO = new MovieDAO();

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }

    public ArrayList<Movie> getMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> allMovies = movieDAO.getAllMovies();
        return allMovies;
    }

    public void addMoviesToComboBox(JComboBox comboBox, ArrayList<Movie> arrayList) {
        // For each Account in ArrayList, get the account name and add it to the parameter comboBox
        for (Movie movie : arrayList) {
            comboBox.addItem(movie.getTitle());
        }
    }

//    getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
//        ArrayList<Profile> arrayList = profileDAO.getMatchingProfiles(id);

//    public ArrayList<String> watchedMovieByAccountArrayList() throws SQLException, ClassNotFoundException {
//        ArrayList<String> = movieDAO.getWatchedMoviesByAccount(account);
//    }
}
