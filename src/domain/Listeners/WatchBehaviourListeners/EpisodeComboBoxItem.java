package domain.Listeners.WatchBehaviourListeners;

import domain.Episode;

/**
 * EpisodeComboBoxItem.java
 * This class takes an object of type Episode in a visualises it as a ComboBox item.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class EpisodeComboBoxItem extends Episode {
    private int episodeId;
    private int watchedDuration;
    private String watchDateAndTime;
    private String title;
    private String serieTitle;
    private int totalDuration;

    // Constructor
    public EpisodeComboBoxItem(int episodeId, int watchedDuration, String watchDateAndTime, String title, String serieTitle, int totalDuration) {
        this.episodeId = episodeId;
        this.watchedDuration = watchedDuration;
        this.watchDateAndTime = watchDateAndTime;
        this.title = title;
        this.serieTitle = serieTitle;
        this.totalDuration = totalDuration;
    }

    // Get the episodeId
    public int getEpisodeId() {
        return episodeId;
    }

    // Get the watchedDuration
    @Override
    public int getWatchedDuration() {
        return watchedDuration;
    }

    // Get the title
    @Override
    public String getTitle() {
        return title;
    }

    // Get the serieTitle
    @Override
    public String getSerieTitle() {
        return serieTitle;
    }

    // Get the totalDuration
    public int getTotalDuration() {
        return totalDuration;
    }

    // Prints the EpisodeComboBox object.
    @Override
    public String toString() {
        return serieTitle + " : " + title + " watched on: " + watchDateAndTime;
    }
}
