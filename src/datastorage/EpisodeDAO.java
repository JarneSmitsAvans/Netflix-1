package datastorage;

import domain.Episode;
import domain.Serie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Returns an ArrayList with all episodes out of the database from the selected serie.
    public ArrayList<Episode> getEpisodes(int serie_id) throws SQLException, ClassNotFoundException {
        ArrayList<Episode> EpisodeList = new ArrayList<Episode>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Episode WHERE fk_serie = ?");
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
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Episode (title, episodenumber, duration, fk_serie) VALUES (?, ?, ?, ?)");
        preparedStatement.setString(1, episode.getTitle());
        preparedStatement.setInt(2, episode.getEpisodenumber());
        preparedStatement.setInt(3, episode.getDuration());
        preparedStatement.setInt(4, episode.getSerieNumber());

        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);

        databaseConnection.CloseConnection();
        return inserted;
    }

    public boolean update(Episode episode) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Episode SET title = ?, episodenumber = ?, duration = ? WHERE id = ?");
        preparedStatement.setString(1, episode.getTitle());
        preparedStatement.setInt(2, episode.getEpisodenumber());
        preparedStatement.setInt(3, episode.getDuration());
        preparedStatement.setInt(4, episode.getId());

        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);

        databaseConnection.CloseConnection();
        return inserted;
    }

    // Delete an episode
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("delete FROM Episode WHERE id = ?");
        preparedStatement.setInt(1,id);

        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);

        databaseConnection.CloseConnection();
        return inserted;
    }
}
