package presentation;

import javax.swing.*;
import java.awt.*;

public class GUI2 implements Runnable {
    // Class variables
    private int width;
    private int height;
    // JFrame
    private JFrame frame;
    // JTabbedPanes
    private JTabbedPane tabbedPane;
    // JPanels
    private JPanel mainPanel;
    private JPanel tijdsduurSeriePerAfleveringPanel;
    private JPanel JPSingleAccounts;
    // Comboboxes
    private JComboBox cbSingleAccounts;
    private JComboBox comboBox1;
    // Labels
    private JLabel NetflixStatistixLogo;
    private JLabel lblDesignerInfo;
    private JLabel lblDesignerInfo2;
    private JLabel lblDesignerInfo3;
    private JLabel lblDesignerInfo4;
    private JLabel lblSingleAccount;
    // Lists
    private JList listSingleAccounts;
    // TextPanes
    private JTextPane textPane1;

    public GUI2(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void createComponents(Container container) {
        String designInfo = "Informatica | 2018-2019 | 23IVK1 | Dylan ten Böhmer (2137867), Marc Verwijmeren (2139166) en Kim van den Berg (2137853)";
        lblDesignerInfo.setText(designInfo);
        lblDesignerInfo2.setText(designInfo);
        lblDesignerInfo3.setText(designInfo);
        lblDesignerInfo4.setText(designInfo);
        lblSingleAccount.setText("Account");
    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setContentPane(mainPanel);
        ImageIcon icon = new ImageIcon(getClass().getResource("/presentation/images/Netflix.png"));
        frame.setIconImage(icon.getImage());
        frame.setPreferredSize(new Dimension(width, height));
        frame.setMinimumSize(new Dimension(700, 500));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}
