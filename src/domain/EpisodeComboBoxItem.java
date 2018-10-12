package domain;

public class EpisodeComboBoxItem extends Episode {
    public int getEpisodeId() {
        return episodeId;
    }

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

    @Override
    public String getSerieTitle() {
        return serieTitle;
    }

    public int getTotalDuration() {
        return totalDuration;
    }

    public void setEpisodeId(int episodeId) {
        this.episodeId = episodeId;
    }

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

    @Override
    public String toString() {
        return serieTitle + " : " + title + " watched on: " + watchDateAndTime;
    }
}
