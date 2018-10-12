package domain;

public class EpisodeComboBoxItem extends Episode {
    private int episodeId;
    private int watchedDuration;
    private String watchDateAndTime;
    private String title;
    private String serieTitle;
    private int totalDuration;

    public EpisodeComboBoxItem(int episodeId, int watchedDuration, String watchDateAndTime, String title, String serieTitle, int totalDuration) {
        this.episodeId = episodeId;
        this.watchedDuration = watchedDuration;
        this.watchDateAndTime = watchDateAndTime;
        this.title = title;
        this.serieTitle = serieTitle;
        this.totalDuration = totalDuration;
    }
    public int getEpisodeId() {
        return episodeId;
    }
    @Override
    public int getWatchedDuration() {
        return watchedDuration;
    }
    @Override
    public String getTitle() {
        return title;
    }
    @Override
    public String getSerieTitle() {
        return serieTitle;
    }
    public int getTotalDuration() {
        return totalDuration;
    }
    @Override
    public String toString() {
        return serieTitle + " : " + title + " watched on: " + watchDateAndTime;
    }
}
