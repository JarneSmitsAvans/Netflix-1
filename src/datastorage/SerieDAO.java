package datastorage;

import domain.Serie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Returns an ArrayList filled with all series in the database.
    public ArrayList<Serie> getSeries() throws SQLException, ClassNotFoundException {
        ArrayList<Serie> serieList = new ArrayList<Serie>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);

        while (resultSet.next()) {
            Serie newserie = new Serie();
            newserie.setId(resultSet.getInt("id"));
            newserie.setTitle(resultSet.getString("title"));
            newserie.setGenre(resultSet.getString("genre"));
            newserie.setLanguage(resultSet.getString("language"));
            newserie.setMinAge(resultSet.getInt("minimumage"));
            serieList.add(newserie);
        }

        databaseConnection.CloseConnection();

        return serieList;
    }

    // Returns the serie with the selected name
    public Serie getSerieByName(String name) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();
        databaseConnection.OpenConnection();
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

        databaseConnection.CloseConnection();
        return serie;
    }

    // Returns the serie with the selected id
    public Serie getSerieById(int id) throws SQLException, ClassNotFoundException {
        Serie serie = new Serie();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Serie WHERE id = ?");
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

        databaseConnection.CloseConnection();
        return serie;
    }

    // Create a new serie
    public boolean create(Serie serie) throws SQLException, ClassNotFoundException
    {
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Serie (title, genre, Language, minimumAge, RecommendedSerie) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, serie.getTitle());
        preparedStatement.setString(2, serie.getGenre());
        preparedStatement.setString(3, serie.getLanguage());
        preparedStatement.setInt(4, serie.getMinAge());
        preparedStatement.setInt(5, serie.getRecommendedSerie());

        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);

        databaseConnection.CloseConnection();
        return inserted;
    }

    // Update the selected serie
    public boolean update(Serie serie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Serie SET title = ?, genre = ?, language = ?, minimumage = ?, RecommendedSerie = ? WHERE id = ?");
        preparedStatement.setString(1, serie.getTitle());
        preparedStatement.setString(2, serie.getGenre());
        preparedStatement.setString(3, serie.getLanguage());
        preparedStatement.setInt(4, serie.getMinAge());
        preparedStatement.setInt(5, serie.getRecommendedSerie());
        preparedStatement.setInt(6, serie.getId());

        boolean updated = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();

        return updated;
    }

    // Delete the selected serie
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();

        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Serie WHERE id = ?");
        preparedStatement.setInt(1, id);

        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();

        return deleted;
    }
}
