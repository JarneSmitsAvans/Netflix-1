package domain.Listeners.WatchBehaviourListeners;

import application.WatchBehaviourManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourSelectedEpisodeListener implements ActionListener {
    private GUI ui;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    public WatchBehaviourSelectedEpisodeListener(GUI ui) {
        this.ui = ui;
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null) {
            System.out.println("I should do something with the selected episode");
        }
    }
}
