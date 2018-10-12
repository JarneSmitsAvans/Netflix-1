package domain;

public class MovieComboBoxItem extends Movie {
    @Override
    public int getWatchedDuration() {
        return watchedDuration;
    }

    public String getWatchDateAndTime() {
        return watchDateAndTime;
    }

    @Override
    public String getTitle() {
        return title;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    private int watchedDuration;
    private String watchDateAndTime;
    private String title;
    private int totalDuration;

    @Override
    public int getId() {
        return id;
    }

    @Override
    public void setId(int id) {
        this.id = id;
    }

    private int id;

    public MovieComboBoxItem(int watchedDuration, String watchDateAndTime, String title, int totalDuration, int id) {
        this.watchedDuration = watchedDuration;
        this.watchDateAndTime = watchDateAndTime;
        this.title = title;
        this.totalDuration = totalDuration;
        this.id = id;
    }

    public String getWatchDate() {
        return watchDateAndTime;
    }
    @Override
    public String toString() {
        return title + " watched on: " + watchDateAndTime;
    }
}





