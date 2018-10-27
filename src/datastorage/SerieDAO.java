package datastorage;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * SerieDAO.java
 * This class has methods that do things with series, like;
 * This class creates SQL statements for series CRUD operations, and executes them.
 * Retuned a boolean if a create, update or delete succeeded is or not
 * * Author: Marc Verwijmeren
 */

public class SerieDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    private GUI ui;

    // Returns an ArrayList filled with all series in the database.
    public ArrayList<Serie> getSeries() throws SQLException, ClassNotFoundException {
        ArrayList<Serie> serieList = new ArrayList<Serie>();

        // Open database connection
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            Serie serie = new Serie();
            serie.setId(resultSet.getInt("id"));
            serie.setTitle(resultSet.getString("title"));
            serie.setGenre(resultSet.getString("genre"));
            serie.setLanguage(resultSet.getString("language"));
            serie.setMinAge(resultSet.getInt("minimumage"));
            serie.setRecommendedSerie(resultSet.getInt("RecommendedSerie"));
            serieList.add(serie);
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a arrayList with series
        return serieList;
    }

    // Returns the serie with the selected name
    public Serie getSerieByName(String name) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();

        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie WHERE Title = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            serie.setId(resultSet.getInt("id"));
            serie.setTitle(resultSet.getString("title"));
            serie.setGenre(resultSet.getString("genre"));
            serie.setLanguage(resultSet.getString("language"));
            serie.setMinAge(resultSet.getInt("minimumage"));
            serie.setRecommendedSerie(resultSet.getInt("RecommendedSerie"));

        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a serie
        return serie;
    }

    // Returns the serie with the selected id
    public Serie getSerieById(int id) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();
        // Open database connection
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie WHERE id = ?");

        // Bind values to the ?'s in the preparedStatement.
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            serie.setId(resultSet.getInt("id"));
            serie.setTitle(resultSet.getString("title"));
            serie.setGenre(resultSet.getString("genre"));
            serie.setLanguage(resultSet.getString("language"));
            serie.setMinAge(resultSet.getInt("minimumage"));
            serie.setRecommendedSerie(resultSet.getInt("RecommendedSerie"));
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a serie
        return serie;
    }

    // Get the data form watched series based on the serieID
    public Serie getSerieByIdForAvg(int id) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();
        // Open database connection
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT  count(DISTINCT ProfileName) as profiles, sum(Watched_Media.TimeWatched) as watched FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id inner JOIN Episode on Episode.Id = Watched_Media.Episode_Id JOIN Serie on Serie.id = Episode.Fk_Serie WHERE Serie.id = ? group by Serie.Id");

        // Bind values to the ?'s in the preparedStatement.
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            serie.setDuration(resultSet.getInt("profiles"));
            serie.setWatchedDuration(resultSet.getInt("watched"));
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a serie
        return serie;
    }

    // Create a new serie
    public boolean create(Serie serie) throws SQLException, ClassNotFoundException{
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Serie (title, genre, Language, minimumAge, RecommendedSerie) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, serie.getTitle());
        preparedStatement.setString(2, serie.getGenre());
        preparedStatement.setString(3, serie.getLanguage());
        preparedStatement.setInt(4, serie.getMinAge());
        preparedStatement.setInt(5, serie.getRecommendedSerie());

        // Checked if the serie title already exists.
        boolean inserted = false;
        String st = getSerieByName(serie.getTitle()).getTitle();
        if(st == null) {
            inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a boolean if the episode has bin inserted
        return inserted;
    }

    // Update the selected serie
    public boolean update(Serie serie) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Serie SET title = ?, genre = ?, language = ?, minimumage = ?, RecommendedSerie = ? WHERE id = ?");
        preparedStatement.setString(1, serie.getTitle());
        preparedStatement.setString(2, serie.getGenre());
        preparedStatement.setString(3, serie.getLanguage());
        preparedStatement.setInt(4, serie.getMinAge());
        preparedStatement.setInt(5, serie.getRecommendedSerie());
        preparedStatement.setInt(6, serie.getId());

        // Checked if the serie already exists
        boolean updated = false;
        Serie getSerie = getSerieByName(serie.getTitle());
        Serie checkSerie = checkSerieTitle(getSerie.getTitle(), serie.getId());
        if (checkSerie.getTitle() == null) {
            // Update the serie
            updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a boolean if the episode has bin updated
        return updated;
    }

    // Delete the selected serie
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Serie WHERE id = ?");

        // Bind values to the ?'s in the preparedStatement.
        preparedStatement.setInt(1, id);

        // Delete the serie
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a boolean if the episode has bin deleted
        return deleted;
    }

    // Checks if there is already a serie with this title
    public Serie checkSerieTitle(String serieTitle, int serieId) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();
        // Open database connection
        databaseConnection.OpenConnection();

        // Bind values to the ?'s in the preparedStatement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie WHERE id != ? AND title = ?");
        preparedStatement.setInt(1, serieId);
        preparedStatement.setString(2, serieTitle);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement (preparedStatement);

        while (resultSet.next()) {
            serie.setId(resultSet.getInt("id"));
            serie.setTitle(resultSet.getString("title"));
            serie.setGenre(resultSet.getString("genre"));
            serie.setLanguage(resultSet.getString("language"));
            serie.setMinAge(resultSet.getInt("minimumage"));
            serie.setRecommendedSerie(resultSet.getInt("RecommendedSerie"));
        }

        // Close database connection
        databaseConnection.CloseConnection();

        // Returns a serie
        return serie;
    }
}
