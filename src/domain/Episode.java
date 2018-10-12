package domain;

public class Episode extends Program {
    private int episodeNumber;
    private int serieNumber;

    public int getEpisodeNumber() {
        return episodeNumber;
    }

    public String getSerieTitle() {
        return serieTitle;
    }

    public void setSerieTitle(String serieTitle) {
        this.serieTitle = serieTitle;
    }

    private String serieTitle;

    public int getEpisodenumber(){
        return this.episodeNumber;
    }
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSerieNumber(){
        return this.serieNumber;
    }
    public void setSerieNumber(int serieNumber) {
        this.serieNumber = serieNumber;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
