package application;

import datastorage.MovieDAO;
import datastorage.ProfileDAO;
import domain.Movie;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * MovieManagerImpl.java
 * This class has methods that do things with movies, like;
 * * CRUD Operations
 * * Getting data for overviews
 * * Adding movies to comboboxes.
 * Author: Kim van den Berg
 */

public class MovieManagerImpl {
    // Initialize a new MovieDAO & ProfileDAO object
    private MovieDAO movieDAO = new MovieDAO();
    private ProfileDAO profileDAO = new ProfileDAO();

    // Fill comboboxes and standard overviews with data.
    public void initializeMovieComponents(GUI gui) throws SQLException, ClassNotFoundException {
        // Create ArrayList with the movie(s) with the longest duration and is below 16.
        // This is an ArrayList, because there can be multiple movies with the same longest duration.
        ArrayList<Movie> moviesWithLongestDurationAndAgeIsBelow16 = this.MovieWithLongestDurationAndAgeUnder16();
        String longestMovie = "";
        for(Movie m : moviesWithLongestDurationAndAgeIsBelow16) {
            longestMovie += m;
        }
        gui.getTxtLongestDurationOfMovieBelow16().setText(longestMovie);

        // Remove all items from the comboboxes.
        // This is useful when there has added, edited or deleted a movie. Otherwise you will get duplicated movies in the combobox.
        gui.getCbAmountOfViewsOfMovie().removeAllItems();
        gui.getCbUpdateMovie().removeAllItems();
        gui.getCbDeleteMovie().removeAllItems();
        gui.getCbAmountOfViewerWholeMovie().removeAllItems();

        // Fill comboboxes with the titles of the movies.
        ArrayList<Movie> movieArraylist = this.getMovies();
        this.addMoviesToComboBox(gui.getCbAmountOfViewsOfMovie(), movieArraylist);
        this.addMoviesToComboBox(gui.getCbUpdateMovie(), movieArraylist);
        this.addMoviesToComboBox(gui.getCbDeleteMovie(), movieArraylist);
        this.addMoviesToComboBox(gui.getCbAmountOfViewerWholeMovie(), movieArraylist);

        // Do not select the first item from the combobox, but select a white/empty item
        gui.getCbAmountOfViewsOfMovie().setSelectedIndex(-1);
        gui.getCbUpdateMovie().setSelectedIndex(-1);
        gui.getCbDeleteMovie().setSelectedIndex(-1);
        gui.getCbAmountOfViewerWholeMovie().setSelectedIndex(-1);

        // Make all fields from movie empty
        gui.getTxtAmountOfViewersWholeMovie().setText("");
        gui.getTxtAmountOfViewersForMovie().setText("");
        gui.getTxtUpdateMovieTitle().setText("");
        gui.getTxtUpdateMovieDuration().setText("");
        gui.getTxtUpdateMovieGenre().setText("");
        gui.getTxtUpdateMovieLanguage().setText("");
        gui.getTxtUpdateMovieMinimumAge().setText("");
    }

    // Call movieDAO function 'create' with the parameter movie from the datatype Movie
    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieCreated = movieDAO.create(movie);
        return movieCreated;
    }

    // Call movieDAO function 'update' with the parameter movie from the datatype Movie
    public boolean update(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieUpdated = movieDAO.update(movie);
        return movieUpdated;
    }

    // Call movieDAO function 'delete' with the parameter movie from the datatype Movie.
    public boolean delete(Movie movie) throws SQLException, ClassNotFoundException {
        boolean movieDeleted = movieDAO.delete(movie);
        return movieDeleted;
    }

    // Call movieDAO function 'getAllMovies' who will store all the movies from the database in the ArrayList<Movie> allMovies.
    public ArrayList<Movie> getMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> allMovies = movieDAO.getAllMovies();
        return allMovies;
    }

    // For each Movie in ArrayList, get the title and add it to the parameter comboBox
    public void addMoviesToComboBox(JComboBox comboBox, ArrayList<Movie> arrayList) {
        for (Movie movie : arrayList) {
            comboBox.addItem(movie.getTitle());
        }
    }

    // Get watched movies by Account id.
    public ArrayList<String> watchedMovieByAccountArrayList(int id) throws SQLException, ClassNotFoundException {
        int accountId = id;
        // Place all profiles which belong to the selected Account in ArrayList.
        ArrayList<Profile> profileList = profileDAO.getMatchingProfiles(accountId);
        ArrayList<String> movieList = new ArrayList<String>();
        // For each Profile in profileList, fill an ArrayList with the titles of the watched movies by this profile.
        for(Profile p : profileList) {
            ArrayList<String> watchedMoviesByProfileList = movieDAO.getWatchedMoviesByAccount(p.getProfileID());
            // For each watched movie add this value to the ArrayList movieList.
            for(String movie : watchedMoviesByProfileList) {
                movieList.add(movie);
            }
        }
        return movieList;
    }

    // Fill ArrayList with the movie(s) with the longest duration and minimum age is below 16
    public ArrayList<Movie> MovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movieWithLongestDurationAndUnder16 = movieDAO.getMovieWithLongestDurationAndAgeUnder16();
        return movieWithLongestDurationAndUnder16;
    }

    // Return String with amount of viewers by selected movie
    public String amountOfViewers(Movie movie) throws SQLException, ClassNotFoundException {
        String amountOfViewers = movieDAO.getAmountOfViewers(movie);
        return amountOfViewers;
    }

    // Return String with amount of viewers by selected movie who watched the movie completely.
    public String viewersByMovie(String movieTitle) throws SQLException, ClassNotFoundException {
        String amountOfViewersByMovie = movieDAO.getViewersByMovie(movieTitle);
        return amountOfViewersByMovie;
    }

    // Get Movie by movie Title and return this movie.
    public Movie getMovieByTitle(String title) throws SQLException, ClassNotFoundException {
        Movie movie = movieDAO.getMovie(title);
        return movie;
    }
}
