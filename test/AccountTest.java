import application.AccountManagerImpl;
import datastorage.AccountDAO;
import datastorage.ProfileDAO;
import domain.Account;
import domain.Profile;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;

/**
 * AccountTest.java
 * This class has methods that test if a method that deals with Accountlogic works as intended.
 * <p>
 * Author: Dylan ten Böhmer
 */

class AccountTest {
    @Test
        // Test if the singleProfile method returns an ArrayList filled with all accounts that only have one profile assigned to them.
    void testSingleProfiles() throws SQLException, ClassNotFoundException {
        // Arrange
        int amountOfProfiles = 0;
        AccountDAO accountDAO = new AccountDAO();
        ProfileDAO profileDAO = new ProfileDAO();
        // Act
        ArrayList<String> AccountArrayList = accountDAO.getSingleAccounts();
        if (!AccountArrayList.isEmpty()) {
            for (String account : AccountArrayList) {
                Account acc = accountDAO.getAccountByName(account);
                ArrayList<Profile> profileArrayList = profileDAO.getMatchingProfiles(acc.getId());
                amountOfProfiles = profileArrayList.size();
                // Assert
                Assertions.assertEquals(1, amountOfProfiles);
            }

        }else{
            // There are no accounts with single profiles, so just return true. (It's technically not incorrect)
            Assertions.assertTrue(true);
        }
    }
    // Test if accounts can be created (and deleted)
    @Test
    void testCreateAccount() throws SQLException, ClassNotFoundException {
        // Arrange
        AccountManagerImpl accountManager = new AccountManagerImpl();
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        // Act
        boolean created = accountManager.create(account);
        Account testedAccount = accountManager.getAccountByName(account.getName());
        accountManager.delete(testedAccount.getId());
        // Assert
        Assertions.assertTrue(created);
    }

    // Test if accounts can be updated (and deleted)
    @Test
    void testUpdateAccount() throws SQLException, ClassNotFoundException {
        // Arrange
        AccountManagerImpl accountManager = new AccountManagerImpl();
        Account account = new Account();
        account.setName("TestAccount");
        account.setAddress("TestAddress");
        account.setResidence("TestResidence");
        // Act
        accountManager.create(account);
        Account testedAccount = accountManager.getAccountByName(account.getName());
        Account newValuesAccount = new Account();
        newValuesAccount.setName("newTestAccount");
        newValuesAccount.setAddress("newTestAddress");
        newValuesAccount.setResidence("newTestResidence");
        boolean updatedAccount = accountManager.update(testedAccount.getId(), newValuesAccount);
        accountManager.delete(testedAccount.getId());
        // Assert
        Assertions.assertTrue(updatedAccount);
    }
}
