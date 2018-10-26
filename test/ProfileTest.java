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
        AccountManagerImpl accountManager = new AccountManagerImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

        // New account
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        boolean createdAccount = accountManager.create(account);
        if (!createdAccount) Assertions.assertFalse(true);
        // Retrieve the ID of the created Account
        Account testedAccount = accountManager.getAccountByName(account.getName());
        // New profile
        String dateOfBirth = "15-01-1998";
        java.util.Date date = sdf.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(testedAccount.getId());
        // Act
        boolean created = profileManager.create(profile);
        // Assert
        Assertions.assertTrue(created, "Profile could not be created.");
        accountManager.delete(testedAccount.getId()); // On cascade: deletes the profile too
    }
    // Test if profiles can be updated (and deleted)
    @Test
    void testUpdateProfile() throws SQLException, ClassNotFoundException, ParseException {
        // Arrange
        ProfileManagerImpl profileManager = new ProfileManagerImpl();
        AccountManagerImpl accountManager = new AccountManagerImpl();
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        int profileID = 0;

        // New account
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        boolean createdAccount = accountManager.create(account);
        if (!createdAccount) Assertions.assertFalse(true);
        // Retrieve the ID of the created Account
        Account testedAccount = accountManager.getAccountByName(account.getName());

        // New profile
        String dateOfBirth = "15-01-1998";
        java.util.Date date = sdf.parse(dateOfBirth);
        java.sql.Date convertedDateOfBirth = new java.sql.Date(date.getTime());
        Profile profile = new Profile();
        profile.setProfileName("ProfileTest");
        profile.setDateOfBirth(convertedDateOfBirth);
        profile.setAccountNumber(testedAccount.getId());
        boolean createdProfile = profileManager.create(profile);
        if (!createdProfile) Assertions.assertFalse(true);

        // Retrieve the ID of the created Profile
        ArrayList<Account> accounts = accountManager.getAccounts();
        for (Account acc : accounts) {
            if (acc.getId() == testedAccount.getId()) {
                profileID = profileManager.getIdOfProfile(profile.getProfileName(), acc.getName());
            }
        }
        // Updated profile
        String newDateOfBirth = "17-02-1999";
        java.util.Date newDate = sdf.parse(newDateOfBirth);
        java.sql.Date newConvertedDateOfBirth = new java.sql.Date(newDate.getTime());
        Profile newValuesProfile = new Profile();
        newValuesProfile.setProfileName("newProfileTest");
        newValuesProfile.setDateOfBirth(newConvertedDateOfBirth);
        // Act
        boolean updated = profileManager.update(profileID, newValuesProfile);
        accountManager.delete(testedAccount.getId()); // On cascade: deletes the profile too
        // Assert
        Assertions.assertTrue(updated, "Profile could not be updated.");
    }
}
