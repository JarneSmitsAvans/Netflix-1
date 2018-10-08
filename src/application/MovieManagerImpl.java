package application;

import datastorage.MovieDAO;
import datastorage.ProfileDAO;
import domain.Movie;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieManagerImpl {
    private MovieDAO movieDAO = new MovieDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    public void initializeMovieComponents(GUI gui) throws SQLException, ClassNotFoundException {
        gui.getTxtLongestDurationOfMovieBelow16().setText(this.MovieWithLongestDurationAndAgeUnder16());
    }

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }

    public ArrayList<Movie> getMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> allMovies = movieDAO.getAllMovies();
        return allMovies;
    }

    public void addMoviesToComboBox(JComboBox comboBox, ArrayList<Movie> arrayList) {
        // For each Movie in ArrayList, get the title and add it to the parameter comboBox
        for (Movie movie : arrayList) {
            comboBox.addItem(movie.getTitle());
        }
    }

    public ArrayList<String> watchedMovieByAccountArrayList(int id) throws SQLException, ClassNotFoundException {
        int accountId = id;
        ArrayList<Profile> profileList = profileDAO.getMatchingProfiles(accountId);
        ArrayList<String> movieList = new ArrayList<String>();
        for(Profile p : profileList) {
            ArrayList<String> watchedMoviesByProfileList = movieDAO.getWatchedMoviesByAccount(p.getProfileID());
            for(String movie : watchedMoviesByProfileList) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    public String MovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        String movieWithLongestDurationAndUnder16 = movieDAO.getMovieWithLongestDurationAndAgeUnder16();
        return movieWithLongestDurationAndUnder16;
    }
}
