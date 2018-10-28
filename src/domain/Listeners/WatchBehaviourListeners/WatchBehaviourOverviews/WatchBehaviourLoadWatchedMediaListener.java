package domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews;

import application.GeneralManager;
import application.ProfileManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Episode;
import domain.Movie;
import presentation.GUI;

import javax.swing.text.BadLocationException;
import javax.swing.text.StyledDocument;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;


/**
 * WatchBehaviourLoadWatchedMediaListener.java
 * This ActionListener gets all the watched programs on actionPerformed and adds the program to the ArrayList of that object type.
 * It then displays that watched program, together with how long it was watched for and when.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourLoadWatchedMediaListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    // Constructor
    public WatchBehaviourLoadWatchedMediaListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    // OnActionPerformed: Get all the watched media matching the selected profile, and append that to the watched programs textPane
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (ui.getCbWatchedProgramsBySelectedProfile().getSelectedItem() != null) {
            try {
                // Declare/initialize variables
                ui.getTxtWatchedProgramsBySelectedProfile().setText(null);
                int profileID = profileManager.getIdOfProfile(ui.getCbWatchedProgramsBySelectedProfile().getSelectedItem().toString(), ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem().toString());
                ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
                ArrayList<Episode> watchedEpisodes = watchBehaviourManager.getWatchedEpisodes(profileID);
                ArrayList<String> watchedPrograms = new ArrayList<>();

                for (Episode episode : watchedEpisodes) {
                    float watchedEpisodeDuration = episode.getWatchedDuration();
                    float episodeTotalDuration = episode.getDuration();
                    // Calculate how much was watched
                    float percent = watchedEpisodeDuration / episodeTotalDuration * 100;
                    // Round to 2 decimals
                    float roundedPercentage = GeneralManager.round(percent, 2);
                    // Add it to the watched programs ArrayList
                    watchedPrograms.add(episode.getSerieTitle() + ":  " + episode.getTitle() + " bekeken voor: " + roundedPercentage + "%" + " en laatst bekeken op: " + episode.getWatchedOn());
                }
                for (Movie movie : watchedMovies) {
                    float watchedMovieDuration = movie.getWatchedDuration();
                    float movieTotalDuration = movie.getDuration();
                    // Calculate how much was watched
                    float percent = watchedMovieDuration / movieTotalDuration * 100;
                    // Round to 2 decimals
                    float roundedPercentage = GeneralManager.round(percent, 2);
                    // Add it to the watched programs ArrayList
                    watchedPrograms.add(movie.getTitle() + " bekeken voor: " + roundedPercentage + "%" + " en laatst bekeken op: " + movie.getWatchedOn());
                }
                // For each watched title, add it to the textPane.
                StyledDocument styledDocument = ui.getTxtWatchedProgramsBySelectedProfile().getStyledDocument();
                if (!watchedPrograms.isEmpty()) {
                    for (String string : watchedPrograms) {
                        styledDocument.insertString(0, string + "\n", null);
                    }
                } else {
                    styledDocument.insertString(0, "Geselecteerd profiel heeft nog geen programma's bekeken." + "\n", null);
                }
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }
    }
}


