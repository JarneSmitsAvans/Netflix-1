package presentation;

import application.*;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import domain.Listeners.AccountListeners.AccountCreateListener;
import domain.Listeners.AccountListeners.AccountDeleteListener;
import domain.Listeners.AccountListeners.AccountOverview.AccountOverviewLoadListener;
import domain.Listeners.AccountListeners.AccountUpdateComboBoxListener;
import domain.Listeners.AccountListeners.AccountUpdateListener;
import domain.Listeners.EpisodeListeners.*;
import domain.Listeners.MovieListeners.*;
import domain.Listeners.PresentationListeners.IntFilter;
import domain.Listeners.PresentationListeners.JTextFieldLimit;
import domain.Listeners.ProfileListeners.*;
import domain.Listeners.ProfileListeners.ProfileOverview.ProfileOverviewLoadProfilesForSelectedAccountListener;
import domain.Listeners.ProfileListeners.ProfileOverview.ProfilesOverviewLoadListener;
import domain.Listeners.SerieListeners.*;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate.*;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourDeleteListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourLoadProfilesForSelectedAccountDeleteListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourLoadWatchedMediaForDeleteListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadProfilesForSelectedAccountEditListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadWatchedMediaForEditListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviourEditBehaviourListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviourLoadDurationListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews.WatchBehaviourLoadProfilesForSelectedAccountListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews.WatchBehaviourLoadWatchedMediaListener;

import javax.swing.*;
import java.awt.*;

public class GUI implements Runnable {
    // Class variables
    private int width;
    private int height;

//    Components ------------------------------------------------------------------------------------------------------------------

    // JFrame--------------------------------------
    private JFrame frame;

    private JTabbedPane crudPane;
    private JTabbedPane crudAccountsAndProfiles;
    private JTabbedPane crudSeriesAndEpisodes;
    private JTabbedPane crudMovie;
    private JTabbedPane crudWatchBehaviour;

    //  Algemeen----------------------------------------------
    private JLabel NetflixStatistixLogo;
    private JLabel lblDesignerInfo;
    private JLabel lblDesignerInfo2;
    private JLabel lblDesignerInfo3;
    private JLabel lblDesignerInfo4;
    private JLabel lblDesignerInfo5;
    private JLabel lblDesignerInfo6;
    private JLabel lblDesignerInfo7;
    private JLabel lblDesignerInfo8;
    private JLabel lblDesignerInfo9;
    private JLabel lblDesignerInfo10;
    private JLabel lblDesignerInfo11;
    private JLabel lblDesignerInfo12;
    private JPanel Home;
    private JPanel mainPanel;
    private JTabbedPane jTabbedPaneBase;

    // CRUD Panels---------------------------------------------
    private JPanel netflixManagement;
    private JPanel accountsAndProfilesPanel;
    private JPanel seriesAndEpisodesPanel;
    private JPanel moviesPanel;
    private JPanel watchBehaviourPanel;

    private JComboBox cbRecommendedSerieForAccount;

    // View
    private JComboBox cbAccountOverviewSelectAccount;
    private JPanel readAccountPanel;
    private JLabel lblForOverviewAccountName;
    private JLabel lblForOverviewAccountAddress;
    private JLabel lblForOverviewAccountResidence;
    private JLabel lblOverviewAccountName;
    private JLabel lblOverviewAccountAddress;
    private JLabel lblOverviewAccountResidence;

    // Add
    private JPanel addAccountPanel;
    private JTextField txtAccountName;
    private JTextField txtAccountAddress;
    private JTextField txtAccountResidence;
    private JButton btnAddAccount;

    // Edit
    private JPanel editAccountPanel;
    private JComboBox cbUpdateAccount;
    private JTextField txtUpdateAccountName;
    private JTextField txtUpdateAccountAdres;
    private JTextField txtUpdateAccountResidence;
    private JButton btnUpdateAccount;

    // Delete
    private JPanel deleteAccountPanel;
    private JComboBox cbDeleteAccount;
    private JButton btnDeleteAccount;

    // Profile----------------------------------------------------
    // Overviews
    private JComboBox cbWatchedProgramsBySelectedProfile;
    private JComboBox cbWatchedProgramsBySelectedAccount;
    private JComboBox cbRecommendedSerieForProfile;

    // View
    private JComboBox cbProfileOverviewSelectAccount;
    private JComboBox cbProfileOverviewSelectProfile;
    private JPanel readProfilePanel;
    private JLabel lblOverviewProfileName;
    private JLabel lblOverviewProfileDateOfBirth;
    private JLabel lblForOverviewProfileName;
    private JLabel lblForOverviewProfileDateOfBirth;

    // Add
    private JPanel addProfileToAccountPanel;
    private JComboBox cbAddProfileToSelectedAccount;
    private JTextField txtProfileName;
    private JDateChooser jDPdateOfBirth;
    private JButton btnCreateProfile;

