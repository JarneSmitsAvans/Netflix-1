package domain;

public class ComboBoxItem {


    public String getWatchDate() {
        return watchDate;
    }

    public ComboBoxItem(int id, String mediaTitle, String episodeTitle, String watchDate, int watchedDuration) {
        this.id = id;
        this.mediaTitle = mediaTitle;
        this.episodeTitle = episodeTitle;
        this.watchDate = watchDate;
        this.watchedDuration = watchedDuration;
    }

    private int id;

    public String getMediaTitle() {
        return mediaTitle;
    }

    private String mediaTitle;

    public String getEpisodeTitle() {
        return episodeTitle;
    }

    private String episodeTitle;
    private String watchDate;

    public int getWatchedDuration() {
        return watchedDuration;
    }

    public void setWatchedDuration(int watchedDuration) {
        this.watchedDuration = watchedDuration;
    }

    private int watchedDuration;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        if (!episodeTitle.isEmpty()) {
            return "Serie: " + mediaTitle + " | Episode Title: " + episodeTitle + " | Watched on: " + watchDate;
        } else {
            return "Movie: " + mediaTitle + " | Watched on: " + watchDate;
        }
    }
}



