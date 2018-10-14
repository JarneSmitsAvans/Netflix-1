package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import domain.Listeners.WatchBehaviourListeners.EpisodeComboBoxItem;
import domain.Listeners.WatchBehaviourListeners.MovieComboBoxItem;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * WatchBehaviourLoadDurationListener.java
 * This ActionListener gets the selected media and casts it to either an episode or movie object.
 * It then gets and places the value of when it was watched and for how long in their belonging component.
 */

public class WatchBehaviourLoadDurationListener implements ActionListener {
    private GUI ui;
    private int watchedDuration;

    // Constructor
    public WatchBehaviourLoadDurationListener(GUI ui) {
        this.ui = ui;
    }

    /* OnActionPerformed: Get the selected media and cast it to either an episode or movie object.
    then get and place the value of when it was watched and for how long in their belonging component.*/
    @Override
    public void actionPerformed(ActionEvent e) {
        // Check if input wasn't empty
        if (ui.getCbEditWatchedMediaTitle().getSelectedItem() != null && ui.getCbEditWatchedMediaAccount().getSelectedItem() != null) {
            Object comboBoxItem = ui.getCbEditWatchedMediaTitle().getSelectedItem();
            if (comboBoxItem instanceof MovieComboBoxItem) {
                MovieComboBoxItem movieComboBoxItem = (MovieComboBoxItem) comboBoxItem;
                watchedDuration = movieComboBoxItem.getWatchedDuration();
                ui.getLblEditWatchedMediaDuration().setText(Integer.toString(movieComboBoxItem.getTotalDuration()));
                ui.getTxtEditWatchedMediaDuration().setText((Integer.toString(watchedDuration)));

            }
            if (comboBoxItem instanceof EpisodeComboBoxItem) {
                EpisodeComboBoxItem episodeComboBoxItem = (EpisodeComboBoxItem) comboBoxItem;
                watchedDuration = episodeComboBoxItem.getWatchedDuration();
                ui.getLblEditWatchedMediaDuration().setText(Integer.toString(episodeComboBoxItem.getTotalDuration()));
                ui.getTxtEditWatchedMediaDuration().setText((Integer.toString(watchedDuration)));
            }
        }else{
            return;
        }
    }
}

