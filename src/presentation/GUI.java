package presentation;

import application.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import domain.Account;
import domain.Listeners.AccountListeners.AccountCreateListener;
import domain.Listeners.AccountListeners.AccountDeleteListener;
import domain.Listeners.AccountListeners.AccountUpdateComboBoxListener;
import domain.Listeners.AccountListeners.AccountUpdateListener;
import domain.Listeners.MovieListeners.MovieCreateListener;
import domain.Listeners.MovieListeners.MovieGetCbValueWatchedByAccountListener;
import domain.Listeners.MovieListeners.MovieLoadAmountOfViewsListener;
import domain.Listeners.ProfileListeners.*;
import domain.Listeners.SerieListeners.SerieCreateListener;
import domain.Listeners.SerieListeners.SerieDeleteListener;
import domain.Listeners.SerieListeners.SerieGetSelectedSerieForEpisodeListener;
import domain.Listeners.SerieListeners.SerieUpdateListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate.*;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadProfilesForSelectedAccountEditListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadWatchedMediaForEdit;
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
    private JTextField txtCreateSerieGenre;
    private JTextField txtCreateSerieLanguage;
    private JTextField txtCreatSerieAge;
    private JComboBox cbGetUpdateSerie;
    private JTextField txtUpdateSerieTitle;
    private JTextField txtUpdateSerieGenre;
    private JTextField txtUpdateSerieAge;
    private JTextField txtUpdateSerieLanguage;
    private JComboBox cbGetdeleteSerie;
    private JComboBox cbCreateEpisodeForSerie;
    private JTextField cbCreateEpisodeTitle;
    private JTextField cbCreateEpisodeDuration;
    private JTextField cbCreateEpisodeReferencenumber;
    private JTextField txtEditEpisodeNumber;
    private JTextField txtEditEpisodeDuration;
    private JTextField txtEditEpisodeTitle;
    private JComboBox cbEditEpisodeForSerie;
    private JComboBox cbDeleteEpisodeFromSerie;
    private JComboBox cbDeleteEpisode;
    private JComboBox cbSelectAccountForProfileEdit;
    private JTextPane txtMoviesWatchedByAccount;


    private JRadioButton rbMovie;
    private JRadioButton rbSerie;
    private JComboBox cbAddWatchedMediaMovieTitle;
    private JLabel lblDurationOfSelectedProgram;
    private JTextField txtAddWatchedMediaDuration;
    private JPanel WatchBehaviourPane;
    private JPanel WatchBehaviourCreate;
    private JComboBox cbAddWatchedMediaEpisode;
    private JLabel lblWatchedEpisode;
    private JComboBox cbAddWatchedMediaSerieTitle;
    private JLabel lblMovieTitle;
    private JLabel lblSerieTitle;
    private JButton btnAddWatchBehaviour;
    private JTextPane txtAmountOfViewersForMovie;
    private JButton btnDeleteSerie;
    private JButton btnEditSerie;
    private JButton btnCreateSerie;
    private JButton btnDeleteEpisode;
    private JComboBox cbEditEpisodeOfSerie;
    private JButton btnEditEpisode;
    private JButton btnCreateEpisode;


    private JComboBox cbEditWatchedMediaAccount;


    private JComboBox cbEditWatchedMediaProfile;


    private JComboBox cbEditWatchedMediaTitle;
    private JLabel lblEditWatchedMediaDuration;
    private JTextField txtEditWatchedMediaDurtion;
    private JButton btnEditWatchedMedia;


    private JSpinnerDateEditor JSpinWatchedDate;

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
    public JTextPane getTxtMoviesWatchedByAccount() { return txtMoviesWatchedByAccount; }

    // Get movie with longest duration
    public JTextPane getTxtLongestDurationOfMovieBelow16() { return txtLongestDurationOfMovieBelow16; }

    // Get viewer of movie
    public JComboBox getCbAmountOfViewsOfMovie() { return cbAmountOfViewsOfMovie; }
    public JTextPane getTxtAmountOfViewersForMovie() { return txtAmountOfViewersForMovie; }

    // Watch Behaviour

    public JComboBox getCbAddWatchedMediaAccount() {
        return cbAddWatchedMediaAccount;
    }
    public JComboBox getCbAddWatchedMediaProfile() {
        return cbAddWatchedMediaProfile;
    }
    public JComboBox getCbAddWatchedMediaEpisode() {
        return cbAddWatchedMediaEpisode;
    }
    public JButton getBtnAddWatchBehaviour() {
        return btnAddWatchBehaviour;
    }
    public JTextField getTxtAddWatchedMediaDuration() {
        return txtAddWatchedMediaDuration;
    }
    public JLabel getLblDurationOfSelectedProgram() {
        return lblDurationOfSelectedProgram;
    }
    public JLabel getLblWatchedEpisode() {
        return lblWatchedEpisode;
    }

    public JSpinnerDateEditor getJSpinWatchedDate() {
        return JSpinWatchedDate;
    }
    public JRadioButton getRbMovie() {
        return rbMovie;
    }

    public JComboBox getCbEditWatchedMediaAccount() {
        return cbEditWatchedMediaAccount;
    }

    public JComboBox getCbEditWatchedMediaProfile() {
        return cbEditWatchedMediaProfile;
    }

    public JRadioButton getRbSerie() {
        return rbSerie;
    }

    public JComboBox getCbEditWatchedMediaTitle() {
        return cbEditWatchedMediaTitle;
    }
    public JComboBox getCbAddWatchedMediaMovieTitle() {
        return cbAddWatchedMediaMovieTitle;
    }

    public JComboBox getCbAddWatchedMediaSerieTitle() {
        return cbAddWatchedMediaSerieTitle;
    }

    public JLabel getLblMovieTitle() {
        return lblMovieTitle;
    }

    public JLabel getLblSerieTitle() {
        return lblSerieTitle;
    }

    //Serie
        //View series
            //ComboBox
            public JComboBox getCbAvgOfWatchedSerie(){
                return cbAvgOfWatchedSerie;
            }
            public JComboBox getCbAvgWatchedSerie(){
                return cbAvgWatchedSerie;
            }
            public JComboBox getCbCreateEpisodeForSerie(){return cbCreateEpisodeForSerie;}
            public JComboBox getCbDeleteEpisodeFromSerie(){return cbDeleteEpisodeFromSerie;}
            public JComboBox getCbEditEpisodeOfSerie(){return cbEditEpisodeOfSerie;}

        //Create Series
            //TextField
            public JTextField getTxtCreateSerieTitle() { return txtCreateSerieTitle; }
            public JTextField getTxtCreateSerieGenre() { return txtCreateSerieGenre; }
            public JTextField getTxtCreateSerieLanguage() { return txtCreateSerieLanguage; }
            public JTextField getTxtCreatSerieAge() { return txtCreatSerieAge; }

        //Update serie
            //ComboBox
            public JComboBox getCbGetUpdateSerie(){
                return cbGetUpdateSerie;
            }

            //TextField
            public JTextField getTxtUpdateSerieTitle() { return txtUpdateSerieTitle; }
            public JTextField getTxtUpdateSerieGenre() { return txtUpdateSerieGenre; }
            public JTextField getTxtUpdateSerieLanguage() { return txtUpdateSerieLanguage; }
            public JTextField getTxtUpdateSerieAge() { return txtUpdateSerieAge; }

         //Delete
            //ComboBox
            public JComboBox getCbGetdeleteSerie(){
                return cbGetdeleteSerie;
            }

   //Episode
       //View episodes
           //ComboBox
           public JComboBox getcbAvgOfWatchedEpisode(){
               return cbAvgOfWatchedEpisode;
           }
           public JComboBox getCbAvgOfWatchedEpisode(){
               return cbAvgOfWatchedEpisode;
           }

       //Create episodes
           //TextFields
           public JTextField getCbCreateEpisodeTitle(){return cbCreateEpisodeTitle;}
           public JTextField getCbCreateEpisodeDuration(){return cbCreateEpisodeDuration;}
           public JTextField getCbCreateEpisodeReferencenumber(){return cbCreateEpisodeReferencenumber;}

       //Edit episode
           //ComboBox
           public JComboBox getCbEditEpisodeForSerie() {return cbEditEpisodeForSerie;}

           //TextFields
           public JTextField getTxtEditEpisodeTitle() {return txtEditEpisodeTitle;}
           public JTextField getTxtEditEpisodeDuration() {return txtEditEpisodeDuration;}
           public JTextField getTxtEditEpisodeNumber() {return txtEditEpisodeNumber;}

       //Delete episode
           //ComboBox
           public JComboBox getCbDeleteEpisode(){return cbDeleteEpisode;}

