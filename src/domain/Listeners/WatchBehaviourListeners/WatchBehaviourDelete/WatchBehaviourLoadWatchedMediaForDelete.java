package domain.Listeners.WatchBehaviourListeners.WatchBehaviourDelete;

import application.EpisodeManagerlmpl;
import application.ProfileManagerImpl;
import application.SerieManagerImpl;
import application.WatchBehaviourManagerImpl;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WatchBehaviourLoadWatchedMediaForDelete implements ActionListener {
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    public WatchBehaviourLoadWatchedMediaForDelete(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (ui.getCbDeleteWatchedMediaProfile().getSelectedItem() != null && ui.getCbDeleteWatchedMediaAccount().getSelectedItem() != null) {
            try {
                System.out.println("Loading for Deletion");
                int profileID;
                String episodeTitle = "";
                String serieTitle = "";
                this.ui.getCbDeleteWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbDeleteWatchedMediaProfile().getSelectedItem().toString(), ui.getCbDeleteWatchedMediaAccount().getSelectedItem().toString());

            } catch (Exception ex) {

            }
        }

    }
}
