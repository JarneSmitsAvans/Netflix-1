package domain.Listeners.WatchBehaviourListeners;

import application.MovieManagerImpl;
import application.SerieManagerImpl;
import domain.Movie;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class WatchBehaviourRadioButtonListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager;
    private MovieManagerImpl movieManager;

    public WatchBehaviourRadioButtonListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(this.ui);
        this.movieManager = new MovieManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (e.getActionCommand().equals("showEpisodeCb")) {
                this.ui.getCbAddWatchedMediaTitle().setActionCommand("fillWithEpisodes");
                ui.getCbAddWatchedMediaTitle().removeAllItems();
                ui.getCbAddWatchedMediaEpisode().removeAllItems();
                ArrayList<Serie> serieArrayList = this.serieManager.setSerieList();
                serieManager.appendComboBox(ui.getCbAddWatchedMediaTitle(), serieArrayList);
                this.ui.getLblWatchedEpisode().setVisible(true);
                this.ui.getCbAddWatchedMediaEpisode().setVisible(true);
                this.ui.getCbAddWatchedMediaTitle().setSelectedItem(null);
                ui.getCbAddWatchedMediaEpisode().removeAllItems();
            } else if (e.getActionCommand().equals("hideEpisodeCb")) {
                ui.getCbAddWatchedMediaTitle().removeAllItems();
                ui.getCbAddWatchedMediaEpisode().removeAllItems();
                ArrayList<Movie> movieArrayList = this.movieManager.getMovies();
                movieManager.addMoviesToComboBox(ui.getCbAddWatchedMediaTitle(), movieArrayList);
                this.ui.getLblWatchedEpisode().setVisible(false);
                this.ui.getCbAddWatchedMediaEpisode().setVisible(false);
                this.ui.getCbAddWatchedMediaTitle().setSelectedItem(null);

                ui.getCbAddWatchedMediaEpisode().removeAllItems();
            }
        } catch (Exception el) {
            el.printStackTrace();
        }
    }
}
