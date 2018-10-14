import datastorage.DatabaseConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.SQLException;

class DatabaseTest {

    @Test
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
