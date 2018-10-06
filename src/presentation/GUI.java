package presentation;

import application.AccountManagerImpl;
import application.EpisodeManagerlmpl;
import application.ProfileManagerImpl;
import application.SerieManagerImpl;
import com.toedter.calendar.JDateChooser;
import domain.Account;
import domain.Listeners.AccountListeners.AccountCreateListener;
import domain.Listeners.AccountListeners.AccountDeleteListener;
import domain.Listeners.AccountListeners.AccountUpdateComboBoxListener;
import domain.Listeners.AccountListeners.AccountUpdateListener;
import domain.Listeners.MovieListeners.MovieCreateListener;
import domain.Listeners.MovieListeners.MovieGetCbValueWatchedByAccountListener;
import domain.Listeners.ProfileListeners.*;
import domain.Listeners.SerieListeners.SerieViewListener;
import domain.Movie;
import domain.Profile;

import javax.swing.*;
import java.awt.*;

public class GUI implements Runnable {
    // Class variables
    private int width;
    private int height;

//    ------------------------------------------------------------------------------------------------------------------

    // Components
    // JFrame
    private JFrame frame;
    // JTabbedPanes
    private JTabbedPane tabbedPane;
    private JTabbedPane crudPane;
    private JTabbedPane crudAccountsAndProfiles;
    private JTabbedPane crudSeriesAndEpisodes;
    private JTabbedPane crudMovie;
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
    private JPanel moviesPane;
    private JPanel editAccount;
    private JPanel editSeries;
    private JPanel editMovie;
    private JPanel deleteAccount;
    private JPanel deleteSeries;
    private JPanel deleteMovie;
    private JPanel addProfileToAccount;
    private JPanel addEpisodeToSeries;
    private JPanel editProfile;
    private JPanel editEpisode;
    private JPanel deleteProfile;
    private JPanel deleteEpisode;
    private JPanel addSeries;
    private JPanel addMovie;
    private JPanel netflixBeheer;
    private JPanel addAccount;
    private JPanel accountsAndProfilesPane;
    private JPanel seriesAndEpisodesPane;
    // JTextPanes
    private JTextPane txtLongestDurationOfMovieBelow16;
    private JTextPane txtAccountsWithOneProfile;
    private JTextPane txtAvgWatchedSeries;
    // JComboBoxes
    private JComboBox cbAvgWatchedAccount;
    private JComboBox cbAvgWatchedSerie;
    private JComboBox cbAvgOfWatchedSerie;
    private JComboBox cbAvgOfWatchedEpisode;
    private JComboBox cbAmountOfViewsOfMovie;
    private JComboBox cbWatchedByAccount;
    private JComboBox cbUpdateSelectedAccount;
    private JComboBox cbDeleteSelectedAccount;
    private JComboBox cbAddProfileToSelectedAccount;
    private JComboBox cbUpdateSelectedProfile;
    private JComboBox cbDeleteProfileFromSelectedAccount;
    private JComboBox cbDeleteProfile;


