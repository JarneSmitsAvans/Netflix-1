package domain.Listeners.WatchBehaviourListeners;

import domain.Movie;

public class MovieComboBoxItem extends Movie {
    private int id;
    private int watchedDuration;
    private String watchDateAndTime;
    private String title;
    private int totalDuration;

    public MovieComboBoxItem(int watchedDuration, String watchDateAndTime, String title, int totalDuration, int id) {
        this.watchedDuration = watchedDuration;
        this.watchDateAndTime = watchDateAndTime;
        this.title = title;
        this.totalDuration = totalDuration;
        this.id = id;
    }
    @Override
    public int getWatchedDuration() {
        return watchedDuration;
    }
    @Override
    public String getTitle() {
        return title;
    }
    public int getTotalDuration() {
        return totalDuration;
    }
    @Override
    public int getId() {
        return id;
    }
    @Override
    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return title + " watched on: " + watchDateAndTime;
    }
}





