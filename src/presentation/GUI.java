package presentation;

import javax.swing.*;
import java.awt.*;

public class GUI {
    // Tab Panes
    private JTabbedPane tabMain;
    private JTabbedPane tabCRUD;
    // Labels
    private JLabel lblMadeBy;
    private JLabel lblAppName;
    private JLabel lblOv6SeenMovie;
    private JLabel lblOv1Serie;
    private JLabel lblOv2Account;
    private JLabel lblOv2Serie;
    private JLabel lblOv3Account;
    // Panels
    private JPanel mainPanel;
    private JPanel ov1;
    private JPanel ov2;
    private JPanel ov3;
    private JPanel ov4;
    private JPanel ov5;
    private JPanel ov6;
    private JPanel AccountCrudPanel;
    private JPanel ProfileCrudPanel;
    private JPanel ProgramCrudPanel;
    // ComboBoxes
    private JComboBox cbxOv3Account;
    private JComboBox cbxOv1AveragceOnSerie;
    private JComboBox cbxOv2Account;
    private JComboBox cbxOv2Serie;
    private JComboBox cbxOv6SeenMovie;
    // Tables
    private JTable tblOvSingleProfile;
    private JTable tblOv1;
    private JTable tblOv2;
    // Lists
    private JList listOv6SeenMovies;
    private JList listOv3;
    private JList listOv4;
    // Buttons
    private JButton btnOv1Refresh;
    private JButton btnOv2Refresh;
    private JButton btnOv3Refresh;
    private JButton btnOv5Refresh;
    private JButton btnOv6Refresh;

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
        // Make the tab appear on the left hand side of the window
        tabMain.setTabPlacement(SwingConstants.LEFT);
        tabCRUD.setTabPlacement(SwingConstants.LEFT);
    }
}
