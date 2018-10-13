package presentation;

import application.AccountManagerImpl;
import application.MovieManagerImpl;
import application.ProfileManagerImpl;
import application.SerieManagerImpl;
import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JSpinnerDateEditor;
import domain.Account;
import domain.Listeners.AccountListeners.AccountCreateListener;
import domain.Listeners.AccountListeners.AccountDeleteListener;
import domain.Listeners.AccountListeners.AccountUpdateComboBoxListener;
import domain.Listeners.AccountListeners.AccountUpdateListener;
import domain.Listeners.MovieListeners.*;
import domain.Listeners.ProfileListeners.*;
import domain.Listeners.SerieListeners.*;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate.*;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourDeleteListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourLoadProfilesForSelectedAccountDeleteListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete.WatchBehaviourLoadWatchedMediaForDelete;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadProfilesForSelectedAccountEditListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviorLoadWatchedMediaForEdit;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviourEditBehaviourListener;
import domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit.WatchBehaviourLoadDurationListener;
import domain.Movie;
import domain.Profile;

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
    private JPanel Home;
    private JPanel mainPanel;

    // CRUD Panels---------------------------------------------
    private JPanel netflixManagement;
    private JPanel accountsAndProfilesPanel;
    private JPanel seriesAndEpisodesPanel;
    private JPanel moviesPanel;
    private JPanel watchBehaviourPanel;

    // Account-------------------------------------------------
    // Overviews
    private JPanel accountsWithOneProfilePanel;
    private JTextPane txtAccountsWithOneProfile;

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

    // Add
    private JPanel addSeriesPanel;
    private JTextField txtSerieTitle;
    private JTextField txtSerieGenre;
    private JTextField txtSerieLanguage;
    private JTextField txtSerieAge;
    private JButton btnCreateSerie;

    // Edit
    private JPanel editSeriesPanel;
    private JComboBox cbGetUpdateSerie;
    private JTextField txtUpdateSerieTitle;
    private JTextField txtUpdateSerieGenre;
    private JTextField txtUpdateSerieAge;
    private JTextField txtUpdateSerieLanguage;
    private JButton btnUpdateSerie;

    // Delete
    private JPanel deleteSeriesPanel;
    private JComboBox cbGetdeleteSerie;
    private JButton btnDeleteSerie;

    // Episode-------------------------------------------------
    // Overviews
    private JPanel avgWatchedEpisodeBySeriePanel;
    private JComboBox cbSerieAvgWatchedByEpisode;
    private JComboBox cbEpisodeAvgWatchedByEpisode;

    // Add
    private JPanel addEpisodeToSeriesPanel;
    private JComboBox cbCreateEpisodeForSerie;
    private JTextField cbCreateEpisodeTitle;
    private JTextField cbCreateEpisodeDuration;
    private JTextField cbCreateEpisodeReferencenumber;
    private JButton btnCreateEpisode;

    // Edit
    private JPanel editEpisodePanel;
    private JComboBox cbEditEpisodeOfSerie;
    private JComboBox cbEditEpisodeForSerie;
    private JTextField txtEditEpisodeNumber;
    private JTextField txtEditEpisodeDuration;
    private JTextField txtEditEpisodeTitle;
    private JButton btnEditEpisode;

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
    private JComboBox cbCreateSerieReferenceNumber;
    private JComboBox cbUpdateSerieReferenceNumber;
    private JButton btnDeleteWatchBehaviour;

