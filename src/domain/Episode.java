package domain;

import java.sql.Time;

public class Episode extends Program {
    private int id;
    private int episodeNumber;
    private int referenceNumber;
    private int serieNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getEpisodenumber(){
        return this.episodeNumber;
    }

    public void setEpisodeNumber(int episodeNumber) {
        this.episodeNumber = episodeNumber;
    }

    public int getReferenceNumber(){
        return this.referenceNumber;
    }

    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
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