    private JComboBox cbWatchedProgramsBySelectedProfile;
    // JTextFields
    // Add account
    private JTextField txtAccountName;
    private JTextField txtAccountAddress;
    private JTextField txtAccountResidence;
    // Add movie
    private JTextField txtMovieTitle;
    private JTextField txtMovieDuration;
    private JTextField txtMovieGenre;
    private JTextField txtMovieLanguage;
    private JTextField txtMovieMinAge;
    // Edit account
    private JTextField txtUpdateAccountName;
    private JTextField txtUpdateAccountAdres;
    private JTextField txtUpdateAccountResidence;
    // Profile
    private JTextField txtProfileName;
    private JTextField txtUpdateProfileName;
    // JDateChoosers
    private JDateChooser jDPdateOfBirth;
    private JDateChooser jDPnewDateOfBirth;
    // JButtons
    private JButton btnAddAccount;
    private JButton btnDeleteAccount;
    private JButton btnUpdateAccount;
    private JButton btnAddMovie;
    private JPanel watchedProgramsByProfile;
    private JComboBox cbAddWatchedMediaAccount;
    private JComboBox cbAddWatchedMediaProfile;
    private JButton btnCreateProfile;
    private JButton btnEditProfile;
    private JButton btnDeleteProfile;
    private JTextField txtCreateSerieTitle;
    private JTextField TxtCreateSerieGenre;
    private JTextField txtCreateSerieLanguage;
    private JTextField txtCreatSerieAge;
    private JComboBox cbxGetUpdateSerie;
    private JTextField txtUpdateSerieTitle;
    private JTextField txtUpdateSerieGenre;
    private JTextField txtUpdateSerieAge;
    private JTextField txtUpdateSerieLanguage;
    private JComboBox cbxGetdeleteSerie;
    private JComboBox cbCreateEpisodeForSerie;
    private JTextField cbCreateEpisodeTitle;
    private JTextField cbCreateEpisodeDuration;
    private JTextField cbCreateEpisodeReferencenumber;
    private JTextField cbEditEpisodeReferencenumber;
    private JTextField cbEditEpisodeDuration;
    private JTextField cbEditEpisodeTitle;
    private JComboBox cbEditEpisodeForSerie;
    private JComboBox cbDeleteEpisodeReferencenumber;
    private JComboBox cbdeleteEditEpisode;


    private JComboBox cbSelectAccountForProfileEdit;
    private JTextPane txtMoviesWatchedByAccount;
    private JRadioButton rbMovie;
    private JRadioButton rbSerie;
    private JComboBox cbAddWatchedMediaTitle;
    private JLabel lblDurationOfSelectedProgram;
    private JTextField txtAddWatchedMediaDuration;


//    ------------------------------------------------------------------------------------------------------------------

    // Getters
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Account
    public JComboBox getCbAvgWatchedAccount() {
        return cbAvgWatchedAccount;
    }
    public JTextPane getTxtAccountsWithOneProfile() {
        return txtAccountsWithOneProfile;
    }
    public JTextField getTxtAccountName() {
        return txtAccountName;
    }
    public JTextField getTxtAccountAddress() {
        return txtAccountAddress;
    }
    public JTextField getTxtAccountResidence() {
        return txtAccountResidence;
    }
    public JComboBox getCbDeleteSelectedAccount() {
        return cbDeleteSelectedAccount;
    }
    public JTextField getTxtUpdateAccountName() {
        return txtUpdateAccountName;
    }
    public JTextField getTxtUpdateAccountAdres() {
        return txtUpdateAccountAdres;
    }
    public JTextField getTxtUpdateAccountResidence() {
        return txtUpdateAccountResidence;
    }
    public JComboBox getCbUpdateSelectedAccount() {
        return cbUpdateSelectedAccount;
    }

    // Profiles
    public JComboBox getCbWatchedProgramsBySelectedProfile() {
        return cbWatchedProgramsBySelectedProfile;
    }
    // Create profile
    public JComboBox getCbAddProfileToSelectedAccount() {
        return cbAddProfileToSelectedAccount;
    }
    public JTextField getTxtProfileName() {
        return txtProfileName;
    }
    public JDateChooser getjDPdateOfBirth() {
        return jDPdateOfBirth;
    }

    // Edit profile
    public JComboBox getCbUpdateSelectedProfile() {
        return cbUpdateSelectedProfile;
    }
    public JTextField getTxtUpdateProfileName() {
        return txtUpdateProfileName;
    }
    public JDateChooser getjDPnewDateOfBirth() {
        return jDPnewDateOfBirth;
    }

    // Delete profile
    public JComboBox getCbDeleteProfileFromSelectedAccount() {
        return cbDeleteProfileFromSelectedAccount;
    }
    public JComboBox getCbDeleteProfile() {
        return cbDeleteProfile;
    }
    public JComboBox getCbSelectAccountForProfileEdit() {
        return cbSelectAccountForProfileEdit;
    }

