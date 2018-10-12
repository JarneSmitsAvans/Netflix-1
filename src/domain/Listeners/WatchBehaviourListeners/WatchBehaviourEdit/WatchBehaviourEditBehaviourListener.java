package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.*;
import domain.Episode;
import domain.EpisodeComboBoxItem;
import domain.Movie;
import domain.MovieComboBoxItem;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class WatchBehaviourEditBehaviourListener implements ActionListener {

    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private MovieManagerImpl movieManager;
    private GUI ui;

    public WatchBehaviourEditBehaviourListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
        this.movieManager = new MovieManagerImpl();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (this.ui.getCbEditWatchedMediaAccount().getSelectedItem() != null && this.ui.getCbEditWatchedMediaProfile().getSelectedItem() != null
                && this.ui.getCbEditWatchedMediaTitle().getSelectedItem() != null && this.ui.getJSpinNewWatchedDate().getValue().toString() != null && !ui.getTxtEditWatchedMediaDuration().getText().isEmpty() && ui.getTxtEditWatchedMediaDuration().getText().matches("^[0-9]*$")) {
            try {
                String newWatchDateAndTime = ui.getJSpinNewWatchedDate().getValue().toString();
                boolean updated = false;
                String strSelectedAccount = this.ui.getCbEditWatchedMediaAccount().getSelectedItem().toString();
                String strSelectedProfile = this.ui.getCbEditWatchedMediaProfile().getSelectedItem().toString();
                int profileId = profileManager.getIdOfProfile(strSelectedProfile, strSelectedAccount);
                Object comboBoxItem = ui.getCbEditWatchedMediaTitle().getSelectedItem();
                int newTimeWatched = Integer.parseInt(ui.getTxtEditWatchedMediaDuration().getText());
                if (!(newTimeWatched > Integer.parseInt(ui.getLblEditWatchedMediaDuration().getText()))) {
                    if (comboBoxItem instanceof MovieComboBoxItem) {
                        MovieComboBoxItem movieComboBoxItem = (MovieComboBoxItem) comboBoxItem;
                        Movie movie = new Movie();
                        movie.setId(movieComboBoxItem.getId());
                        movie.setTitle(movieComboBoxItem.getTitle());
                        movie.setWatchedDuration(newTimeWatched);
                        movie.setWatchedOn(newWatchDateAndTime);
                        updated = watchBehaviourManager.updateWatchedMovie(movie, profileId);

                    } else if (comboBoxItem instanceof EpisodeComboBoxItem) {
                        EpisodeComboBoxItem episodeComboBoxItem = (EpisodeComboBoxItem) comboBoxItem;
                        Episode episode = new Episode();
                        episode.setWatchedDuration(newTimeWatched);
                        episode.setWatchedOn(newWatchDateAndTime);
                        episode.setId(episodeComboBoxItem.getEpisodeId());
                        updated = watchBehaviourManager.updateWatchedEpisode(episode, profileId);
                    }
                    if (updated) {
                        this.ui.getTxtEditWatchedMediaDuration().setText(null);
                        this.ui.getLblEditWatchedMediaDuration().setText(null);
                        this.ui.getCbEditWatchedMediaTitle().setSelectedItem(null);
                        this.ui.getCbEditWatchedMediaProfile().removeAllItems();
                        this.ui.getCbEditWatchedMediaAccount().setSelectedItem(null);
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Watchbehaviour has been edited.", "Watchbehaviour has been edited", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occured.", "Watchbehaviour has been added", JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "The value entered for time watched is greater than the duration of the selected media. Please specify a different value", "Watchbehaviour has not been edited", JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}

