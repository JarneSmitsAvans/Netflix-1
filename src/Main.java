import presentation.GUI;

import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        // Create a new GUI object
        GUI ui = new GUI(1600,1000);
        SwingUtilities.invokeLater(ui);
    }
}
