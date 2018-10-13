package application;

import datastorage.WatchBehaviourDAO;
import domain.Episode;
import domain.Movie;
import domain.Program;
import presentation.GUI;

import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviourManagerImpl extends GeneralManager {
    private WatchBehaviourDAO watchBehaviourDAO = new WatchBehaviourDAO();

    public void initializeWatchBehaviourComboBoxes(GUI gui) {

    }

    public boolean create(Program program, int profileId, String watchedOn) throws SQLException, ClassNotFoundException {
        boolean created = watchBehaviourDAO.create(program, profileId, watchedOn);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateWatchedEpisode(Episode episode, int profileId) throws SQLException, ClassNotFoundException {
        boolean updated = watchBehaviourDAO.updateWatchedEpisode(episode, profileId);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean updateWatchedMovie(Movie movie, int profileId) throws SQLException, ClassNotFoundException {
        boolean updated = watchBehaviourDAO.updateWatchedMovie(movie, profileId);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int mediaId, int profileId) throws SQLException, ClassNotFoundException {
        boolean deleted = watchBehaviourDAO.delete(mediaId, profileId);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Movie> getWatchedMovies(int profileID) throws SQLException, ClassNotFoundException {
        ArrayList<Movie> watchedMovies = watchBehaviourDAO.getWatchedMovies(profileID);
        return watchedMovies;
    }

    public ArrayList<Episode> getWatchedEpisodes(int profileID) throws SQLException, ClassNotFoundException {
        ArrayList<Episode> watchedEpisodes = watchBehaviourDAO.getWatchedEpisodes(profileID);
        return watchedEpisodes;
    }
}

