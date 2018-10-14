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
        PreparedStatement preparedStatementTitle = databaseConnection.getConnection().prepareStatement("SELECT * FROM Movie WHERE title = ?");
        preparedStatementTitle.setString(1, movie.getTitle());
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatementTitle);
        while (resultSet.next()) {
            return false;
        }

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

    public boolean update(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Movie SET duration = ?, genre = ?, language = ?, minimumAge = ? WHERE title = ?");
        preparedStatement.setInt(1, movie.getDuration());
        preparedStatement.setString(2, movie.getGenre());
        preparedStatement.setString(3, movie.getLanguage());
        preparedStatement.setInt(4, movie.getMinAge());
        preparedStatement.setString(5, movie.getTitle());
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return updated;
    }

    public boolean delete(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Movie WHERE title = ?");
        preparedStatement.setString(1, movie.getTitle());
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();
        return deleted;
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

    public ArrayList<Movie> getMovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movieWithLongestDurationAndUnder16 = new ArrayList<Movie>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Movie WHERE Duration = (SELECT MAX(Duration) FROM Movie WHERE MinimumAge < 16)");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()){
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("Title"));
            movie.setDuration(resultSet.getInt("Duration"));
            movie.setGenre(resultSet.getString("Genre"));
            movie.setLanguage(resultSet.getString("Language"));
            movie.setMinAge(resultSet.getInt("Minimumage"));
            movieWithLongestDurationAndUnder16.add(movie);
        }
        databaseConnection.CloseConnection();
        return movieWithLongestDurationAndUnder16;
    }

    public String getAmountOfViewers(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT count(*) AS viewers from Watched_Media where Movie_Title = ?");
        preparedStatement.setString(1, movie.getTitle());
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        int amount = 0;
        while (resultSet.next()) {
            amount = resultSet.getInt("viewers");
        }
        String strAmount = Integer.toString(amount);
        databaseConnection.CloseConnection();
        return strAmount;
    }

    public String getViewersByMovie(String movieTitle) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Watched_Media where Movie_Title = ?");
        preparedStatement.setString(1, movieTitle);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        Movie DataOfMovie = getMovieId(movieTitle);
        int durationOfMovie = DataOfMovie.getDuration();
        int watchedWholeMovie = 0;
        int watchedMovie = 0;

        String strViewers = "";
        if(!resultSet.isBeforeFirst()) {
            strViewers = "De film '" + movieTitle + "' is nog niet bekeken";
        } else {
            while (resultSet.next()) {
                watchedMovie++;
                if(resultSet.getInt("TimeWatched") == durationOfMovie) {
                    watchedWholeMovie++;
                }
            }
            float percentage = (float)((watchedWholeMovie*100) / watchedMovie);
            String amountOfViewerWhoWatchedWholeMovie = "";
            if(watchedMovie > 1) {
                amountOfViewerWhoWatchedWholeMovie = Integer.toString(watchedWholeMovie) + " van de " + watchedMovie + " kijkers (" + percentage + "%) hebben de film ";
            }
            else {
                amountOfViewerWhoWatchedWholeMovie = Integer.toString(watchedWholeMovie) + " van de " + watchedMovie + " (" + percentage + "%) kijker heeft de film ";
            }
            strViewers = movieTitle + "\nDe film is " + watchedMovie + "x bekeken.\n" + amountOfViewerWhoWatchedWholeMovie + "'" + movieTitle + "' volledig bekeken.";
        }

        databaseConnection.CloseConnection();
        return strViewers;
    }

    public Movie getMovieId(String title) throws SQLException, ClassNotFoundException {
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
}
