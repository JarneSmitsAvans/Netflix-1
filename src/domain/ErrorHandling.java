package domain;

public enum ErrorHandling {
    UNEXPECTEDERROR("Er is een onverwachte fout opgetreden"),
    EMPTYINPUT("Een of meerdere velden zijn leeg gelaten of onjuist ingevuld"),
    ALREADYEXISTS("Een profiel met de opgegeven naam bestaat al voor het geselecteerde account."),
    ISGREATER("De ingevoerde waarde voor de bekeken tijd is groter dan de duur van het geselecteerde media.");
    private String error;

    ErrorHandling(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}

