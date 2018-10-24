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
        boolean checker = false;
        AccountDAO accountDAO = new AccountDAO();
        ProfileDAO profileDAO = new ProfileDAO();
        // Act
        ArrayList<String> AccountArayList = accountDAO.getSingleAccounts();
        if (!AccountArayList.isEmpty()) {
            for (String account : AccountArayList) {
                Account acc = accountDAO.getAccountByName(account);
                ArrayList<Profile> profileArrayList = profileDAO.getMatchingProfiles(acc.getId());
                if (profileArrayList.size() == 1) {
                    checker = true;
                } else {
                    checker = false;
                }
            }
        }
        // Assert
        Assertions.assertTrue(checker);
    }
}
