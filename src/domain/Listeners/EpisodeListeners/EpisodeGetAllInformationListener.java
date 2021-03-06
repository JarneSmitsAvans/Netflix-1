package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import application.SerieManagerImpl;
import domain.Episode;
import presentation.GUI;
import sun.management.snmp.jvminstr.JvmOSImpl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * EpisodeGetAllInformationListener.java
 * This ActionListener will show all the information of an episode.
 * Author: Marc Verwijmeren
 */

public class EpisodeGetAllInformationListener implements ActionListener {
    private GUI ui;
    private EpisodeManagerlmpl episodeManager;
    private JComboBox selectedcb;

    // Constructor
    public EpisodeGetAllInformationListener(GUI ui,JComboBox selectedcb) {
        this.ui = ui;
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.selectedcb = selectedcb;
    }

    @Override
    // Fill all the labels with the right data from a episode
    public void actionPerformed(ActionEvent e) {
        // Check if the combobox isn't empty
        if(selectedcb != null && selectedcb.getItemCount() > 0){
            // Get the selected item
            Episode episode = (Episode)selectedcb.getSelectedItem();

            // Fill the labels with data
            ui.getLblEpisodeTitle().setText(episode.getTitle());
            String episodeNumber = Integer.toString(episode.getEpisodeNumber());
            ui.getLblEpisodeNumber().setText(episodeNumber);
            String episodeDuration = Integer.toString(episode.getDuration());
            ui.getLblEpisodeDuration().setText(episodeDuration);

            // Make the lables visible
            ui.getLblEpisodeTitle().setVisible(true);
            ui.getLblEpisodeNumber().setVisible(true);
            ui.getLblEpisodeDuration().setVisible(true);

            ui.getLblEpisodeTitleLabel().setVisible(true);
            ui.getLblEpisodeNumberLabel().setVisible(true);
            ui.getLblEpisodeDurationLabel().setVisible(true);
        }
        else{
            // Make the labels invisible
            ui.getLblEpisodeTitle().setVisible(false);
            ui.getLblEpisodeNumber().setVisible(false);
            ui.getLblEpisodeDuration().setVisible(false);

            ui.getLblEpisodeTitleLabel().setVisible(false);
            ui.getLblEpisodeNumberLabel().setVisible(false);
            ui.getLblEpisodeDurationLabel().setVisible(false);
        }



    }

}
