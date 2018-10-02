package datastorage;

import application.ProfileManagerImpl;
import domain.Profile;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

public class ProfileDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT INTO Profile (profilename, age, fk_profile) VALUES (?, ?, ?)");
        preparedStatement.setString(1, profile.getProfileName());
        preparedStatement.setDate(2, new java.sql.Date(ProfileManagerImpl.calculateAge(profile.getDateOfBirth(), LocalDate.now())));
        preparedStatement.setInt(3, profile.getAccountNumber());
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(int id, Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Profile SET profilename = ? WHERE id = ?");
        preparedStatement.setString(1, profile.getProfileName());
        preparedStatement.setInt(2, id);
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Profile WHERE id = ?");
        preparedStatement.setInt(1, id);
        boolean deleted = databaseConnection.ExecuteDeleteStatement(preparedStatement);
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
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Profile");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
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
