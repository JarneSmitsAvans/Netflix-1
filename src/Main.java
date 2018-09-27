import presentation.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new GUI object
        GUI ui = new GUI(1500,900);
        SwingUtilities.invokeLater(ui);
        // Testing if the databaseconnection class works. Will remove later!
//        DatabaseConnection databaseConnection = new DatabaseConnection();
//        try {
//            databaseConnection.OpenConnection();
//            ResultSet resultSet = databaseConnection.ExecuteSelectStatement("SELECT * FROM Account");
//            while (resultSet.next())
//            {
//               String name = resultSet.getString("name");
//               System.out.println("Found an account with the name: " + name);
//            }
//            databaseConnection.CloseConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }
    }
}
