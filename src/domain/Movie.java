package domain;

import java.sql.Time;

public class Movie extends Program {

    @Override
    public void setDuration(Time duration) {
        this.duration = duration;
    }
}