    // Edit
    private JPanel editProfilePanel;
    private JComboBox cbSelectAccountForProfileEdit;
    private JComboBox cbUpdateSelectedProfile;
    private JTextField txtUpdateProfileName;
    private JDateChooser jDPnewDateOfBirth;
    private JButton btnEditProfile;

    // Delete
    private JPanel deleteProfilePanel;
    private JComboBox cbDeleteProfileFromSelectedAccount;
    private JComboBox cbDeleteProfile;
    private JButton btnDeleteProfile;

    // Serie------------------------------------------------------
    // Overviews
    private JPanel avgWatchedSerieByAccountPanel;
    private JTextPane txtAvgWatchedSeries;
    private JComboBox cbAccountAvgWatchedBySerie;
    private JComboBox cbSerieAvgWatchedBySerie;
    private JTextPane txtGetAvgOfSerie;
    private JPanel avgWatchedSeriePanel;
    private JComboBox cbSerieAvgOfSerie;
    private JPanel recommendedSeriesByProfilePanel;
    private JTextPane txtRecommendedSerie;

    // View
    private JPanel readSeriesPanel;
    private JComboBox cbReadserie;
    private JLabel lblSerieTitleLabel;
    private JLabel lblSerieGenreLabel;
    private JLabel lblSerieGenre;
    private JLabel lblSerieLanguageLabel;
    private JLabel lblSerieLanguage;
    private JLabel lblSerieMinAgeLabel;
    private JLabel lblSerieMinAge;
    private JLabel lblSerieRecommendedLabel;
    private JLabel lblSerieRecommended;
    private JLabel lblSerieViewTitle;

    // Add
    private JPanel addSeriesPanel;
    private JTextField txtSerieCreateTitle;
    private JTextField txtSerieCreateGenre;
    private JTextField txtSerieCreateLanguage;
    private JTextField txtSerieCreateAge;
    private JButton btnCreateSerie;

    // Edit
    private JPanel editSeriesPanel;
    private JComboBox cbGetUpdateSerie;
    private JTextField txtUpdateSerieTitle;
    private JTextField txtUpdateSerieGenre;
    private JTextField txtUpdateSerieAge;
    private JTextField txtUpdateSerieLanguage;
    private JButton btnUpdateSerie;
    private JComboBox cbCreateSerieRecomendedSerie;
    private JComboBox cbUpdateSerieRecomendedSerie;

    // Delete
    private JPanel deleteSeriesPanel;
    private JComboBox cbGetdeleteSerie;
    private JButton btnDeleteSerie;

    // Episode-------------------------------------------------
    // Overviews
    private JPanel avgWatchedEpisodeBySeriePanel;
    private JComboBox cbSerieAvgWatchedByEpisode;
    private JComboBox cbEpisodeAvgWatchedByEpisode;
    private JTextPane txtAvgWatchedEpisode;

    // View
    private JPanel readEpisodePanel;
    private JComboBox cbReadSerieForEpisode;
    private JComboBox cbReadEpisode;
    private JLabel lblEpisodeTitleLabel;
    private JLabel lblEpisodeTitle;
    private JLabel lblEpisodeNumberLabel;
    private JLabel lblEpisodeNumber;
    private JLabel lblEpisodeDurationLabel;
    private JLabel lblEpisodeDuration;

    // Add
    private JPanel addEpisodeToSeriesPanel;
    private JComboBox cbCreateEpisodeForSerie;
    private JTextField txtCreateEpisodeTitle;
    private JTextField txtCreateEpisodeDuration;
    private JTextField txtCreateEpisodeNumber;
    private JButton btnCreateEpisode;

    // Edit
    private JPanel editEpisodePanel;
    private JComboBox cbUpdateEpisodeOfSerie;
    private JComboBox cbUpdateEpisodeForSerie;
    private JTextField txtUpdateEpisodeNumber;
    private JTextField txtUpdateEpisodeDuration;
    private JTextField txtUpdateEpisodeTitle;
    private JButton btnUpdateEpisode;

    // Delete
    private JPanel deleteEpisodePanel;
    private JComboBox cbDeleteEpisodeFromSerie;
    private JComboBox cbDeleteEpisode;
    private JButton btnDeleteEpisode;

    // Movie----------------------------------------------------
    // Overviews
    private JPanel watchedMoviesByAccountPanel;
    private JComboBox cbWatchedMoviesByAccount;
    private JTextPane txtMoviesWatchedByAccount;

    private JPanel movieWithLongestDurationAndMinAgeBelow16Panel;
    private JTextPane txtLongestDurationOfMovieBelow16;

    private JPanel amountOfViewerByMoviePanel;
    private JComboBox cbAmountOfViewsOfMovie;
    private JTextPane txtAmountOfViewersForMovie;

    private JPanel amountOfViewersWhoWatchedWholeMoviePanel;
    private JComboBox cbAmountOfViewerWholeMovie;
    private JTextPane txtAmountOfViewersWholeMovie;

