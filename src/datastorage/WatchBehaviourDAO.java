package datastorage;

import domain.Episode;
import domain.Movie;
import domain.Program;
import domain.Serie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;

/**
 * WatchBehaviourDAO.java
 * This class creates SQL PreparedStatement for WatchBehaviour CRUD operations, and sends them to the DatabaseConnection for execution.
 * The DatabaseConnection class then returns true if it was executed successfully, false is it wasn't.
 * If a select statement was created, the requested ResultSet is then returned as the return type of that called method.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Generate the SQL Statement that will insert a new watched behaviour record.
    public boolean create(Program program, int profileId, String watchedOn) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Watched_Media" +
                " (Movie_Title, Episode_Id, TimeWatched, Profile_Id, WatchedOn) VALUES (?, ?, ?,?,?)");
        if (program.getClass().equals(new Episode().getClass())) {
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, program.getId());
        } else if (program.getClass().equals(new Movie().getClass())) {
            preparedStatement.setString(1, program.getTitle());
            preparedStatement.setString(2, null);
        }
        preparedStatement.setInt(3, program.getDuration());
        preparedStatement.setInt(4, profileId);
        preparedStatement.setString(5, watchedOn);
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    // Generate the SQL Statement that will delete existing watched behaviour.
    public boolean delete(int mediaId, int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Watched_Media WHERE Id = ? AND Profile_Id = ?");
        preparedStatement.setInt(1, mediaId);
        preparedStatement.setInt(2, profileId);
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return deleted;
    }

    /* Generate the SQL Statement that will update a watched episode
    with a new watchedDuration and watchedOn value*/
    public boolean updateWatchedEpisode(Episode episode, int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Watched_Media SET TimeWatched = ?, WatchedOn = ?  WHERE Profile_Id = ? AND Id = ?");
        preparedStatement.setInt(1, episode.getWatchedDuration());
        preparedStatement.setString(2, episode.getWatchedOn());
        preparedStatement.setInt(3, profileId);
        preparedStatement.setInt(4, episode.getId());
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return updated;
    }

    /* Generate the SQL Statement that will update a watched movie
    with a new watchedDuration and watchedOn value*/
    public boolean updateWatchedMovie(Movie movie, int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Watched_Media SET TimeWatched = ?, WatchedOn = ? WHERE Profile_Id = ? AND Id = ?");
        preparedStatement.setInt(1, movie.getWatchedDuration());
        preparedStatement.setString(2, movie.getWatchedOn());
        preparedStatement.setInt(3, profileId);
        preparedStatement.setInt(4, movie.getId());
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return updated;
    }

    // Generate the SQL Statement that returns all the watched movies of a profile.
    public ArrayList<Movie> getWatchedMovies(int profileID) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        ArrayList<Movie> movies = new ArrayList<>();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id JOIN Movie ON movie.Title = Watched_Media.Movie_Title WHERE Watched_Media.Profile_Id = ? ");
        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setId(resultSet.getInt("Id"));
            movie.setTitle(resultSet.getString("Movie_Title"));
            movie.setWatchedOn(resultSet.getString("WatchedOn"));
            movie.setWatchedDuration(resultSet.getInt("TimeWatched"));
            movie.setDuration(resultSet.getInt("Duration"));
            movies.add(movie);
        }
        databaseConnection.CloseConnection();
        return movies;
    }

    // Generate the SQL Statement that returns all the watched episodes of a profile.
    public ArrayList<Episode> getWatchedEpisodes(int profileID) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        ArrayList<Episode> episodes = new ArrayList<>();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id JOIN Episode on Episode.Id = Watched_Media.Episode_Id JOIN Serie on Serie.id = Episode.Fk_Serie WHERE Watched_Media.Profile_Id = ?");
        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Episode episode = new Episode();
            episode.setId(resultSet.getInt("Id"));
            episode.setTitle(resultSet.getString(12));
            episode.setWatchedOn(resultSet.getString("WatchedOn"));
            episode.setWatchedDuration(resultSet.getInt("TimeWatched"));
            episode.setDuration(resultSet.getInt("Duration"));
            episode.setSerieTitle(resultSet.getString(17));
            episodes.add(episode);
        }
        databaseConnection.CloseConnection();
        return episodes;
    }

    // Generate the SQL Statement that returns all the watched episodes of a profile.
    public ArrayList<Episode> getWatchedEpisodesFromAccount(int profileID, int episodeID) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        ArrayList<Episode> episodes = new ArrayList<>();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id JOIN Episode on Episode.Id = Watched_Media.Episode_Id JOIN Serie on Serie.id = Episode.Fk_Serie WHERE Watched_Media.Profile_Id = ? AND Watched_Media.Episode_Id = ?");
        preparedStatement.setInt(1, profileID);
        preparedStatement.setInt(2, episodeID);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Episode episode = new Episode();
            episode.setId(resultSet.getInt("Id"));
            episode.setTitle(resultSet.getString(10));
            episode.setWatchedDuration(resultSet.getInt("TimeWatched"));
            episode.setDuration(resultSet.getInt("Duration"));
            episodes.add(episode);
        }
        databaseConnection.CloseConnection();
        return episodes;
    }

    // Generate the SQL Statement that returns all the watched episodes of a profile.
    public HashSet<Serie> getRecommendedWatchedSerie(int profileID) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        HashSet<Serie> serieHashSet = new HashSet<Serie>();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT Serie.Title as title, RecommendedSerie FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id JOIN Episode on Episode.Id = Watched_Media.Episode_Id JOIN Serie on Serie.id = Episode.Fk_Serie WHERE Watched_Media.Profile_Id = ? group by Serie.Title, RecommendedSerie");
        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Serie serie = new Serie();
            //serie.setId(resultSet.getInt("Id"));
            serie.setTitle(resultSet.getString("title"));
            serie.setRecommendedSerie(resultSet.getInt("RecommendedSerie"));
            serieHashSet.add(serie);
        }


        databaseConnection.CloseConnection();
        return serieHashSet;
    }
}

