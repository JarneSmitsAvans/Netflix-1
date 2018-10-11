package domain.Listeners.WatchBehaviourListeners.WatchBehaviourEdit;

import application.EpisodeManagerlmpl;
import application.ProfileManagerImpl;
import application.SerieManagerImpl;
import application.WatchBehaviourManagerImpl;
import domain.*;
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
                String episodeTitle = "";
                String serieTitle = "";
                this.ui.getCbEditWatchedMediaTitle().removeAllItems();
                profileID = profileManager.getIdOfProfile(ui.getCbEditWatchedMediaProfile().getSelectedItem().toString(), ui.getCbEditWatchedMediaAccount().getSelectedItem().toString());
                ArrayList<Program> programs = watchBehaviourManager.getWatchedMedia(profileID);
                for (Program program : programs) {
                    if (program.getClass().equals(new Episode().getClass())) {
                        episodeManager.setEpisodeList(program.getId());
                        ArrayList<Episode> episodes = episodeManager.getEpisode();
                        for (Episode ep : episodes) {
                            if (ep.getId() == program.getId()) {
                                episodeTitle = ep.getTitle();
                                serieManager.setSerieList();
                                ArrayList<Serie> series = serieManager.getSerie();
                                for (Serie serie : series) {
                                    if (serie.getId() == ep.getSerieNumber()) {
                                        serieTitle = serie.getTitle();
                                    }
                                }
                            }
                        }
                        ui.getCbEditWatchedMediaTitle().addItem(new ComboBoxItem(program.getId(), serieTitle, episodeTitle, program.getWatchedOn(), program.getWatchedDuration()));
                    }
                    if (program.getClass().equals(new Movie().getClass())) {
                        ui.getCbEditWatchedMediaTitle().addItem(new ComboBoxItem(0, program.getTitle(), "", program.getWatchedOn(), program.getWatchedDuration()));
                    }
                }
                ui.getCbEditWatchedMediaTitle().setSelectedItem(null);
                ui.getLblEditWatchedMediaDuration().setText("0");
                ui.getTxtEditWatchedMediaDuration().setText("0");
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
        }
    }
}
