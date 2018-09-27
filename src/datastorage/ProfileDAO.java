package datastorage;

import application.ProfileManagerImpl;
import domain.Account;
import domain.Profile;
import sun.plugin.perf.PluginRollup;

import javax.xml.transform.Result;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    public ArrayList<Profile> getProfiles() throws SQLException, ClassNotFoundException
    {
        // Returns an ArrayList filled with all profiles in the database.
        ArrayList<Profile> profileArrayList = new ArrayList<>();
        databaseConnection.OpenConnection();
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement("SELECT * FROM Profile");
        while (resultSet.next()) {
            Profile profile = new Profile();
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("age")).toLocalDate());
            profile.setAccountNumber(resultSet.getInt("fk_profile"));
            profileArrayList.add(profile);
        }
        databaseConnection.CloseConnection();
        return profileArrayList;
    }
}
