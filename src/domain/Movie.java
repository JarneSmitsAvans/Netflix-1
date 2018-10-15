package domain;

/**
 * Movie.java
 * This class contains a toString method to display a movie.
 * Author: Kim van den Berg
 */

public class Movie extends Program {

    @Override
    public String toString() {
        // Return this when there will be asked for showing the movie.
        return this.getTitle() + "\nTijdsduur: " + this.getDuration() + " minuten\nGenre: " + this.getGenre()
                + "\nTaal: " + this.getLanguage() + "\nLeeftijdsindicatie: " + this.getMinAge() + " jaar\n\n";
    }
}
