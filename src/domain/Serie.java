package domain;

import java.security.PublicKey;
import java.sql.Time;
import java.util.*;

public class Serie extends Program {
    private int referenceNumber;

    public int getReferenceNumber(){
        return this.referenceNumber;
    }
    public void setReferenceNumber(int referenceNumber) {
        this.referenceNumber = referenceNumber;
    }

    @Override
    public String toString() {
        return getTitle();
    }


}