    // View
    private JPanel readMoviePanel;
    private JComboBox cbReadMovie;
    private JLabel lblReadMovieTitle;
    private JLabel lblReadMovieDuration;
    private JLabel lblReadMovieGenre;
    private JLabel lblReadMovieLanguage;
    private JLabel lblReadMovieAge;

    private JLabel lblReadMovieTitleLabel;
    private JLabel lblReadMovieDurationLabel;
    private JLabel lblReadMovieGenreLabel;
    private JLabel lblReadMovieLanguageLabel;
    private JLabel lblReadMovieAgeLabel;

    // Add
    private JPanel addMoviePanel;
    private JTextField txtMovieTitle;
    private JTextField txtMovieDuration;
    private JTextField txtMovieGenre;
    private JTextField txtMovieLanguage;
    private JTextField txtMovieMinAge;
    private JButton btnAddMovie;

    // Edit
    private JPanel editMoviePanel;
    private JComboBox cbUpdateMovie;
    private JTextField txtUpdateMovieTitle;
    private JTextField txtUpdateMovieDuration;
    private JTextField txtUpdateMovieGenre;
    private JTextField txtUpdateMovieLanguage;
    private JTextField txtUpdateMovieMinimumAge;
    private JButton btnUpdateMovie;

    // Delete
    private JPanel deleteMoviePanel;
    private JComboBox cbDeleteMovie;
    private JButton btnDeleteMovie;

    // Watched media-----------------------------------------------
    // Overviews
    private JPanel watchedProgramsByProfilePanel;

    private JTextPane txtWatchedProgramsBySelectedProfile;

    // Add
    private JPanel addWatchBehaviourPanel;
    private JComboBox cbAddWatchedMediaAccount;
    private JComboBox cbAddWatchedMediaProfile;
    private JRadioButton rbMovie;
    private JRadioButton rbSerie;
    private JComboBox cbAddWatchedMediaMovieTitle;
    private JLabel lblDurationOfSelectedProgram;
    private JTextField txtAddWatchedMediaDuration;
    private JComboBox cbAddWatchedMediaEpisode;
    private JLabel lblWatchedEpisode;
    private JComboBox cbAddWatchedMediaSerieTitle;
    private JLabel lblMovieTitle;
    private JLabel lblSerieTitle;
    private JSpinnerDateEditor JSpinWatchedDate;
    private JButton btnAddWatchBehaviour;

    // Edit
    private JPanel editWatchBehaviourPanel;
    private JComboBox cbEditWatchedMediaAccount;
    private JComboBox cbEditWatchedMediaProfile;
    private JComboBox cbEditWatchedMediaTitle;
    private JLabel lblEditWatchedMediaDuration;
    private JTextField txtEditWatchedMediaDuration;
    private JSpinnerDateEditor JSpinNewWatchedDate;
    private JButton btnEditWatchedMedia;

    // Delete
    private JPanel deleteWatchBehaviourPanel;

    private JComboBox cbDeleteWatchedMediaAccount;
    private JComboBox cbDeleteWatchedMediaProfile;
    private JComboBox cbDeleteWatchedMediaTitle;
    private JButton btnDeleteWatchBehaviour;
    private JPanel accountsWithOneProfilePanel;
    private JTextPane txtAccountsWithOneProfile;


//    Getters ------------------------------------------------------------------------------------------------------------------

    // JPanel------------------------------------------------------------
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Account-----------------------------------------------------------
    // Overviews
    public JComboBox getCbAccountOverviewSelectAccount() {
        return cbAccountOverviewSelectAccount;
    }

    public JPanel getReadAccountPanel() {
        return readAccountPanel;
    }

    public JLabel getLblForOverviewAccountName() {
        return lblForOverviewAccountName;
    }

    public JLabel getLblForOverviewAccountAddress() {
        return lblForOverviewAccountAddress;
    }

    public JLabel getLblForOverviewAccountResidence() {
        return lblForOverviewAccountResidence;
    }

    public JLabel getLblOverviewAccountName() {
        return lblOverviewAccountName;
    }

    public JLabel getLblOverviewAccountAddress() {
        return lblOverviewAccountAddress;
    }

    public JLabel getLblOverviewAccountResidence() {
        return lblOverviewAccountResidence;
    }
    public JTextPane getTxtAccountsWithOneProfile() {
        return txtAccountsWithOneProfile;
    }
    public JComboBox getCbRecommendedSerieForAccount() { return cbRecommendedSerieForAccount; }


    // Add
    public JTextField getTxtAccountName() {
        return txtAccountName;
    }
    public JTextField getTxtAccountAddress() {
        return txtAccountAddress;
    }
    public JTextField getTxtAccountResidence() {
        return txtAccountResidence;
    }

