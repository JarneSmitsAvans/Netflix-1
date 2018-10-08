package domain;

import java.sql.SQLException;

public class Movie extends Program {

    @Override
    public String toString() {
        return this.getTitle() + "\nTijdsduur: " + this.getDuration() + " minuten\nGenre: " + this.getGenre()
                + "\nTaal: " + this.getLanguage() + "\nLeeftijdsindicatie: " + this.getMinAge() + " jaar\n\n";
    }
}
