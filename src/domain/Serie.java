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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Serie serie = (Serie) o;
        return recommendedSerie == serie.recommendedSerie;
    }

    @Override
    public int hashCode() {
        return Objects.hash(recommendedSerie);
    }

    @Override
    public String toString() {
        return getTitle();
    }


}
