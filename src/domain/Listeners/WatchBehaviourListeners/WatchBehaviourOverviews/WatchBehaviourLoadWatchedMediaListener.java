package domain.Listeners.WatchBehaviourListeners.WatchBehaviourOverviews;

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
import java.util.HashSet;

/**
 * WatchBehaviourLoadWatchedMediaListener.java
 * This ActionListener gets all the watched programs on actionPerformed and adds the program to the matching ArrayList of the object type.
 * It then checks if it was fully watched, and if it was fully watched, it adds it to the fullyWatchedTitels hashset.
 * Finally, it adds it to the watchedPrograms textPane.
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
                HashSet<String> fullyWatchedTitles = new HashSet<>();

                // For each movie, check if it was fully watched, if so, add it to the fully watched titles HashSet.
                for (Movie movie : watchedMovies) {
                    int watchedDuration = movie.getWatchedDuration();
                    int totalDuration = movie.getDuration();
                    if (watchedDuration == totalDuration) {
                        fullyWatchedTitles.add(movie.getTitle());
                    }
                }
                // For each episode, check if it was fully watched, if so, add it to the fully watched titels HashSet.
                for (Episode episode : watchedEpisodes) {
                    int watchedDuration = episode.getWatchedDuration();
                    int totalDuration = episode.getDuration();
                    if (watchedDuration == totalDuration) {
                        fullyWatchedTitles.add(episode.getSerieTitle() + " : " + episode.getTitle());
                    }
                }
                // For each fully watched title, add it to the textPane.
                StyledDocument styledDocument = ui.getTxtWatchedProgramsBySelectedProfile().getStyledDocument();
                if (!fullyWatchedTitles.isEmpty()) {
                    for (String string : fullyWatchedTitles) {
                        styledDocument.insertString(0, string + "\n", null);
                    }
                } else {
                    styledDocument.insertString(0, "Geselecteerd profiel heeft nog geen programma's volledig (100%) bekeken." + "\n", null);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            } catch (BadLocationException e1) {
                e1.printStackTrace();
            }
        }else{
            return;
        }
    }
}