    // Edit
    public JTextField getTxtUpdateAccountName() {
        return txtUpdateAccountName;
    }
    public JTextField getTxtUpdateAccountAdres() {
        return txtUpdateAccountAdres;
    }
    public JTextField getTxtUpdateAccountResidence() {
        return txtUpdateAccountResidence;
    }
    public JComboBox getCbUpdateAccount() {
        return cbUpdateAccount;
    }

    // Delete
    public JComboBox getCbDeleteAccount() {
        return cbDeleteAccount;
    }


    // Profiles----------------------------------------------------------
    // Overview
    public JComboBox getCbProfileOverviewSelectAccount() {
        return cbProfileOverviewSelectAccount;
    }

    public JComboBox getCbProfileOverviewSelectProfile() {
        return cbProfileOverviewSelectProfile;
    }

    public JPanel getReadProfilePanel() {
        return readProfilePanel;
    }

    public JLabel getLblOverviewProfileName() {
        return lblOverviewProfileName;
    }

    public JLabel getLblOverviewProfileDateOfBirth() {
        return lblOverviewProfileDateOfBirth;
    }

    public JLabel getLblForOverviewProfileName() {
        return lblForOverviewProfileName;
    }

    public JLabel getLblForOverviewProfileDateOfBirth() {
        return lblForOverviewProfileDateOfBirth;
    }
    public JComboBox getCbRecommendedSerieForProfile() { return cbRecommendedSerieForProfile; }

    // Add
    public JComboBox getCbAddProfileToSelectedAccount() {
        return cbAddProfileToSelectedAccount;
    }
    public JTextField getTxtProfileName() {
        return txtProfileName;
    }
    public JDateChooser getjDPdateOfBirth() {
        return jDPdateOfBirth;
    }

    // Edit
    public JComboBox getCbUpdateSelectedProfile() {
        return cbUpdateSelectedProfile;
    }
    public JTextField getTxtUpdateProfileName() {
        return txtUpdateProfileName;
    }
    public JDateChooser getjDPnewDateOfBirth() {
        return jDPnewDateOfBirth;
    }

    // Delete
    public JComboBox getCbDeleteProfileFromSelectedAccount() {
        return cbDeleteProfileFromSelectedAccount;
    }
    public JComboBox getCbDeleteProfile() {
        return cbDeleteProfile;
    }
    public JComboBox getCbSelectAccountForProfileEdit() {
        return cbSelectAccountForProfileEdit;
    }

    // Movie-------------------------------------------------------------
    // Overviews
    public JComboBox getCbWatchedMoviesByAccount() {
        return cbWatchedMoviesByAccount;
    }
    public JTextPane getTxtMoviesWatchedByAccount() { return txtMoviesWatchedByAccount; }

    public JTextPane getTxtLongestDurationOfMovieBelow16() { return txtLongestDurationOfMovieBelow16; }

    public JComboBox getCbAmountOfViewsOfMovie() { return cbAmountOfViewsOfMovie; }
    public JTextPane getTxtAmountOfViewersForMovie() { return txtAmountOfViewersForMovie; }

    public JComboBox getCbAmountOfViewerWholeMovie() { return cbAmountOfViewerWholeMovie; };
    public JTextPane getTxtAmountOfViewersWholeMovie() { return txtAmountOfViewersWholeMovie; };

    // View
    public JComboBox getCbReadMovie() { return cbReadMovie; }
    public JLabel getLblReadMovieTitle() { return lblReadMovieTitle; }
    public JLabel getLblReadMovieDuration() { return lblReadMovieDuration; }
    public JLabel getLblReadMovieGenre() { return lblReadMovieGenre; }
    public JLabel getLblReadMovieLanguage() { return lblReadMovieLanguage; }
    public JLabel getLblReadMovieAge() { return lblReadMovieAge; }

    public JLabel getLblReadMovieTitleLabel() { return lblReadMovieTitleLabel; }
    public JLabel getLblReadMovieDurationLabel() { return lblReadMovieDurationLabel; }
    public JLabel getLblReadMovieGenreLabel() { return lblReadMovieGenreLabel; }
    public JLabel getLblReadMovieLanguageLabel() { return lblReadMovieLanguageLabel; }
    public JLabel getLblReadMovieAgeLabel() { return lblReadMovieAgeLabel; }

    // Add
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

    // Edit
    public JComboBox getCbUpdateMovie() { return cbUpdateMovie; }
    public JTextField getTxtUpdateMovieTitle() { return txtUpdateMovieTitle; };
    public JTextField getTxtUpdateMovieDuration() { return txtUpdateMovieDuration; };
    public JTextField getTxtUpdateMovieGenre() { return txtUpdateMovieGenre; };
    public JTextField getTxtUpdateMovieLanguage() { return txtUpdateMovieLanguage; };
    public JTextField getTxtUpdateMovieMinimumAge() { return txtUpdateMovieMinimumAge; };

    // Delete
    public JComboBox getCbDeleteMovie() { return cbDeleteMovie; };

