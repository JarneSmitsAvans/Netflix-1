package domain;

/**
 * Program.java
 * This abstract class has generic variables that every program in Netflix has.
 * <p>
 * Author: Dylan ten Böhmer
 */

public abstract class Program {
    private String title;
    private String genre;
    private String language;
    private String watchedOn;
    private int duration;
    private int minAge;
    private int id;
    private int watchedDuration;

    // Get the watchedDuration
    public int getWatchedDuration() {
        return watchedDuration;
    }

    // Set the watchedDuration
    public void setWatchedDuration(int watchedDuration) {
        this.watchedDuration = watchedDuration;
    }

    // Get the watchedOn
    public String getWatchedOn() {
        return watchedOn;
    }

    // Set the watchedOn
    public void setWatchedOn(String watchedOn) {
        this.watchedOn = watchedOn;
    }

    // Get the id
    public int getId() {
        return id;
    }

    // Set the id
    public void setId(int id) {
        this.id = id;
    }

    // Get the title
    public String getTitle() {
        return this.title;
    }

    // Set the title
    public void setTitle(String title) {
        this.title = title;
    }

    // Get the duration
    public int getDuration() {
        return this.duration;
    }

    // Set the duration
    public void setDuration(int duration) {
        this.duration = duration;
    }

    // Get the genre
    public String getGenre() {
        return this.genre;
    }

    // Set the genre
    public void setGenre(String genre) {
        this.genre = genre;
    }

    // Get the language
    public String getLanguage() {
        return this.language;
    }

    // Set the language
    public void setLanguage(String language) {
        this.language = language;
    }

    // Get the language
    public int getMinAge() {
        return this.minAge;
    }

    // Set the language
    public void setMinAge(int minAge) {
        this.minAge = minAge;
    }

}
