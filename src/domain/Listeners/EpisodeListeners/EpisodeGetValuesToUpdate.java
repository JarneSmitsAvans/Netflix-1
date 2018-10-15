package domain.Listeners.EpisodeListeners;

import application.EpisodeManagerlmpl;
import domain.Episode;
import presentation.GUI;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EpisodeGetValuesToUpdate implements ActionListener {
    private GUI ui;
    private JComboBox cbSelectedEpisode;
    private EpisodeManagerlmpl episodeManager ;

    // Constructor
    public EpisodeGetValuesToUpdate(GUI ui,JComboBox cbSelectedEpisode) {
        this.ui = ui;
        this.cbSelectedEpisode = cbSelectedEpisode;
        this.episodeManager = new EpisodeManagerlmpl(ui);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(cbSelectedEpisode.getSelectedItem() != "Selecteer serie" && cbSelectedEpisode.getSelectedItem() != null){
            Episode episode = (Episode) cbSelectedEpisode.getSelectedItem();

            ui.getTxtUpdateEpisodeTitle().setText(episode.getTitle());
            String episodeNumber = Integer.toString(episode.getEpisodeNumber());
            ui.getTxtUpdateEpisodeNumber().setText(episodeNumber);
            String episodeDuration = Integer.toString(episode.getDuration());
            ui.getTxtUpdateEpisodeDuration().setText(episodeDuration);
        }
    }
}
