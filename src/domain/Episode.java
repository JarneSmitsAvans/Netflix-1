package domain;

/**
 * Episode.java
 * This class has all variables that an episode in Netflix has.
 * Author: Marc Verwijmeren
 */

public class Episode extends Program {
    private int episodeNumber;
    private int serieNumber;
    private String serieTitle;

    // Get the title of a serie
    public String getSerieTitle() {
        return serieTitle;
    }

    // Set the title of a serie
    public void setSerieTitle(String serieTitle) {
        this.serieTitle = serieTitle;
    }

    // Get the episodeNumber
    public int getEpisodeNumber(){
        return episodeNumber;
    }

    // Set the episodeNumber
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    // Get the serieNumber
    public int getSerieNumber(){
        return serieNumber;
    }

    // Set the serienumber
    public void setSerieNumber(int serieNumber) {
        this.serieNumber = serieNumber;
    }

    @Override
    // Returned the title of an episode
    public String toString() {
        return getTitle();
    }

}
