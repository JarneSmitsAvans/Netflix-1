package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.EpisodeManagerlmpl;
import application.ProfileManagerImpl;
import application.SerieManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.Episode;
import domain.Movie;
import domain.Program;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviorLoadWatchedMediaForEdit implements ActionListener {
    private SerieManagerImpl serieManager;
    private EpisodeManagerlmpl episodeManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;
    private int duration;

    public WatchBehaviorLoadWatchedMediaForEdit(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (ui.getCbEditWatchedMediaProfile().getSelectedItem() != null) {
            try {
                this.duration = 0;
                int profileID;
                this.ui.getCbEditWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbEditWatchedMediaProfile().getSelectedItem().toString(), ui.getCbEditWatchedMediaAccount().getSelectedItem().toString());
                ArrayList<Program> programs = watchBehaviourManager.getWatchedMedia(profileID);
                for (Program program : programs) {
                    if (program.getClass().equals(new Episode().getClass())) {
                        // do something with the watched episode
                        System.out.println(program.getId());
                        System.out.println(program.getWatchedOn());
                    }
                    if (program.getClass().equals(new Movie().getClass())) {
                        //  // do something with the watched movie
                        System.out.println(program.getTitle());
                        System.out.println(program.getWatchedOn());
                    }
                }
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
