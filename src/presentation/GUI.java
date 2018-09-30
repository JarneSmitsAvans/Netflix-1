package presentation;

import application.AccountManagerImpl;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class GUI implements Runnable {
    // Class variables
    private int width;
    private int height;
    // JFrame
    private JFrame frame;
    // JTabbedPanes
    private JTabbedPane tabbedPane;
    //  Labels
    private JLabel NetflixStatistixLogo;
    private JLabel lblDesignerInfo;
    private JLabel lblDesignerInfo2;
    private JLabel lblDesignerInfo3;
    // JPanels
    private JPanel Home;
    private JPanel mainPanel;
    private JPanel tijdsduurAfleveringPerAccountSerie;
    private JPanel tijdsduurSeriePerAfleveringPanel;
    private JPanel bekekenFilmsDoorAccount;
    private JPanel filmMetLangsteTijdsduurOnder16;
    private JPanel aantalKijkersPerFilm;
    private JPanel accountsMet1Profiel;
    // JTextPanes
    private JTextPane textPane1;
    private JTextPane txtAccountsWithOneProfile;
    private JTextPane txtAvgWatchedSeries;
    // Managers
    private AccountManagerImpl accountManager;

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
        this.accountManager = new AccountManagerImpl();
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

        // Setting txtAccountsWithOneProfile read only.
        txtAccountsWithOneProfile.setEditable(false);
        initializeComponents();
    }

    // initialize the value for components in the GUI
    private void initializeComponents() {
        try {
            //Fill txtAccountsWithOneProfile with Accounts that have one and only one profile.
            ArrayList<String> singleProfileAccounts = accountManager.singleProfile();
            accountManager.addToTextPane(txtAccountsWithOneProfile, singleProfileAccounts);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
