package domain.Listeners.WatchBehaviourListeners.WatchBehaviourCreate;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * WatchBehaviourSerieListener.java
 * This ActionListener fires should the user decide to add a new watched episode of a serie.
 * It makes all the components required for inserting a newly watched episode visible,
 * and hides all the components for inserting a movie.
 * <p>
 * It then adds all the available series to the serie comboBox
 * <p>
 * Author: Dylan ten Böhmer
 */

public class WatchBehaviourSerieListener implements ActionListener {
    // Constructor
    public WatchBehaviourSerieListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
    }

    private GUI ui;
    private SerieManagerImpl serieManager;

    @Override
    public void actionPerformed(ActionEvent e) {
        // Make movie components invisible
        ui.getCbAddWatchedMediaMovieTitle().setVisible(false);
        ui.getLblMovieTitle().setVisible(false);
        // Make serie components visible
        ui.getLblSerieTitle().setVisible(true);
        ui.getLblWatchedEpisode().setVisible(true);
        ui.getCbAddWatchedMediaSerieTitle().setVisible(true);
        ui.getCbAddWatchedMediaEpisode().setVisible(true);
        // Clear before adding
        ui.getCbAddWatchedMediaSerieTitle().removeAllItems();
        // Add all available series to the series comboBox.
        this.serieManager.setSerieList();
        ArrayList<Serie> serieArrayList = serieManager.getSerie();
        serieManager.appendComboBox(ui.getCbAddWatchedMediaSerieTitle(), serieArrayList);

    }
}