    // Movie
    // Create movie
    public JTextField getTxtMovieTitle() {
        return txtMovieTitle;
    }
    public JTextField getTxtMovieDuration() {
        return txtMovieDuration;
    }
    public JTextField getTxtMovieGenre() {
        return txtMovieGenre;
    }
    public JTextField getTxtMovieLanguage() {
        return txtMovieLanguage;
    }
    public JTextField getTxtMovieMinAge() {
        return txtMovieMinAge;
    }

    // Get movies by account
    public JComboBox getCbWatchedByAccount() {
        return cbWatchedByAccount;
    }






    //Serie
        //View Series
            //ComoBox
            public JComboBox getCbAvgOfWatchedSerie(){
                return cbAvgOfWatchedSerie;
            }
            public JComboBox getcbAvgWatchedSerie(){
                return cbAvgWatchedSerie;
            }

   //Episode
       //View Episodes
            //ComoBox
           public JComboBox getcbAvgOfWatchedEpisode(){
               return cbAvgOfWatchedEpisode;
           }

//    ------------------------------------------------------------------------------------------------------------------

    // Managers
    // Account
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
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

    }

    private void createComponents(Container container) {
        container = mainPanel;
        String designInfo = "Informatica | 2018-2019 | 23IVK1 | Dylan ten Böhmer (2137867), Marc Verwijmeren (2139166) en Kim van den Berg (2137853)";
        lblDesignerInfo.setText(designInfo);
        lblDesignerInfo2.setText(designInfo);
        lblDesignerInfo3.setText(designInfo);
        lblDesignerInfo4.setText(designInfo);
        lblDesignerInfo5.setText(designInfo);
        lblDesignerInfo6.setText(designInfo);
        lblDesignerInfo7.setText(designInfo);
        lblDesignerInfo8.setText(designInfo);

        initializeComponents();
        // Account
        cbUpdateSelectedAccount.addActionListener(new AccountUpdateComboBoxListener(this));
        btnAddAccount.addActionListener(new AccountCreateListener(this, new Account()));
        btnDeleteAccount.addActionListener(new AccountDeleteListener(this));
        btnUpdateAccount.addActionListener(new AccountUpdateListener(this));
        // Profile
        cbDeleteProfile.setEnabled(false);
        btnEditProfile.addActionListener(new ProfileUpdateListener(this));
        btnCreateProfile.addActionListener(new ProfileCreateListener(this, new Profile()));
        cbDeleteProfileFromSelectedAccount.addActionListener(new ProfileLoadProfilesForSelectedAccountListener(this));
        cbUpdateSelectedProfile.addActionListener(new ProfileUpdateComboBoxListener(this));
        btnDeleteProfile.addActionListener(new ProfileDeleteListener(this));

        cbSelectAccountForProfileEdit.addActionListener(new ProfilesLoadProfilesForSelectedAccountUpdate(this));
        cbUpdateSelectedProfile.setEnabled(false);

        // Movie
        btnAddMovie.addActionListener(new MovieCreateListener(this, new Movie()));
        cbWatchedByAccount.addActionListener(new MovieGetCbValueWatchedByAccountListener(this));

        //Serie
        cbAvgOfWatchedSerie.addActionListener(new SerieViewListener(this));

        // Watch Behaviour
        //comboBox2.setEnabled(false);
        //cbAddWatchedMediaAccount.addActionListener(new WatchBehaviourLoadProfilesForSelectedAccountListener(this));
    }

    private void initializeComponents() {
        try {
            /* Initialize the value for components in the GUI */
            //Account
            accountManager.initializeAccountComponents(this);
            accountManager.initializeAccountComboBoxes(this);

            //Profile
            profileManager.initializeProfileComboBoxes(this);

            //Serie
            SerieManagerImpl serieManager = new SerieManagerImpl(this);
            serieManager.fillAllSerieCbx();

            //Episodes
            EpisodeManagerlmpl episodeManagerlmpl = new EpisodeManagerlmpl(this);



        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

