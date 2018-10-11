package domain;
public abstract class Program {
    private String title;
    private int duration;
    private String genre;
    private String language;
    private int minAge;
    private int id;
    private String watchedOn;
    private int watchedDuration;

    public int getWatchedDuration() {
        return watchedDuration;
    }

    public void setWatchedDuration(int watchedDuration) {
        this.watchedDuration = watchedDuration;
    }

    public String getWatchedOn() {
        return watchedOn;
    }

    public void setWatchedOn(String watchedOn) {
        this.watchedOn = watchedOn;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return this.title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public int getDuration() {
        return this.duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getGenre() {
        return this.genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return this.language;
    }
    public void setLanguage(String language) {
        this.language = language;
    }

    public int getMinAge() {
        return this.minAge;
    }
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

}
