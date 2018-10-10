package domain.Listeners.WatchBehaviourListeners;

import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WatchBehaviourSerieListener implements ActionListener {
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

        this.serieManager.setSerieList();
        ArrayList<Serie> serieArrayList = serieManager.getSerie();
        serieManager.appendComboBox(ui.getCbAddWatchedMediaSerieTitle(), serieArrayList);

        }
    }
