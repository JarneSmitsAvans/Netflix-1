package domain.Listeners.WatchBehaviourListeners;

import application.MovieManagerImpl;
import application.WatchBehaviourManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourSelectedMovieListener implements ActionListener {
    private GUI ui;
    private MovieManagerImpl movieManager;
    private int duration;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    public WatchBehaviourSelectedMovieListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null) {
            System.out.println("I should do something with the selected movie");
            // TO DO: Get the duration of the selected movie.

        }
    }
}
