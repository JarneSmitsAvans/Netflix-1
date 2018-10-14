import datastorage.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * DatabaseTest.java
 * This class has methods that test if the database class works as intended.
 * <p>
 * Author: Dylan ten Böhmer
 */

class DatabaseTest {

    @Test
        // Test if a DatabaseConnection is possible.
    void testDatabaseConnection() throws SQLException, ClassNotFoundException {
        // Arrange
        boolean hasConnection;
        DatabaseConnection databaseConnection = new DatabaseConnection();

        // Act
        databaseConnection.OpenConnection();
        Connection connected = databaseConnection.getConnection();
        if (connected != null) {
            hasConnection = true;
        } else {
            hasConnection = false;
        }
        // Assert
        Assertions.assertTrue(hasConnection);
    }
}
