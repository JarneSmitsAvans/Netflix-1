package datastorage;

import domain.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Movie (title, duration, genre, language, minimumAge) VALUES (?, ?, ?, ?, ?)");
        preparedStatement.setString(1, movie.getTitle());
        preparedStatement.setInt(2, movie.getDuration());
        preparedStatement.setString(3, movie.getGenre());
        preparedStatement.setString(4, movie.getLanguage());
        preparedStatement.setInt(5, movie.getMinAge());
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return inserted;
    }

    public ArrayList<String> getWatchedMoviesByAccount() throws SQLException, ClassNotFoundException
    {
        ArrayList<String> watchedMovieByAccountArrayList = new ArrayList<String>();
//        databaseConnection.OpenConnection();
//        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT Account.name FROM Account JOIN Profile ON Profile.fk_account = Account.id GROUP BY Account.name HAVING COUNT(*) = 1");
//        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
//        while (resultSet.next()){
//            String account = (resultSet.getString("name"));
//            accountArrayList.add(account);
//        }
//        databaseConnection.CloseConnection();
        return watchedMovieByAccountArrayList;
    }
}
