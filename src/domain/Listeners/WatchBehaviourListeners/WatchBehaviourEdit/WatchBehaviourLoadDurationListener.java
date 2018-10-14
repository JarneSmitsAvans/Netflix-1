package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import domain.Listeners.WatchBehaviourListeners.EpisodeComboBoxItem;
import domain.Listeners.WatchBehaviourListeners.MovieComboBoxItem;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class WatchBehaviourLoadDurationListener implements ActionListener {
    private GUI ui;
    private int watchedDuration;

    public WatchBehaviourLoadDurationListener(GUI ui) {
        this.ui = ui;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
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

