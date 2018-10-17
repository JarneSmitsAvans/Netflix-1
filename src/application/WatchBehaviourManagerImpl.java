package application;

import datastorage.WatchBehaviourDAO;
import domain.Episode;
import domain.Movie;
import domain.Program;
import domain.Serie;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * WatchBehaviourManagerImpl.java
 * <p>
 * This class has methods that do things with watched behaviour, like;
 * * Implementation for CRUD Operations
 * * Getting data for overviews
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourManagerImpl extends GeneralManager {
    // Initialize a new watchBehaviourDAO object.
    private WatchBehaviourDAO watchBehaviourDAO = new WatchBehaviourDAO();

    /* Sends an object of type Program to the WatchBehaviour DAO that needs to be inserted into the database.
    along with the profile that watched the program and the date and time when it was watched.*/
    public boolean create(Program program, int profileId, String watchedOn) throws SQLException, ClassNotFoundException {
        boolean created = watchBehaviourDAO.create(program, profileId, watchedOn);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

    /* Sends an object of type Episode which has a new watchedDuration and watchedOn value
    to the WatchBehaviour DAO that needs to be updated in the database. */
    public boolean updateWatchedEpisode(Episode episode, int profileId) throws SQLException, ClassNotFoundException {
        boolean updated = watchBehaviourDAO.updateWatchedEpisode(episode, profileId);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    /* Sends an object of type Movie which has a new watchedDuration and watchedOn value
       to the WatchBehaviour DAO that needs to be updated in the database. */
    public boolean updateWatchedMovie(Movie movie, int profileId) throws SQLException, ClassNotFoundException {
        boolean updated = watchBehaviourDAO.updateWatchedMovie(movie, profileId);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    // Sends an id of watched media to the WatchBehaviour DAO that needs to be deleted from the database.
    public boolean delete(int mediaId, int profileId) throws SQLException, ClassNotFoundException {
        boolean deleted = watchBehaviourDAO.delete(mediaId, profileId);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList of all the watched movies of a profile.
    public ArrayList<Movie> getWatchedMovies(int profileID) throws SQLException, ClassNotFoundException {
        ArrayList<Movie> watchedMovies = watchBehaviourDAO.getWatchedMovies(profileID);
        return watchedMovies;
    }

    // Returns an ArrayList of all the watched episodes of a profile.
    public ArrayList<Episode> getWatchedEpisodes(int profileID) throws SQLException, ClassNotFoundException {
        ArrayList<Episode> watchedEpisodes = watchBehaviourDAO.getWatchedEpisodes(profileID);
        return watchedEpisodes;
    }

    // Returns an ArrayList of all the watched episodes of a profile.
    public Serie getLastWatchedSerie (int profileID) throws SQLException, ClassNotFoundException {
        Serie lastWatchedSerie = watchBehaviourDAO.getRecommendedLastWatchedSerie(profileID);
        return lastWatchedSerie;
    }

    // Returns an ArrayList of all the watched episodes from a serie and account.
    public ArrayList<Episode> getWatchedEpisodesFromAccount(int profileID, int episodeID) throws SQLException, ClassNotFoundException {
        ArrayList<Episode> watchedEpisodes = watchBehaviourDAO.getWatchedEpisodesFromAccount(profileID,episodeID);
        return watchedEpisodes;
    }
}

