package domain;

import java.sql.Time;

public class Episode extends Program {
    private int episodeNumber;
    private int serieNumber;

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
