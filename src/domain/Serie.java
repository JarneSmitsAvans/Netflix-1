package domain;

public class Serie extends Program {
    private int recommendedSerie;

    public int getRecommendedSerie(){
        return this.recommendedSerie;
    }
    public void setRecommendedSerie(int referenceNumber) {
        this.recommendedSerie = referenceNumber;
    }

    @Override
    public String toString() {
        return getTitle();
    }


}
