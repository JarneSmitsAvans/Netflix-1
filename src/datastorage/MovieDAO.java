package datastorage;

import domain.Movie;
import domain.Profile;

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

    public ArrayList<String> getWatchedMoviesByAccount(int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT Watched_Media.Movie_Title FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id WHERE Watched_Media.Profile_Id = ?");
        preparedStatement.setInt(1, profileId);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        ArrayList<String> movieTitles = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                movieTitles.add(resultSet.getString("Movie_Title"));
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        databaseConnection.CloseConnection();
        return movieTitles;
    }

    public ArrayList<Movie> getAllMovies() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all movies in the database.
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Movie");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("Title"));
            movie.setDuration(resultSet.getInt("Duration"));
            movie.setGenre(resultSet.getString("Genre"));
            movie.setLanguage(resultSet.getString("Language"));
            movie.setMinAge(resultSet.getInt("Minimumage"));
            movieArrayList.add(movie);
        }
        databaseConnection.CloseConnection();
        return movieArrayList;

    }
}
