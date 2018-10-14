package domain;

/**
 * ErrorHandling.java
 * This enum has the string values that are displayed in flexible error messages.
 * <p>
 * Author: Dylan ten Böhmer
 */
public enum ErrorHandling {
    UNEXPECTEDERROR("Er is een onverwachte fout opgetreden"),
    EMPTYINPUT("Een of meerdere velden zijn leeg gelaten of onjuist ingevuld"),
    ALREADYEXISTS("Een instantie met de opgegeven informatie bestaat al voor het geselecteerde object."),
    ISGREATER("De ingevoerde waarde voor de bekeken tijd is groter dan de duur van het geselecteerde media.");
    private String error;

    // Constructor
    ErrorHandling(String error) {
        this.error = error;
    }

    // Prints the error
    public String getError() {
        return error;
    }
}

