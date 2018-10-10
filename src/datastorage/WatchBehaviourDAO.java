package datastorage;

import domain.Episode;
import domain.Movie;
import domain.Program;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviourDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean create(Program program, int profileId) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT into Watched_Media" +
                " (Movie_Title, Episode_Id, TimeWatched, Profile_Id) VALUES (?, ?, ?,?)");
        if (program.getClass().equals(new Episode().getClass())) {
            preparedStatement.setString(1, null);
            preparedStatement.setInt(2, program.getId());
        } else if (program.getClass().equals(new Movie().getClass())) {
            preparedStatement.setString(1, program.getTitle());
            preparedStatement.setString(2, null);
        }
        preparedStatement.setInt(3, program.getDuration());
        preparedStatement.setInt(4, profileId);
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    public ArrayList<Program> getWatchedMedia(int profileID) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        ArrayList<Program> programs = new ArrayList<>();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement(
                "SELECT * FROM Watched_Media JOIN Profile ON Profile.Id = Watched_Media.Profile_Id WHERE Watched_Media.Profile_Id = ?");
        preparedStatement.setInt(1, profileID);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            if (resultSet.getString(2) != "null" && resultSet.getInt(3) == 0) {
                Movie movie = new Movie();
                movie.setTitle(resultSet.getString("Movie_Title"));
                programs.add(movie);
            } else {
                Episode episode = new Episode();
                episode.setId(resultSet.getInt("Episode_Id"));
                programs.add(episode);
            }
        }
        databaseConnection.CloseConnection();
        return programs;
    }
}

