package domain.Listeners.WatchBehaviourListeners;

import application.*;
import domain.Episode;
import domain.Movie;
import domain.Program;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;

public class WatchBehaviourCreateListener implements ActionListener {
    private WatchBehaviourManagerImpl watchBehaviourManager;
    private MovieManagerImpl movieManager;
    private EpisodeManagerlmpl episodeManager;
    private SerieManagerImpl serieManager;
    private ProfileManagerImpl profileManager;
    private GUI ui;

    public WatchBehaviourCreateListener(GUI ui) {
        this.ui = ui;
        this.movieManager = new MovieManagerImpl();
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.serieManager = new SerieManagerImpl(ui);
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
        this.profileManager = new ProfileManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = ui.getBtnAddWatchBehaviour().getActionCommand();
        String timeWatched;
        int profileID = 0;
        if (!(ui.getTxtAddWatchedMediaDuration().getText() == null || ui.getTxtAddWatchedMediaDuration().getText().isEmpty()) && ui.getTxtAddWatchedMediaDuration().getText().matches("^[0-9]*$")) {
            timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
            try {
                profileID = profileManager.getIdOfProfile(ui.getCbAddWatchedMediaProfile().getSelectedItem().toString(), ui.getCbAddWatchedMediaAccount().getSelectedItem().toString());
            } catch (SQLException e1) {
                e1.printStackTrace();
            } catch (ClassNotFoundException e1) {
                e1.printStackTrace();
            }
            if (command.equals("insertEpisode")) {
                if (ui.getCbAddWatchedMediaEpisode().getSelectedItem() != null) {
                    String selectedEpisode = ui.getCbAddWatchedMediaEpisode().getSelectedItem().toString();
                    String selectedSerie = ui.getCbAddWatchedMediaSerieTitle().getSelectedItem().toString();
                    Serie serie = serieManager.getSerieByName(selectedSerie);
                    int id = serie.getId();
                    episodeManager.setEpisodeList(id);
                    ArrayList<Episode> episodes = episodeManager.getEpisode();
                    for (Episode ep : episodes) {
                        if (ep.getTitle().equals(selectedEpisode)) {
                            if (ep.getSerieNumber() == serie.getId()) {
                                try {
                                    Program program = ep;
                                    program.setDuration(Integer.parseInt(timeWatched));
                                    if (!(program.getDuration() > Integer.parseInt(ui.getLblDurationOfSelectedProgram().getText()))) {
                                        boolean created = watchBehaviourManager.create(program, profileID);
                                        if (created) {
                                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Watchbehaviour has been created.", "Watchbehaviour has been added", JOptionPane.INFORMATION_MESSAGE);
                                            this.ui.getTxtAddWatchedMediaDuration().setText(null);
                                            watchBehaviourManager.initializeWatchBehaviourComboBoxes(ui);
                                        } else {
                                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occured.", "Watchbehaviour has been added", JOptionPane.ERROR_MESSAGE);
                                        }
                                    } else {
                                        JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "The value entered for time watched is greater than the duration of the selected media. Please specify a different value", "Watchbehaviour has not been added", JOptionPane.ERROR_MESSAGE);
                                    }
                                } catch (SQLException e1) {
                                    e1.printStackTrace();
                                } catch (ClassNotFoundException e1) {
                                    e1.printStackTrace();
                                }
                            }
                        }
                    }
                }
            } else if (command.equals("insertMovie")) {
                if (ui.getCbAddWatchedMediaMovieTitle().getSelectedItem() != null) {
                    try {
                        String selectedMovie = ui.getCbAddWatchedMediaMovieTitle().getSelectedItem().toString();
                        timeWatched = ui.getTxtAddWatchedMediaDuration().getText();
                        Movie movie = movieManager.getMovieByTitle(selectedMovie);
                        Program program = movie;
                        program.setDuration(Integer.parseInt(timeWatched));
                        if (!(program.getDuration() > Integer.parseInt(ui.getLblDurationOfSelectedProgram().getText()))) {
                            boolean created = watchBehaviourManager.create(program, profileID);
                            if (created) {
                                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "Watchbehaviour has been created.", "Watchbehaviour has been added", JOptionPane.INFORMATION_MESSAGE);
                                this.ui.getTxtAddWatchedMediaDuration().setText(null);
                                watchBehaviourManager.initializeWatchBehaviourComboBoxes(ui);
                            } else {
                                JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "An unexpected error occured.", "Watchbehaviour has been added", JOptionPane.ERROR_MESSAGE);
                            }
                        } else {
                            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "The value entered for time watched is greater than the duration of the selected media. Please specify a different value", "Watchbehaviour has not been added", JOptionPane.ERROR_MESSAGE);
                        }
                    } catch (SQLException e1) {
                        e1.printStackTrace();
                    } catch (ClassNotFoundException e1) {
                        e1.printStackTrace();
                    }
                }
            }
        } else {
            JOptionPane.showInternalMessageDialog(ui.getMainPanel(), "The value entered for time watched is invalid. Please specify a different value", "Watchbehaviour has not been added", JOptionPane.ERROR_MESSAGE);
        }
    }
}
