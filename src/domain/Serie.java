package domain;

import java.util.Objects;

/**
 * Serie.java
 * This class has all variables that a serie in Netflix has.
 * Author: Marc Verwijmeren
 */

public class Serie extends Program {
    private int recommendedSerie;

    // Sets a recommendedSerie
    public int getRecommendedSerie(){
        return this.recommendedSerie;
    }

    // Gets a recommendedSerie
    public void setRecommendedSerie(int referenceNumber) {
        this.recommendedSerie = referenceNumber;
    }

    @Override
    // Returned the title of a serie
    public String toString() {
        return getTitle();
    }


}
