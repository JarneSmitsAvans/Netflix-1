package datastorage;

import domain.Serie;

import javax.swing.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SerieDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public ArrayList<Serie> getSeries() throws SQLException, ClassNotFoundException {
            // Returns an ArrayList filled with all series in the database.
            ArrayList<Serie> accountArrayList = new ArrayList<Serie>();
            databaseConnection.OpenConnection();
            ResultSet resultSet = databaseConnection.ExecuteSelectStatement("SELECT * FROM Serie");

            while (resultSet.next()) {
                Serie newserie = new Serie();
                newserie.setTitle(resultSet.getString("title"));
                newserie.setGenre(resultSet.getString("genre"));
                newserie.setLanguage(resultSet.getString("language"));
                newserie.setMinAge(resultSet.getInt("minimumage"));
                accountArrayList.add(newserie);

            }
           databaseConnection.CloseConnection();

            return accountArrayList;
    }
}
