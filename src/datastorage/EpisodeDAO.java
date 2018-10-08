package datastorage;

import domain.Episode;
import domain.Serie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    public ArrayList<Episode> getEpisodes(int id) throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all episodes in the database.
        ArrayList<Episode> EpisodeList = new ArrayList<Episode>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Episode WHERE fk_serie = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            Episode newEpisode = new Episode();
            newEpisode.setId(resultSet.getInt("id"));
            newEpisode.setTitle(resultSet.getString("title"));
            newEpisode.setDuration(resultSet.getInt("duration"));
            newEpisode.setEpisodeNumber(resultSet.getInt("episodenumber"));
           //newEpisode.setReferenceNumber(resultSet.getInt("referencenumber"));
            newEpisode.setSerieNumber(resultSet.getInt("fk_serie"));
            EpisodeList.add(newEpisode);
        }

        databaseConnection.CloseConnection();
        return EpisodeList;
    }
    public ArrayList<Episode> getAllEpisodes() throws SQLException, ClassNotFoundException {
        // Returns all episodes out of the db
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
            //newEpisode.setReferenceNumber(resultSet.getInt("referencenumber"));
            newEpisode.setSerieNumber(resultSet.getInt("fk_serie"));
            EpisodeList.add(newEpisode);
        }
        databaseConnection.CloseConnection();
        return EpisodeList;
    }
}
