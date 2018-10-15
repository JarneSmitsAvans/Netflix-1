package datastorage;

import domain.Movie;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class MovieDAO {
    // Get databaseconnection
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Create new Movie
    public boolean create(Movie movie) throws SQLException, ClassNotFoundException {
        // Open database connection
        databaseConnection.OpenConnection();
        // Check if there already exists a movie with the same title
        PreparedStatement preparedStatementTitle = databaseConnection.getConnection().prepareStatement("SELECT * FROM Movie WHERE title = ?");
        preparedStatementTitle.setString(1, movie.getTitle());
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatementTitle);
        // If there is a movie with the same title, return false. Movie can not be added.
        while (resultSet.next()) {
            return false;
        }

        // If there is not a movie with the same title, make a insert statement.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Movie (title, duration, genre, language, minimumAge) VALUES (?, ?, ?, ?, ?)");
        // Bind values to the ?'s in the preparedStatement.
        preparedStatement.setString(1, movie.getTitle());
        preparedStatement.setInt(2, movie.getDuration());
        preparedStatement.setString(3, movie.getGenre());
        preparedStatement.setString(4, movie.getLanguage());
        preparedStatement.setInt(5, movie.getMinAge());
        // Execute insert statement
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        // Close database connection
        databaseConnection.CloseConnection();
        // Return true or false. Depends of the movie is inserted or not.
        return inserted;
    }

    // Update existing Movie
    public boolean update(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        // Update selected Movie.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Movie SET duration = ?, genre = ?, language = ?, minimumAge = ? WHERE title = ?");
        preparedStatement.setInt(1, movie.getDuration());
        preparedStatement.setString(2, movie.getGenre());
        preparedStatement.setString(3, movie.getLanguage());
        preparedStatement.setInt(4, movie.getMinAge());
        preparedStatement.setString(5, movie.getTitle());
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        // Return true or false. Depends of the movie is updated or not.
        return updated;
    }

    // Delete existing movie
    public boolean delete(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        // Delete selected Movie
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Movie WHERE title = ?");
        preparedStatement.setString(1, movie.getTitle());
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
        databaseConnection.CloseConnection();
        // Return true or false. Depends of the movie is deleted or not.
        return deleted;
    }

    // Get watched Movies by profileId
    public ArrayList<String> getWatchedMoviesByAccount(int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        // Select movie title where profile_Id is parameter profileId.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT Watched_Media.Movie_Title FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id WHERE Watched_Media.Profile_Id = ?");
        preparedStatement.setInt(1, profileId);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        ArrayList<String> watchedMovies = new ArrayList<String>();
        try {
            while (resultSet.next()) {
                // If the movie_title is not null (because the profile has not watched an episode instead of a movie), add the title to the ArrayList.
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

    // Returns an ArrayList filled with all movies from the database.
    public ArrayList<Movie> getAllMovies() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movieArrayList = new ArrayList<Movie>();
        databaseConnection.OpenConnection();
        // Statement to get all the movies
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Movie");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("Title"));
            movie.setDuration(resultSet.getInt("Duration"));
            movie.setGenre(resultSet.getString("Genre"));
            movie.setLanguage(resultSet.getString("Language"));
            movie.setMinAge(resultSet.getInt("Minimumage"));
            // Add movie to the ArrayList
            movieArrayList.add(movie);
        }
        databaseConnection.CloseConnection();
        return movieArrayList;
    }

    // Return ArrayList with the movie(s) whose duration is the longest and the minimum age is below 16.
    public ArrayList<Movie> getMovieWithLongestDurationAndAgeUnder16() throws SQLException, ClassNotFoundException {
        ArrayList<Movie> movieWithLongestDurationAndUnder16 = new ArrayList<Movie>();
        databaseConnection.OpenConnection();
        // Select all movies where minimum age is < 16. And select from this movies the movies with the longest duration (MAX)
        // If there are two movies below 16 and the duration is the same, they will both be returned.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Movie WHERE Duration = (SELECT MAX(Duration) FROM Movie WHERE MinimumAge < 16)");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()){
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("Title"));
            movie.setDuration(resultSet.getInt("Duration"));
            movie.setGenre(resultSet.getString("Genre"));
            movie.setLanguage(resultSet.getString("Language"));
            movie.setMinAge(resultSet.getInt("Minimumage"));
            // Add movie to ArrayList.
            movieWithLongestDurationAndUnder16.add(movie);
        }
        databaseConnection.CloseConnection();
        return movieWithLongestDurationAndUnder16;
    }

    // Returns String which contains the amount of viewers.
    public String getAmountOfViewers(Movie movie) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        // Count records from the table Watched_Media where the movieTitle is the title of the selected movie.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT count(*) AS viewers from Watched_Media where Movie_Title = ?");
        preparedStatement.setString(1, movie.getTitle());
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        int amount = 0;
        while (resultSet.next()) {
            // Get amount of viewers
            amount = resultSet.getInt("viewers");
        }
        // Convert int to string
        String strAmount = Integer.toString(amount);
        databaseConnection.CloseConnection();
        return strAmount;
    }

    // Return String which contains the amount of viewer who watched the whole movie (%)
    public String getViewersByMovie(String movieTitle) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        // Select everything from the table Watched_Media where the Movie_title is the selected movie title.
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Watched_Media where Movie_Title = ?");
        preparedStatement.setString(1, movieTitle);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        // Get movie by movieTitle
        Movie DataOfMovie = getMovie(movieTitle);
        // Get duration of movie
        int durationOfMovie = DataOfMovie.getDuration();
        int watchedWholeMovie = 0;
        int watchedMovie = 0;

        String strViewers = "";
        // If the movies has not been watched return this.
        if(!resultSet.isBeforeFirst()) {
            strViewers = "De film '" + movieTitle + "' is nog niet bekeken";
        } else {
            while (resultSet.next()) {
                // Add one value to the amount of viewers.
                watchedMovie++;
                // If the watched duration of the viewer is equal to the duration of the movie, the viewer watched the whole movie.
                if(resultSet.getInt("TimeWatched") == durationOfMovie) {
                    // Add one value to the amount of viewers who watched the whole movie.
                    watchedWholeMovie++;
                }
            }
            // Percentage between people who watched the whole movie and the people who watched the movie (whole of a part).
            float percentage = (float)((watchedWholeMovie*100) / watchedMovie);
            String amountOfViewerWhoWatchedWholeMovie = "";
            // If the movie has been watched more than once call viewers: 'kijkers'.
            if(watchedMovie > 1) {
                amountOfViewerWhoWatchedWholeMovie = Integer.toString(watchedWholeMovie) + " van de " + watchedMovie + " (" + percentage + "%) kijkers hebben de film ";
            }
            // If the movie has been watched one time call viewer: 'kijker'.
            else {
                amountOfViewerWhoWatchedWholeMovie = Integer.toString(watchedWholeMovie) + " van de " + watchedMovie + " (" + percentage + "%) kijker heeft de film ";
            }
            strViewers = movieTitle + "\nDe film is " + watchedMovie + "x bekeken.\n" + amountOfViewerWhoWatchedWholeMovie + "'" + movieTitle + "' volledig bekeken.";
        }

        databaseConnection.CloseConnection();
        return strViewers;
    }

    // Get Movie by movieTitle.
    public Movie getMovie(String title) throws SQLException, ClassNotFoundException {
        Movie movie = new Movie();
        databaseConnection.OpenConnection();
        // Select the movie where the title is the parameter title.
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