//    Getters ------------------------------------------------------------------------------------------------------------------

    // JPanel------------------------------------------------------------
    public JPanel getMainPanel() {
        return mainPanel;
    }

    // Account-----------------------------------------------------------
    // Overviews
    public JTextPane getTxtAccountsWithOneProfile() {
        return txtAccountsWithOneProfile;
    }

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
    public JComboBox getCbWatchedProgramsBySelectedProfile() {
        return cbWatchedProgramsBySelectedProfile;
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
    public JComboBox getCbCreateEpisodeForSerie(){return cbCreateEpisodeForSerie;}
    public JComboBox getCbDeleteEpisodeFromSerie(){return cbDeleteEpisodeFromSerie;}
    public JComboBox getCbEditEpisodeOfSerie(){return cbEditEpisodeOfSerie;}
    public JComboBox getCbCreateSerieReferenceNumber(){return cbCreateSerieReferenceNumber;}
    public JComboBox getCbUpdateSerieReferenceNumber(){return cbUpdateSerieReferenceNumber;}

    // Add
    public JTextField getTxtSerieTitle() { return txtSerieTitle; }
    public JTextField getTxtSerieGenre() { return txtSerieGenre; }
    public JTextField getTxtSerieLanguage() { return txtSerieLanguage; }
    public JTextField getTxtSerieAge() { return txtSerieAge; }

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
    public JComboBox getcbAvgOfWatchedEpisode(){
       return cbEpisodeAvgWatchedByEpisode;
   }
    public JComboBox getCbEpisodeAvgWatchedByEpisode(){
               return cbEpisodeAvgWatchedByEpisode;
           }

    // Add
    public JTextField getCbCreateEpisodeTitle(){return cbCreateEpisodeTitle;}
    public JTextField getCbCreateEpisodeDuration(){return cbCreateEpisodeDuration;}
    public JTextField getCbCreateEpisodeReferencenumber(){return cbCreateEpisodeReferencenumber;}

    // Edit
    public JComboBox getCbEditEpisodeForSerie() {return cbEditEpisodeForSerie;}
    public JTextField getTxtEditEpisodeTitle() {return txtEditEpisodeTitle;}
    public JTextField getTxtEditEpisodeDuration() {return txtEditEpisodeDuration;}
    public JTextField getTxtEditEpisodeNumber() {return txtEditEpisodeNumber;}

    // Delete
    public JComboBox getCbDeleteEpisode(){return cbDeleteEpisode;}

// ---------------------------------------------------------------------------------------------------------------------

    // Managers
    private AccountManagerImpl accountManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private SerieManagerImpl serieManager;

    // Constructor
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

        initializeComponents();

//    ActionListeners ------------------------------------------------------------------------------------------------------------------

        // Account-------------------------------------------
        // Add
        btnAddAccount.addActionListener(new AccountCreateListener(this, new Account()));

        // Edit
        cbUpdateAccount.setSelectedItem(null);
        cbUpdateAccount.addActionListener(new AccountUpdateComboBoxListener(this));
        btnUpdateAccount.addActionListener(new AccountUpdateListener(this));

        // Delete
        btnDeleteAccount.addActionListener(new AccountDeleteListener(this));

        // Profile-------------------------------------------
        // Add
        btnCreateProfile.addActionListener(new ProfileCreateListener(this, new Profile()));

        // Edit
        cbSelectAccountForProfileEdit.setSelectedItem(null);
        cbSelectAccountForProfileEdit.addActionListener(new ProfilesLoadProfilesForSelectedAccountUpdate(this));
        cbUpdateSelectedProfile.setEnabled(false);
        cbUpdateSelectedProfile.addActionListener(new ProfileUpdateComboBoxListener(this));
        btnEditProfile.addActionListener(new ProfileUpdateListener(this));

        // Delete
        cbDeleteProfileFromSelectedAccount.addActionListener(new ProfileLoadProfilesForSelectedAccountListener(this));
        cbDeleteProfile.setEnabled(false);
        btnDeleteProfile.addActionListener(new ProfileDeleteListener(this));

        // Movie--------------------------------------------
        // Overviews
        cbWatchedMoviesByAccount.addActionListener(new MovieGetCbValueWatchedByAccountListener(this));
        cbAmountOfViewsOfMovie.addActionListener(new MovieLoadAmountOfViewsListener(this));

        // Add
        btnAddMovie.addActionListener(new MovieCreateListener(this, new Movie()));

        // Edit
        cbUpdateMovie.addActionListener(new MovieUpdateFillFieldsListener(this));
        btnUpdateMovie.addActionListener(new MovieUpdateListener(this));

        // Delete
        btnDeleteMovie.addActionListener(new MovieDeleteListener(this));

        // Serie--------------------------------------------
        // Overviews
        cbSerieAvgWatchedByEpisode.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this, cbSerieAvgWatchedByEpisode));
        cbEditEpisodeOfSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbEditEpisodeOfSerie));

        // Add
        btnCreateSerie.addActionListener(new SerieCreateListener(this));

        // Edit
        cbEditEpisodeOfSerie.addActionListener(new SerieGetSelectedSerieForEpisodeListener(this,cbEditEpisodeOfSerie));
        cbGetUpdateSerie.addActionListener(new SerieGetValuesToUpdate(this,cbGetUpdateSerie));
        btnUpdateSerie.addActionListener(new SerieUpdateListener(this,cbGetUpdateSerie));

        // Delete
        btnDeleteSerie.addActionListener(new SerieDeleteListener(this,cbGetdeleteSerie));

        // Watch Behaviour-----------------------------------
        // Add
        ((JSpinner.DefaultEditor) JSpinWatchedDate.getEditor()).getTextField().setEditable(false);
        cbAddWatchedMediaAccount.setSelectedItem(null);
        cbAddWatchedMediaProfile.setEnabled(false);
        JSpinWatchedDate.setEnabled(false);
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

        // Edit
        ((JSpinner.DefaultEditor) JSpinNewWatchedDate.getEditor()).getTextField().setEditable(false);
        cbEditWatchedMediaAccount.setSelectedItem(null);
        cbEditWatchedMediaProfile.setEnabled(false);
        cbEditWatchedMediaAccount.addActionListener(new WatchBehaviorLoadProfilesForSelectedAccountEditListener(this));
        cbEditWatchedMediaProfile.addActionListener(new WatchBehaviorLoadWatchedMediaForEdit(this));
        cbEditWatchedMediaTitle.addActionListener(new WatchBehaviourLoadDurationListener(this));
        btnEditWatchedMedia.addActionListener(new WatchBehaviourEditBehaviourListener(this));

        // Delete
        cbDeleteWatchedMediaAccount.setSelectedItem(null);
        cbDeleteWatchedMediaProfile.setEnabled(false);
        cbDeleteWatchedMediaAccount.addActionListener(new WatchBehaviourLoadProfilesForSelectedAccountDeleteListener(this));
        cbDeleteWatchedMediaProfile.addActionListener(new WatchBehaviourLoadWatchedMediaForDelete(this));
        btnDeleteWatchBehaviour.addActionListener(new WatchBehaviourDeleteListener(this));
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

        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}

