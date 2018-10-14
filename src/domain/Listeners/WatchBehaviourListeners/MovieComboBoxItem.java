package domain.Listeners.WatchBehaviourListeners;

import domain.Movie;

/**
 * MovieComboBoxItem.java
 * This class takes an object of type Movie in a visualises it as a ComboBox item.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class MovieComboBoxItem extends Movie {
    private int id;
    private int watchedDuration;
    private int totalDuration;
    private String watchDateAndTime;
    private String title;

    // Constructor
    public MovieComboBoxItem(int watchedDuration, String watchDateAndTime, String title, int totalDuration, int id) {
        this.watchedDuration = watchedDuration;
        this.watchDateAndTime = watchDateAndTime;
        this.title = title;
        this.totalDuration = totalDuration;
        this.id = id;
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

    // Get the totalDuration
    public int getTotalDuration() {
        return totalDuration;
    }

    // Get the id
    @Override
    public int getId() {
        return id;
    }

    // Set the id
    @Override
    public void setId(int id) {
        this.id = id;
    }

    // Prints the MovieComboBoxItem object.
    @Override
    public String toString() {
        return title + " watched on: " + watchDateAndTime;
    }
}





