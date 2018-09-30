package presentation;

import javax.swing.*;
import java.awt.*;

public class GUI implements Runnable {
    // Class variables
    private int width;
    private int height;
    // JFrame
    private JFrame frame;
    // JTabbedPanes
    private JTabbedPane tabbedPane;
    // JPanels
    private JPanel mainPanel;
    //     Labels
    private JLabel NetflixStatistixLogo;
    private JLabel lblDesignerInfo;
    private JLabel lblDesignerInfo2;
    private JLabel lblDesignerInfo3;
    private JPanel Home;
    private JPanel tijdsduurAfleveringPerAccountSerie;
    private JTextPane txtAvgWatchedSeries;
    private JPanel tijdsduurSeriePerAfleveringPanel;
    private JPanel bekekenFilmsDoorAccount;
    private JPanel filmMetLangsteTijdsduurOnder16;
    private JTextPane textPane1;
    private JPanel accountsMet1Profiel;
    private JTextPane txtAccountsWithOneProfile;
    private JPanel aantalKijkersPerFilm;

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
    }

    private void createComponents(Container container) {
        String designInfo = "Informatica | 2018-2019 | 23IVK1 | Dylan ten Böhmer (2137867), Marc Verwijmeren (2139166) en Kim van den Berg (2137853)";
        lblDesignerInfo.setText(designInfo);
        lblDesignerInfo2.setText(designInfo);
        lblDesignerInfo3.setText(designInfo);
//        lblDesignerInfo4.setText(designInfo);
//        lblSingleAccount.setText("Account");
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

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