//    ------------------------------------------------------------------------------------------------------------------

    // Managers
    // Account
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private SerieManagerImpl serieManager;

    public GUI(int width, int height) {
        this.width = width;
        this.height = height;
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
        this.serieManager = new SerieManagerImpl(this);
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
        cbUpdateSelectedAccount.setSelectedItem(null);
        cbUpdateSelectedAccount.addActionListener(new AccountUpdateComboBoxListener(this));
        btnAddAccount.addActionListener(new AccountCreateListener(this, new Account()));
        btnDeleteAccount.addActionListener(new AccountDeleteListener(this));
        btnUpdateAccount.addActionListener(new AccountUpdateListener(this));

        // Profile
        cbSelectAccountForProfileEdit.setSelectedItem(null);
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
        cbWatchedByAccount.setSelectedIndex(-1);
        cbWatchedByAccount.addActionListener(new MovieGetCbValueWatchedByAccountListener(this));
        cbAmountOfViewsOfMovie.setSelectedIndex(-1);
        cbAmountOfViewsOfMovie.addActionListener(new MovieLoadAmountOfViewsListener(this));

        //Serie
        cbAvgOfWatchedSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbAvgOfWatchedSerie));
        cbEditEpisodeOfSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbEditEpisodeOfSerie));
        btnCreateSerie.addActionListener(new SerieCreateListener(this));
        btnEditSerie.addActionListener(new SerieUpdateListener(this));
        btnDeleteSerie.addActionListener(new SerieDeleteListener(this));

        // Watch Behaviour

        JSpinWatchedDate.setEnabled(false);
        cbAddWatchedMediaAccount.setSelectedItem(null);
        cbEditWatchedMediaAccount.setSelectedItem(null);
        cbAddWatchedMediaProfile.setEnabled(false);
        cbEditWatchedMediaProfile.setEnabled(false);
        rbMovie.setEnabled(false);
        rbSerie.setEnabled(false);
        txtAddWatchedMediaDuration.setEnabled(false);
        cbAddWatchedMediaAccount.addActionListener(new WatchBehaviourLoadProfilesForSelectedAccountListener(this));
        cbAddWatchedMediaEpisode.setVisible(false);
        lblWatchedEpisode.setVisible(false);
        ButtonGroup watchBehaviourbtnGrp = new ButtonGroup();
        watchBehaviourbtnGrp.add(rbMovie);
        watchBehaviourbtnGrp.add(rbSerie);
        cbAddWatchedMediaMovieTitle.setVisible(false);
        cbAddWatchedMediaSerieTitle.setVisible(false);
        cbAddWatchedMediaEpisode.setVisible(false);
        lblMovieTitle.setVisible(false);
        lblSerieTitle.setVisible(false);
        lblWatchedEpisode.setVisible(false);
        rbSerie.addActionListener(new WatchBehaviourSerieListener(this));
        rbMovie.addActionListener(new WatchBehaviourMovieListener(this));
        cbAddWatchedMediaMovieTitle.addActionListener(new WatchBehaviourSelectedMovieListener(this));
        cbAddWatchedMediaSerieTitle.addActionListener(new WatchBehaviourLoadEpisodesForSelectedSerieListener(this));
        cbAddWatchedMediaEpisode.addActionListener(new WatchBehaviourSelectedEpisodeListener(this));
        btnAddWatchBehaviour.addActionListener(new WatchBehaviourCreateListener(this));
        cbEditWatchedMediaAccount.addActionListener(new WatchBehaviorLoadProfilesForSelectedAccountEditListener(this));
        cbEditWatchedMediaProfile.addActionListener(new WatchBehaviorLoadWatchedMediaForEdit(this));
    }
    private void initializeComponents() {
        try {
            /* Initialize the value for components in the GUI */
            //Account
            accountManager.initializeAccountComponents(this);
            accountManager.initializeAccountComboBoxes(this);

            //Profile
            profileManager.initializeProfileComboBoxes(this);

            // Movie
            movieManager.initializeMovieComponents(this);

            //Serie
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

