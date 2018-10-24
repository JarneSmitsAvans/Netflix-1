import application.AccountManagerImpl;
import application.ProfileManagerImpl;
import domain.Account;
import domain.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;


/**
 * ProfileTest.java
 * This class has methods that test if a method that deals with profilelogic works as intended.
 * <p>
 * Author: Dylan ten Böhmer
 */

class ProfileTest {
    // Test if profiles can be created (and deleted)
    @Test
    void testCreateProfile() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        String dateOfBirth = "15-01-1998";
        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(1);
        // Act
        boolean created = profileManager.create(profile);
        AccountManagerImpl accountManager = new AccountManagerImpl();
        ArrayList<Account> accounts = accountManager.getAccounts();
        for (Account account : accounts) {
            if (account.getId() == 1) {
                int profileID = profileManager.getIdOfProfile(profile.getProfileName(), account.getName());
                profileManager.delete(profileID);
            }
        }
        // Assert
        Assertions.assertTrue(created);
    }

    // Test if profiles can be updated (and deleted)
    @Test
    void testUpdateProfile() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        AccountManagerImpl accountManager = new AccountManagerImpl();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        String dateOfBirth = "15-01-1998";
        java.util.Date date = simpleDateFormat.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(1);
        profileManager.create(profile);
        String newDateOfBirth = "17-02-1999";
        java.util.Date newDate = simpleDateFormat.parse(newDateOfBirth);
        java.sql.Date newConvertedDateOfBirth = new java.sql.Date(newDate.getTime());
        int profileID = 0;
        ArrayList<Account> accounts = accountManager.getAccounts();
        for (Account account : accounts) {
            if (account.getId() == 1) {
                profileID = profileManager.getIdOfProfile(profile.getProfileName(), account.getName());
            }
        }
        Profile newValuesProfile = new Profile();
        newValuesProfile.setProfileName("newProfileTest");
        newValuesProfile.setDateOfBirth(newConvertedDateOfBirth);
        // Act
        boolean updated = profileManager.update(profileID, newValuesProfile);
        profileManager.delete(profileID);
        // Assert
        Assertions.assertTrue(updated);
    }
}
