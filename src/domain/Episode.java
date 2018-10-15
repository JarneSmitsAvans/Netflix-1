package domain;

public class Episode extends Program {
    private int episodeNumber;
    private int serieNumber;
    private String serieTitle;

    public String getSerieTitle() {
        return serieTitle;
    }

    public void setSerieTitle(String serieTitle) {
        this.serieTitle = serieTitle;
    }

    public int getEpisodeNumber(){
        return episodeNumber;
    }
    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getSerieNumber(){
        return serieNumber;
    }
    public void setSerieNumber(int serieNumber) {
        this.serieNumber = serieNumber;
    }

    @Override
    public String toString() {
        return getTitle();
    }

}
