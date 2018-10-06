package domain;

import java.security.PublicKey;
import java.sql.Time;
import java.util.*;

public class Serie extends Program {
    private int id;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return getTitle();
    }


}
