package datastorage;

import domain.Episode;
import domain.Serie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EpisodeDAO.java
 * This class has methods that do things with episodes, like;
 * This class creates SQL statements for episodes CRUD operations, and executes them.
 * Retuned a boolean if a create, update or delete succeeded is or not
 * * Author: Marc Verwijmeren
 */

public class EpisodeDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Returns an ArrayList with all episodes out of the database from the selected serie.
    public ArrayList<Episode> getEpisodes(int serie_id) throws SQLException, ClassNotFoundException {
        ArrayList<Episode> EpisodeList = new ArrayList<Episode>();
        // Open database connection
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Episode WHERE fk_serie = ?");

        // Bind values to the ?'s in the preparedStatement.
        preparedStatement.setInt(1, serie_id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            Episode newEpisode = new Episode();
            newEpisode.setId(resultSet.getInt("id"));
            newEpisode.setTitle(resultSet.getString("title"));
            newEpisode.setDuration(resultSet.getInt("duration"));
            newEpisode.setEpisodeNumber(resultSet.getInt("episodenumber"));
            newEpisode.setSerieNumber(resultSet.getInt("fk_serie"));
            EpisodeList.add(newEpisode);
        }

        databaseConnection.CloseConnection();
        return EpisodeList;
    }

    // Returns an ArrayList with all episodes out of the database
    public ArrayList<Episode> getAllEpisodes() throws SQLException, ClassNotFoundException {
        ArrayList<Episode> EpisodeList = new ArrayList<Episode>();
        // Open database connection
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Episode");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            Episode newEpisode = new Episode();
            newEpisode.setId(resultSet.getInt("id"));
            newEpisode.setTitle(resultSet.getString("title"));
            newEpisode.setDuration(resultSet.getInt("duration"));
            newEpisode.setEpisodeNumber(resultSet.getInt("episodenumber"));
            newEpisode.setSerieNumber(resultSet.getInt("fk_serie"));
            EpisodeList.add(newEpisode);
        }

        databaseConnection.CloseConnection();
        return EpisodeList;
    }

    // Create a new episode
    public boolean create(Episode episode) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Episode (title, episodenumber, duration, fk_serie) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, episode.getTitle());
        preparedStatement.setInt(2, episode.getEpisodeNumber());
        preparedStatement.setInt(3, episode.getDuration());
        preparedStatement.setInt(4, episode.getSerieNumber());

        // Execute insert statement
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);

        // Close database connection
        databaseConnection.CloseConnection();

        // Retuned a boolean if the episode has bin inserted
        return inserted;
    }

    public boolean update(Episode episode) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Episode SET title = ?, episodenumber = ?, duration = ? WHERE id = ?");
        preparedStatement.setString(1, episode.getTitle());
        preparedStatement.setInt(2, episode.getEpisodeNumber());
        preparedStatement.setInt(3, episode.getDuration());
        preparedStatement.setInt(4, episode.getId());

        // Execute update statement
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);

        // Close database connection
        databaseConnection.CloseConnection();

        // Retuned a boolean if the episode has bin updated
        return updated;
    }

    // Delete an episode
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ? in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("delete FROM Episode WHERE id = ?");
        preparedStatement.setInt(1,id);

        // Execute delete statement
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);

        // Close database connection
        databaseConnection.CloseConnection();

        // Retuned a boolean if the episode has bin deleted
        return deleted;
    }
}
