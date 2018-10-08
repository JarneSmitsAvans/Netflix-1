package domain.Listeners.WatchBehaviourListeners;
import application.WatchBehaviourManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourCreateListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private GUI ui;
    public WatchBehaviourCreateListener(GUI ui) {
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
      String command =  ui.getBtnAddWatchBehaviour().getActionCommand();
      String timeWatched;
        /* 1. Differentiate between an episode or movie
           2. Get episode data or movie data
           3. Get duration watched
           4. Get ID of selected profile
        */
        if(command.equals("insertEpisode")){
            if(ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null){
                timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
                String episode = ui.getCbAddWatchedMediaEpisode().getSelectedItem().toString();
                Integer.parseInt(timeWatched);
            }
        }else if (command.equals("insertMovie")){
            if(ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null){
                String movie = ui.getCbAddWatchedMediaMovieTitle().getSelectedItem().toString();
                timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
                Integer.parseInt(timeWatched);
            }
        }


    }
}
