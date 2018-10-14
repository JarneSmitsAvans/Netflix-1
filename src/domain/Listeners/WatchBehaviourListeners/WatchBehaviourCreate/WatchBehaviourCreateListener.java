package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

import application.*;
import domain.*;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * WatchBehaviourCreateListener.java
 * This ActionListener handles the "Create" functionality of the WatchBehaviour CRUD
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourCreateListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private MovieManagerImpl movieManager;
    private EpisodeManagerlmpl episodeManager;
    private SerieManagerImpl serieManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    // Constructor
    public WatchBehaviourCreateListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // Get the Action Command so we can differentiate between between a watched movie or episode.
        // Declare/initialize variables
        String command = ui.getBtnAddWatchBehaviour().getActionCommand();
        String timeWatched = "";
        int profileID = 0;
        // Check if input wasn't empty or invalid
        if (!(ui.getTxtAddWatchedMediaDuration().getText() == null || ui.getTxtAddWatchedMediaDuration().getText().isEmpty()) && ui.getTxtAddWatchedMediaDuration().getText().matches("^[0-9]*$")) {
            // Get the duration the user has watched for
            timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
            try {
                // Get the ID of the profile that has watched the selected program.
                profileID = profileManager.getIdOfProfile(ui.getCbAddWatchedMediaProfile().getSelectedItem().toString(), ui.getCbAddWatchedMediaAccount().getSelectedItem().toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            // Get the watched date and time the user has watched on
            String watchDateAndTime = ui.getJSpinWatchedDate().getValue().toString();
            if (command.equals("insertEpisode")) {
                // insertEpisode: We're going to insert an episode.
                if (ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null) {
                    // Check if input wasn't empty
                    // Declare/initialize more variables
                    String selectedEpisode = ui.getCbAddWatchedMediaEpisode().getSelectedItem().toString();
                    String selectedSerie = ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
                    // Get the selected serie as an object
                    Serie serie = serieManager.getSerieByName(selectedSerie);
                    // Get the id of the selected serie.
                    int id = serie.getId();
                    // Get all episodes matching parameter serie id.
                    episodeManager.setEpisodeList(id);
                    // Iterate through all the episodes.
                    ArrayList<Episode> episodes = episodeManager.getEpisode();
                    for (Episode ep : episodes) {
                        // If the title matches the selected episode title, continue.
                        if (ep.getTitle().equals(selectedEpisode)) {
                            // if the episode belongs to the serie, continue.
                            if (ep.getSerieNumber() == serie.getId()) {
                                try {
                                    // Initialize the episode object as a Program object.
                                    Program program = ep;
                                    // Set the duration of the program to the new time watched.
                                    program.setDuration(Integer.parseInt(timeWatched));
                                    // Check if the time entered wasn't greater than the duration of the selected media.
                                    if (!(program.getDuration() > Integer.parseInt(ui.getLblDurationOfSelectedProgram().getText()))) {
                                        // Create the watched episode.
                                        boolean created = watchBehaviourManager.create(program, profileID, watchDateAndTime);
                                        if (created) {
                                              /* If the create was successful, empty the fields and display a success message.
                                             After that, reinitialize the movie components in the application.
                                             */
                                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Kijkgedrag is aangemaakt.", "Kijkgedrag is aangemaakt.", JOptionPane.INFORMATION_MESSAGE);
                                            this.ui.getTxtAddWatchedMediaDuration().setText(null);
                                            movieManager.initializeMovieComponents(ui);
                                        } else {
                                            // Something went wrong, throw an error message.
                                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Kijkgedrag is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        // The value entered was greater than the duration of the selected media.
                                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.ISGREATER.getError(), "Kijkgedrag is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } else if (command.equals("insertMovie")) {
                // insertMovie: We're going to insert an episode.
                // Check if input wasn't empty or invalid
                if (ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null) {
                    try {
                        // Declare/initialize variables
                        String selectedMovie = ui.getCbAddWatchedMediaMovieTitle().getSelectedItem().toString();
                        timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
                        // Get the selected movie as an object
                        Movie movie = movieManager.getMovieByTitle(selectedMovie);
                        // Initialize the movie object as a Program object.
                        Program program = movie;
                        // Set the duration of the program to the new time watched.
                        program.setDuration(Integer.parseInt(timeWatched));
                        // Check if the time entered wasn't greater than the duration of the selected media.
                        if (!(program.getDuration() > Integer.parseInt(ui.getLblDurationOfSelectedProgram().getText()))) {
                            // Create the watched movie.
                            boolean created = watchBehaviourManager.create(program, profileID, watchDateAndTime);
                            if (created) {
                               /* If the create was successful, empty the fields and display a success message.
                               After that, reinitialize the movie components in the application.
                               */
                                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Kijkgedrag is aangemaakt.", "Kijkgedrag is aangemaakt.", JOptionPane.INFORMATION_MESSAGE);
                                this.ui.getTxtAddWatchedMediaDuration().setText(null);
                            } else {
                                // Something went wrong, throw an error message.
                                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.UNEXPECTEDERROR.getError(), "Kijkgedrag is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            // The value entered was greater than the duration of the selected media.
                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.ISGREATER.getError(), "Kijkgedrag is niet aangemaakt.", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } else {
            // Some fields were left empty or have invalid values, throw an error if that is the case.
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), ErrorHandling.EMPTYINPUT.getError(), "Lege velden", JOptionPane.ERROR_MESSAGE);
            return;
        }
    }
}
