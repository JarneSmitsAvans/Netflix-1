package domain;

import java.sql.Time;

public class Episode extends Program {
    private int episodeNumber;
    private int referenceNumber;


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

//    @Override
//    public void setDuration(Time duration) {
//        this.duration = duration;
//    }


}
