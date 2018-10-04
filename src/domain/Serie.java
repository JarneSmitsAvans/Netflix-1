package domain;

import java.security.PublicKey;
import java.sql.Time;
import java.util.*;

public class Serie extends Program {
    private ArrayList<Episode> episodes = new ArrayList<Episode>();

//    public Serie(Episode episode){
//        this.episodes.add(episode);
//    }

    public void addEpisode(Episode episode){
        episodes.add(episode);
    }

//    @Override
//    public void setDuration(Time duration) {
//
//    }
    @Override
    public String toString() {
        String Buffer = this.getTitle() + "\n";
        Buffer += "Afleveringen: \n";
        for(Program ep : episodes){
            Buffer +=  "Aflevering: " + ep.getTitle() + "\n" ;
        }

        return Buffer;
    }


}
