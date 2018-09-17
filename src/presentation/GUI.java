package presentation;

import javax.swing.*;
import java.awt.*;

public class GUI {
    private JTabbedPane tabMain;
    private JPanel mainPanel;

    // To do: Make the frame responsive
    public GUI(int width, int height) {
        // New JFrame
        JFrame frame = new JFrame("Netflix Statistix");
        // Set the content pane to the mainPanel
        frame.setContentPane(mainPanel);
        // Set the preferred window size
        frame.setPreferredSize(new Dimension(width, height));
        // Set the minimum size
        frame.setMinimumSize(new Dimension(width, height));
        // Make the frame appear in the center of the screen.
        frame.setLocationRelativeTo(null);
        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Make the frame visible
        frame.setVisible(true);
    }
}
