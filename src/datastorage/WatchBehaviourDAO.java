package datastorage;

import domain.Episode;
import domain.Movie;
import domain.Program;

import java.sql.PreparedStatement;
import java.sql.SQLException;

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
}
