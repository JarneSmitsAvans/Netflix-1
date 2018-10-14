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

public class WatchBehaviourLoadWatchedMediaListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    public WatchBehaviourLoadWatchedMediaListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbWatchedProgramsBySelectedProfile().getSelectedItem() != null) {
            try {
                ui.getTxtWatchedProgramsBySelectedProfile().setText(null);
                int profileID = profileManager.getIdOfProfile(ui.getCbWatchedProgramsBySelectedProfile().getSelectedItem().toString(), ui.getCbWatchedProgramsBySelectedAccount().getSelectedItem().toString());
                ArrayList<Movie> watchedMovies = watchBehaviourManager.getWatchedMovies(profileID);
                ArrayList<Episode> watchedEpisodes = watchBehaviourManager.getWatchedEpisodes(profileID);
                HashSet<String> fullyWatchedTitels = new HashSet<>();

                for (Movie movie : watchedMovies) {
                    int watchedDuration = movie.getWatchedDuration();
                    int totalDuration = movie.getDuration();
                    if (watchedDuration == totalDuration) {
                        fullyWatchedTitels.add(movie.getTitle());
                    }
                }
                for (Episode episode : watchedEpisodes) {
                    int watchedDuration = episode.getWatchedDuration();
                    int totalDuration = episode.getDuration();
                    if (watchedDuration == totalDuration) {
                        fullyWatchedTitels.add(episode.getSerieTitle() + " : " + episode.getTitle());
                    }
                }
                StyledDocument styledDocument = ui.getTxtWatchedProgramsBySelectedProfile().getStyledDocument();
                for (String string : fullyWatchedTitels) {
                    styledDocument.insertString(0, string + "\n", null);
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


