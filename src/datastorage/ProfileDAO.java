package datastorage;

import application.ProfileManagerImpl;
import domain.Profile;

import java.sql.SQLException;
import java.time.LocalDate;

public class ProfileDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean inserted = databaseConnection.ExecuteInsertStatement("INSERT INTO Profile " +
                "VALUES (" + profile.getProfileName() + "," + ProfileManagerImpl.calculateAge(profile.getDateOfBirth(), LocalDate.now()));
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(int id, Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean updated = databaseConnection.ExecuteUpdateStatement("UPDATE Profile " +
                "SET profileName = " + profile.getProfileName() + "WHERE id = " + id);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        boolean deleted = databaseConnection.ExecuteDeleteStatement("DELETE FROM Profile WHERE id = " + id);
        databaseConnection.CloseConnection();
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }
}
