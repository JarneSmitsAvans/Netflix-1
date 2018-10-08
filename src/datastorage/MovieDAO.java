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
        ArrayList<String> watchedMovies = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                if(resultSet.getString("Movie_Title") != null) {
                    watchedMovies.add(resultSet.getString("Movie_Title"));
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        databaseConnection.CloseConnection();
        return watchedMovies;
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

    public Movie getMovieByTitle(String title) throws SQLException, ClassNotFoundException {
        Movie movie = new Movie();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Movie where Title = ?");
        preparedStatement.setString(1, title);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            movie.setTitle(resultSet.getString("Title"));
            movie.setDuration(resultSet.getInt("Duration"));
            movie.setGenre(resultSet.getString("Genre"));
            movie.setLanguage(resultSet.getString("Language"));
            movie.setMinAge(resultSet.getInt("Minimumage"));
        }
        databaseConnection.CloseConnection();
        return movie;
    }

    public String getMovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
//    public ArrayList<Movie> getMovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        String movieWithLongestDurationAndUnder16 = "";
//        ArrayList<Movie> movieWithLongestDurationAndUnder16 = new ArrayList<Movie>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Movie WHERE Duration = (SELECT MAX(Duration) FROM Movie WHERE MinimumAge < 16)");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()){
            movieWithLongestDurationAndUnder16 = (resultSet.getString("Title"));
//            Movie movie = new Movie();
//            movie.s
//            movieWithLongestDurationAndUnder16.add(resultSet.next());
        }
        databaseConnection.CloseConnection();
        return movieWithLongestDurationAndUnder16;
    }
}
