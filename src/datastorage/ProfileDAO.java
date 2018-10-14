package datastorage;

import domain.Profile;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ProfileDAO.java
 * This class creates SQL PreparedStatement for Profile CRUD operations, and sends them to the DatabaseConnection for execution.
 * The DatabaseConnection class then returns true if it was executed successfully, false is it wasn't.
 * If a select statement was created, the requested ResultSet is then returned as the return type of that called method.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileDAO {
    private DatabaseConnection databaseConnection = new DatabaseConnection();

    // Generate the SQL Statement that will create a new Profile.
    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("INSERT INTO Profile (profilename, DateOfBirth, fk_account) VALUES (?, ?, ?)");
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

    // Generate the SQL Statement that will update an existing profile.
    public boolean update(int id, Profile profile) throws SQLException, ClassNotFoundException {
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("UPDATE Profile SET profilename = ?, DateOfBirth = ? WHERE id = ?");
        preparedStatement.setString(1, profile.getProfileName());
        preparedStatement.setDate(2, (Date) profile.getDateOfBirth());
        preparedStatement.setInt(3, id);
        boolean updated = databaseConnection.ExecuteUpdateStatement(preparedStatement);
        databaseConnection.CloseConnection();
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    // Generate the SQL Statement that will delete an existing profile.
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

    // Generate the SQL Statement that will return all profiles in the database.
    public ArrayList<Profile> getProfiles() throws SQLException, ClassNotFoundException {
        ArrayList<Profile> profileArrayList = new ArrayList<>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Profile");
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Profile profile = new Profile();
            profile.setProfileID(resultSet.getInt("id"));
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("DateOfBirth")));
            profile.setAccountNumber(resultSet.getInt("fk_account"));
            profileArrayList.add(profile);
        }
        databaseConnection.CloseConnection();
        return profileArrayList;
    }

    // Generate the SQL Statement that will return the profiles matching the account id.
    public ArrayList<Profile> getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Profile> profileArrayList = new ArrayList<>();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * from Profile JOIN Account on Account.id = Profile.fk_account WHERE fk_account = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            Profile profile = new Profile();
            profile.setProfileID(resultSet.getInt("id"));
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("DateOfBirth")));
            profile.setAccountNumber(resultSet.getInt("fk_account"));
            profileArrayList.add(profile);
        }
        return profileArrayList;
    }

    // Generate the SQL Statement that will return the id of the profile that matches the parameter profile and account.
    public int getIdOfProfile(String profile, String account) throws SQLException, ClassNotFoundException {
        int profileId = 0;
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT Profile.id FROM Profile JOIN Account ON Account.id = Profile.fk_account WHERE Profile.profilename = ? AND Account.name = ?");
        preparedStatement.setString(1, profile);
        preparedStatement.setString(2, account);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            profileId = resultSet.getInt("id");
            return profileId;
        }
        return profileId;
    }

    // Generate the SQL Statement that will return the requested profile that matches the parameter id.
    public Profile getProfileById(int id) throws SQLException, ClassNotFoundException {
        Profile profile = new Profile();
        databaseConnection.OpenConnection();
        PreparedStatement preparedStatement = databaseConnection.getConnection().prepareStatement("SELECT * FROM Profile WHERE id = ?");
        preparedStatement.setInt(1, id);
        ResultSet resultSet = databaseConnection.ExecuteSelectStatement(preparedStatement);
        while (resultSet.next()) {
            profile.setProfileID(resultSet.getInt("id"));
            profile.setProfileName(resultSet.getString("profilename"));
            profile.setDateOfBirth((resultSet.getDate("DateOfBirth")));
            profile.setAccountNumber(resultSet.getInt("fk_account"));
            return profile;
        }
        databaseConnection.CloseConnection();
        return profile;
    }
}
