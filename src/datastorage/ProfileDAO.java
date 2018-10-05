package datastorage;

import domain.Profile;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ProfileDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();
    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT INTO Profile (profilename, age, fk_profile) VALUES (?, ?, ?)");
        preparedStatement.setString(1, profile.getProfileName());
        preparedStatement.setDate(2, (Date) profile.getDateOfBirth());
        preparedStatement.setInt(3, profile.getAccountNumber());
        boolean inserted = databaseConnection.ExecuteInsertStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (inserted) {
            return true;
        } else {
            return false;
        }
    }

    public boolean update(String name, Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Profile SET profilename = ?, age = ? WHERE profilename = ?");
        preparedStatement.setString(1, profile.getProfileName());
        preparedStatement.setDate(2, (Date) profile.getDateOfBirth());
        preparedStatement.setString(3, name);
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(String name) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("DELETE FROM Profile WHERE profilename = ?");
        preparedStatement.setString(1, name);
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
            profile.setDateOfBirth((resultSet.getDate("age")));
            profile.setAccountNumber(resultSet.getInt("fk_profile"));
            profileArrayList.add(profile);
        }
        databaseConnection.CloseConnection();
        return profileArrayList;
    }

    public ArrayList<Profile> getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Profile> profileArrayList = new ArrayList<>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Profile JOIN Account on Account.id = Profile.fk_profile WHERE fk_profile = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Profile profile = new Profile();
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("age")));
            profile.setAccountNumber(resultSet.getInt("fk_profile"));
            profileArrayList.add(profile);
        }
        return profileArrayList;
    }

    public Profile getProfileByName(String name) throws SQLException, ClassNotFoundException {
        // Returns the data matching parameter id
        Profile profile = new Profile();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Profile WHERE profilename = ?");
        preparedStatement.setString(1, name);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("age")));
            profile.setAccountNumber(resultSet.getInt("fk_profile"));
            return profile;
        }
        databaseConnection.CloseConnection();
        return profile;
    }
}