    // Watch Behaviour---------------------------------------------------
    // Overviews
    public JTextPane getTxtWatchedProgramsBySelectedProfile() {
        return txtWatchedProgramsBySelectedProfile;
    }
    public JComboBox getCbWatchedProgramsBySelectedProfile() {
        return cbWatchedProgramsBySelectedProfile;
    }

    public JComboBox getCbWatchedProgramsBySelectedAccount() {
        return cbWatchedProgramsBySelectedAccount;
    }

    // Add
    public JComboBox getCbAddWatchedMediaAccount() {
        return cbAddWatchedMediaAccount;
    }
    public JComboBox getCbAddWatchedMediaProfile() {
        return cbAddWatchedMediaProfile;
    }
    public JComboBox getCbAddWatchedMediaEpisode() {
        return cbAddWatchedMediaEpisode;
    }
    public JComboBox getCbAddWatchedMediaMovieTitle() {
        return cbAddWatchedMediaMovieTitle;
    }
    public JComboBox getCbAddWatchedMediaSerieTitle() {
        return cbAddWatchedMediaSerieTitle;
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
    public JRadioButton getRbSerie() {
        return rbSerie;
    }
    public JLabel getLblMovieTitle() {
        return lblMovieTitle;
    }
    public JLabel getLblSerieTitle() {
        return lblSerieTitle;
    }
    public JButton getBtnAddWatchBehaviour() {
        return btnAddWatchBehaviour;
    }

    // Edit
    public JComboBox getCbEditWatchedMediaAccount() {
        return cbEditWatchedMediaAccount;
    }
    public JComboBox getCbEditWatchedMediaProfile() {
        return cbEditWatchedMediaProfile;
    }
    public JComboBox getCbEditWatchedMediaTitle() {
        return cbEditWatchedMediaTitle;
    }
    public JSpinnerDateEditor getJSpinNewWatchedDate() {
        return JSpinNewWatchedDate;
    }
    public JTextField getTxtEditWatchedMediaDuration() {
        return txtEditWatchedMediaDuration;
    }
    public JLabel getLblEditWatchedMediaDuration() {
        return lblEditWatchedMediaDuration;
    }

    // Delete
    public JComboBox getCbDeleteWatchedMediaAccount() {
        return cbDeleteWatchedMediaAccount;
    }

    public JComboBox getCbDeleteWatchedMediaProfile() {
        return cbDeleteWatchedMediaProfile;
    }

    public JComboBox getCbDeleteWatchedMediaTitle() {
        return cbDeleteWatchedMediaTitle;
    }

    // Serie-------------------------------------------------------------
    // Overviews
    public JComboBox getCbAccountAvgWatchedBySerie() {
        return cbAccountAvgWatchedBySerie;
    }
    public JComboBox getCbSerieAvgWatchedByEpisode(){
        return cbSerieAvgWatchedByEpisode;
    }
    public JComboBox getCbSerieAvgWatchedBySerie(){
        return cbSerieAvgWatchedBySerie;
    }
    public JComboBox getCbCreateEpisodeForSerie(){ return cbCreateEpisodeForSerie; }
    public JComboBox getCbDeleteEpisodeFromSerie(){ return cbDeleteEpisodeFromSerie; }
    public JComboBox getCbUpdateEpisodeOfSerie(){ return cbUpdateEpisodeOfSerie; }
    public JComboBox getCbUpdateSerieRecomendedSerie(){ return cbUpdateSerieRecomendedSerie; }
    public JComboBox getCbCreateSerieRecomendedSerie(){ return cbCreateSerieRecomendedSerie; }
    public JComboBox getCbSerieAvgOfSerie() { return cbSerieAvgOfSerie; }
    public JTextPane getTxtRecommendedSerie() { return txtRecommendedSerie; }

    // View
    public JLabel getLblSerieViewTitlelb() { return lblSerieViewTitle; }
    public JLabel getLblSerieGenre() { return lblSerieGenre; }
    public JLabel getLblSerieLanguage() { return lblSerieLanguage; }
    public JLabel getLblSerieMinAge() { return lblSerieMinAge; }
    public JLabel getLblSerieRecommended() { return  lblSerieRecommended; }

    public JLabel getLblSerieTitleLabel() { return lblSerieTitleLabel; }
    public JLabel getLblSerieGenreLabel() { return lblSerieGenreLabel; }
    public JLabel getLblSerieLanguageLabel() { return lblSerieLanguageLabel; }
    public JLabel getLblSerieMinAgeLabel() { return lblSerieMinAgeLabel; }
    public JLabel getLblSerieRecommendedLabel() { return lblSerieRecommendedLabel; }

    public JComboBox getCbReadserie() { return cbReadserie; }

    // Add
    public JTextField getTxtSerieCreateTitle() { return txtSerieCreateTitle; }
    public JTextField getTxtSerieCreateGenre() { return txtSerieCreateGenre; }
    public JTextField getTxtSerieCreateLanguage() { return txtSerieCreateLanguage; }
    public JTextField getTxtSerieCreateAge() { return txtSerieCreateAge; }


    // Edit
    public JComboBox getCbGetUpdateSerie(){
        return cbGetUpdateSerie;
    }
    public JTextField getTxtUpdateSerieTitle() { return txtUpdateSerieTitle; }
    public JTextField getTxtUpdateSerieGenre() { return txtUpdateSerieGenre; }
    public JTextField getTxtUpdateSerieLanguage() { return txtUpdateSerieLanguage; }
    public JTextField getTxtUpdateSerieAge() { return txtUpdateSerieAge; }

    // Delete
    public JComboBox getCbGetdeleteSerie(){
                return cbGetdeleteSerie;
            }

    // Episode--------------------------------------------------------------
    // Overviews
    public JComboBox getcbAvgOfWatchedEpisode(){ return cbEpisodeAvgWatchedByEpisode; }
    public JComboBox getCbEpisodeAvgWatchedByEpisode(){ return cbEpisodeAvgWatchedByEpisode; }
    public JTextPane getTxtAvgWatchedSeries(){ return txtAvgWatchedSeries; }
    public JTextPane getTxtAvgWatchedEpisode() { return txtAvgWatchedEpisode; }
    public JTextPane getTxtGetAvgOfSerie() { return txtGetAvgOfSerie; }

    // View
    public  JLabel getLblEpisodeTitle() { return lblEpisodeTitle;}
    public  JLabel getLblEpisodeDuration() { return lblEpisodeDuration;}
    public  JLabel getLblEpisodeNumber() { return lblEpisodeNumber;}
    public  JComboBox getCbReadSerieForEpisode() { return cbReadSerieForEpisode; }
    public  JComboBox getCbReadEpisode() { return cbReadEpisode; }

    public  JLabel getLblEpisodeTitleLabel() { return lblEpisodeTitleLabel;}
    public  JLabel getLblEpisodeNumberLabel() { return lblEpisodeNumberLabel;}
    public  JLabel getLblEpisodeDurationLabel() { return lblEpisodeDurationLabel;}

    // Add
    public JTextField getTxtCreateEpisodeTitle(){ return txtCreateEpisodeTitle; }
    public JTextField getTxtCreateEpisodeDuration(){ return txtCreateEpisodeDuration; }
    public JTextField getTxtCreateEpisodeNumber(){ return txtCreateEpisodeNumber; }

    // Edit
    public JComboBox getCbUpdateEpisodeForSerie() { return cbUpdateEpisodeForSerie; }
    public JTextField getTxtUpdateEpisodeTitle() { return txtUpdateEpisodeTitle; }
    public JTextField getTxtUpdateEpisodeDuration() { return txtUpdateEpisodeDuration; }
    public JTextField getTxtUpdateEpisodeNumber() { return txtUpdateEpisodeNumber; }

    // Delete
    public JComboBox getCbDeleteEpisode(){ return cbDeleteEpisode; }

// ---------------------------------------------------------------------------------------------------------------------

    // Managers
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;

    // Constructor
    public GUI() {
        this.accountManager = new AccountManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
        this.episodeManager = new EpisodeManagerlmpl(this);
        this.serieManager = new SerieManagerImpl(this);
    }

    @Override
    public void run() {
        frame = new JFrame("Netflix Statistix");
        frame.setContentPane(mainPanel);
        ImageIcon icon = new ImageIcon(getClass().getResource("/presentation/images/Netflix.png"));
        frame.setIconImage(icon.getImage());
//        frame.setPreferredSize(new Dimension(1800, 1020));
        frame.setMinimumSize(new Dimension(1550, 970));
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
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
        lblDesignerInfo9.setText(designInfo);
        lblDesignerInfo10.setText(designInfo);
        lblDesignerInfo11.setText(designInfo);
        lblDesignerInfo12.setText(designInfo);

        initializeComponents();

//    ActionListeners ------------------------------------------------------------------------------------------------------------------

        // Account-------------------------------------------
        // Overviews
        cbAccountOverviewSelectAccount.addActionListener(new AccountOverviewLoadListener(this));
        // Add
        btnAddAccount.addActionListener(new AccountCreateListener(this));
        cbRecommendedSerieForAccount.addActionListener(new ProfileLoadProfielsForRecommendedSeries(this,cbRecommendedSerieForAccount,cbRecommendedSerieForProfile));

        // Edit
        cbUpdateAccount.setSelectedItem(null);
        cbUpdateAccount.addActionListener(new AccountUpdateComboBoxListener(this));
        btnUpdateAccount.addActionListener(new AccountUpdateListener(this));

        // Delete
        btnDeleteAccount.addActionListener(new AccountDeleteListener(this));

        // Profile-------------------------------------------
        // Overviews
        cbProfileOverviewSelectAccount.addActionListener(new ProfileOverviewLoadProfilesForSelectedAccountListener(this));
        cbProfileOverviewSelectProfile.addActionListener(new ProfilesOverviewLoadListener(this));

        // Add
        btnCreateProfile.addActionListener(new ProfileCreateListener(this));
        cbAddProfileToSelectedAccount.setSelectedItem(null);
        // Edit
        cbSelectAccountForProfileEdit.setSelectedItem(null);
        cbSelectAccountForProfileEdit.addActionListener(new ProfilesLoadProfilesForSelectedAccountUpdateListener(this));
        cbUpdateSelectedProfile.setEnabled(false);
        cbUpdateSelectedProfile.addActionListener(new ProfileUpdateComboBoxListener(this));
        btnEditProfile.addActionListener(new ProfileUpdateListener(this));
        // Delete
        cbDeleteProfileFromSelectedAccount.setSelectedItem(null);
        cbDeleteProfileFromSelectedAccount.addActionListener(new ProfileLoadProfilesForSelectedAccountListener(this));
        cbDeleteProfile.setEnabled(false);
        btnDeleteProfile.addActionListener(new ProfileDeleteListener(this));

        // Movie--------------------------------------------
        // Overviews
        cbWatchedMoviesByAccount.addActionListener(new MovieGetCbValueWatchedByAccountListener(this));
        cbAmountOfViewsOfMovie.addActionListener(new MovieLoadAmountOfViewsListener(this));
        cbAmountOfViewerWholeMovie.addActionListener(new MovieGetAmountOfViewerWholeMovieListener(this));

        // View
        cbReadMovie.addActionListener(new MovieGetAllInformationListener(this));

        // Add
        btnAddMovie.addActionListener(new MovieCreateListener(this));

        // Edit
        cbUpdateMovie.addActionListener(new MovieUpdateFillFieldsListener(this));
        btnUpdateMovie.addActionListener(new MovieUpdateListener(this));

        // Delete
        btnDeleteMovie.addActionListener(new MovieDeleteListener(this));

        // Serie--------------------------------------------
        // Overviews
        cbSerieAvgWatchedByEpisode.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this, cbSerieAvgWatchedByEpisode,cbEpisodeAvgWatchedByEpisode));
        cbUpdateEpisodeOfSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this, cbUpdateEpisodeOfSerie,cbUpdateEpisodeForSerie));
        cbDeleteEpisodeFromSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbDeleteEpisodeFromSerie,cbDeleteEpisode));
        cbSerieAvgOfSerie.addActionListener(new EpisodeGetAvgOfSerie(this, cbSerieAvgOfSerie));
        cbRecommendedSerieForProfile.addActionListener(new SerieGetRecommendedSerieFromProfileListener(this));

        // View
        cbReadserie.addActionListener(new SerieGetAllInformationListener(this, cbReadserie));

        // Add
        btnCreateSerie.addActionListener(new SerieCreateListener(this));
        txtSerieCreateAge.addKeyListener(new IntFilter());


        // Edit
        cbGetUpdateSerie.addActionListener(new SerieGetValuesToUpdateListener(this,cbGetUpdateSerie));
        btnUpdateSerie.addActionListener(new SerieUpdateListener(this,cbGetUpdateSerie));
        txtUpdateSerieAge.addKeyListener(new IntFilter());

        // Delete
        btnDeleteSerie.addActionListener(new SerieDeleteListener(this,cbGetdeleteSerie));

        // Episode------------------------------------------
        // Overviews
        cbEpisodeAvgWatchedByEpisode.addActionListener(new EpisodeGetAvgOfWatchedEpisodes(this, cbEpisodeAvgWatchedByEpisode));

        // View
        cbReadSerieForEpisode.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbReadSerieForEpisode,cbReadEpisode));
        cbReadEpisode.addActionListener(new EpisodeGetAllInformationListener(this,cbReadEpisode));
        // Add
        btnCreateEpisode.addActionListener(new EpisodeCreateListener(this));
        txtCreateEpisodeDuration.addKeyListener(new IntFilter());
        txtCreateEpisodeNumber.addKeyListener(new IntFilter());

        // Update
        cbUpdateEpisodeForSerie.addActionListener(new EpisodeGetValuesToUpdate(this, cbUpdateEpisodeForSerie));
        btnUpdateEpisode.addActionListener(new EpisodeUpdateListener(this,cbUpdateEpisodeForSerie));
        txtUpdateEpisodeNumber.addKeyListener(new IntFilter());
        txtUpdateEpisodeDuration.addKeyListener(new IntFilter());

        // Delete
        btnDeleteEpisode.addActionListener(new EpisodeDeleteListener(this,cbDeleteEpisode));

        // Watch Behaviour-----------------------------------

        // Overviews
        cbWatchedProgramsBySelectedAccount.addActionListener(new WatchBehaviourLoadProfilesForSelectedAccountListener(this));
        cbWatchedProgramsBySelectedProfile.addActionListener(new WatchBehaviourLoadWatchedMediaListener(this));
        cbWatchedProgramsBySelectedProfile.setSelectedItem(null);
        cbSerieAvgWatchedBySerie.addActionListener(new EpisodesGetAvgOfWatchedEpisodesFromAccount(this,cbSerieAvgWatchedBySerie));

        // Add
        ((JSpinner.DefaultEditor) JSpinWatchedDate.getEditor()).getTextField().setEditable(false);
        cbAddWatchedMediaAccount.setSelectedItem(null);
        cbAddWatchedMediaProfile.setEnabled(false);
        JSpinWatchedDate.setEnabled(false);
        rbMovie.setEnabled(false);
        rbSerie.setEnabled(false);
        txtAddWatchedMediaDuration.setEnabled(false);
        cbAddWatchedMediaAccount.addActionListener(new domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate.WatchBehaviourLoadProfilesForSelectedAccountListener(this));
        cbAddWatchedMediaEpisode.setVisible(false);
        lblWatchedEpisode.setVisible(false);

        // Create a button group for the two radio buttons on the Watch Behaviour tab.
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

        // Edit
        ((JSpinner.DefaultEditor) JSpinNewWatchedDate.getEditor()).getTextField().setEditable(false);
        cbEditWatchedMediaAccount.setSelectedItem(null);
        cbEditWatchedMediaProfile.setEnabled(false);
        cbEditWatchedMediaAccount.addActionListener(new WatchBehaviorLoadProfilesForSelectedAccountEditListener(this));
        cbEditWatchedMediaProfile.addActionListener(new WatchBehaviorLoadWatchedMediaForEditListener(this));
        cbEditWatchedMediaTitle.addActionListener(new WatchBehaviourLoadDurationListener(this));
        btnEditWatchedMedia.addActionListener(new WatchBehaviourEditBehaviourListener(this));

        // Delete
        cbDeleteWatchedMediaAccount.setSelectedItem(null);
        cbDeleteWatchedMediaProfile.setEnabled(false);
        cbDeleteWatchedMediaAccount.addActionListener(new WatchBehaviourLoadProfilesForSelectedAccountDeleteListener(this));
        cbDeleteWatchedMediaProfile.addActionListener(new WatchBehaviourLoadWatchedMediaForDeleteListener(this));
        btnDeleteWatchBehaviour.addActionListener(new WatchBehaviourDeleteListener(this));
    }

    private void initializeComponents() {
        try {
            /* Initialize the value for components in the GUI */
            //Account
            accountManager.initializeAccountComponents(this);
            accountManager.initializeAccountComboBoxes(this);

            //Account Create
            txtAccountName.setDocument(new JTextFieldLimit(50));
            txtAccountAddress.setDocument(new JTextFieldLimit(50));
            txtAccountResidence.setDocument(new JTextFieldLimit(50));

            //Account Edit
            txtUpdateAccountName.setDocument(new JTextFieldLimit(50));
            txtUpdateAccountAdres.setDocument(new JTextFieldLimit(50));
            txtUpdateAccountResidence.setDocument(new JTextFieldLimit(50));

            //Profile
            profileManager.initializeProfileComboBoxes(this);

            //Profile Create
            txtProfileName.setDocument(new JTextFieldLimit(50));

            //Profile Edit
            txtUpdateProfileName.setDocument(new JTextFieldLimit(50));

            // Movie
            movieManager.initializeMovieComponents(this);

            // Movie Create
            txtMovieTitle.setDocument(new JTextFieldLimit(50));
            txtMovieGenre.setDocument(new JTextFieldLimit(50));
            txtMovieLanguage.setDocument(new JTextFieldLimit(50));

            // Move Edit
            txtUpdateMovieTitle.setDocument(new JTextFieldLimit(50));
            txtUpdateMovieGenre.setDocument(new JTextFieldLimit(50));
            txtUpdateMovieLanguage.setDocument(new JTextFieldLimit(50));

            // Serie
            serieManager.fillAllSerieCbx();
            serieManager.hideSerieLabels();

            // Serie Create
            txtSerieCreateTitle.setDocument(new JTextFieldLimit(50));
            txtSerieCreateGenre.setDocument(new JTextFieldLimit(50));
            txtSerieCreateLanguage.setDocument(new JTextFieldLimit(50));

            // Serie Edit
            txtUpdateSerieTitle.setDocument(new JTextFieldLimit(50));
            txtUpdateSerieGenre.setDocument(new JTextFieldLimit(50));
            txtUpdateSerieLanguage.setDocument(new JTextFieldLimit(50));

            // Episode
            episodeManager.hideEpisodeLabels();
            // Episode Create
            txtCreateEpisodeTitle.setDocument(new JTextFieldLimit(50));
            // Episode Edit
            txtUpdateEpisodeTitle.setDocument(new JTextFieldLimit(50));

        } catch (Exception e) {
            System.out.println(e);
        }
    }
}

