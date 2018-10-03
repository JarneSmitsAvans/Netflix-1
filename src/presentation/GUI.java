package presentation;

import application.AccountManagerImpl;

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

    //  Labels
    private JLabel NetflixStatistixLogo;
    private JLabel lblDesignerInfo;
    private JLabel lblDesignerInfo2;
    private JLabel lblDesignerInfo3;
    private JLabel lblDesignerInfo4;
    private JLabel lblDesignerInfo5;
    private JLabel lblDesignerInfo6;
    private JLabel lblDesignerInfo7;
    private JLabel lblDesignerInfo8;

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
    // Setters

    // JComboBoxes
    private JComboBox cbAvgWatchedAccount;
    private JComboBox cbAvgWatchedEpisode;
    private JComboBox cbAvgOfWatchedSerie;
    private JComboBox cbAvgOfWatchedEpisode;
    private JComboBox cbAmountOfViewsOfMovie;
    private JComboBox cbWatchedByAccount;
    private JPanel netflixBeheer;
    private JTabbedPane crudPane;
    private JTabbedPane crudAccountsAndProfiles;
    private JPanel addAccount;
    private JPanel accountsAndProfilesPane;
    private JPanel seriesAndEpisodesPane;
    private JTabbedPane crudSeriesAndEpisodes;
    private JPanel moviesPane;
    private JTabbedPane crudMovie;
    private JTextField txtAccountName;
    private JTextField txtAccountAddress;
    private JTextField txtAccountResidence;
    private JButton btnAddAccount;
    private JPanel addSeries;
    private JPanel addMovie;
    private JComboBox comboBox1;
    private JPanel editAccount;
    private JPanel editSeries;
    private JPanel editMovie;
    private JPanel deleteAccount;
    private JButton verwijderButton;
    private JPanel deleteSeries;
    private JPanel deleteMovie;
    private JPanel addProfileToAccount;
    private JPanel addEpisodeToSeries;
    private JPanel editProfile;
    private JPanel editEpisode;
    private JPanel deleteProfile;
    private JPanel deleteEpisode;

    // Getters
    public JComboBox getCbWatchedByAccount() {
        return cbWatchedByAccount;
    }

    public JComboBox getCbAvgWatchedAccount() {
        return cbAvgWatchedAccount;
    }

    public JTextPane getTxtAccountsWithOneProfile() {
        return txtAccountsWithOneProfile;
    }

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
        lblDesignerInfo4.setText(designInfo);
        lblDesignerInfo5.setText(designInfo);
        lblDesignerInfo6.setText(designInfo);
        lblDesignerInfo7.setText(designInfo);
        lblDesignerInfo8.setText(designInfo);
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

    private void initializeComponents() {
        try {
            /* Initialize the value for components in the GUI */
            accountManager.initializeAccountComponents(this);
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

