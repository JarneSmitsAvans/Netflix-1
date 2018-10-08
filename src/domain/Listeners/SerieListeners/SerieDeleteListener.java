package domain.Listeners.SerieListeners;
import application.SerieManagerImpl;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SerieDeleteListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager = new SerieManagerImpl(ui);

    public SerieDeleteListener(GUI ui) {
        this.ui = ui;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            String message = "";

            if(ui.getCbAvgOfWatchedSerie().getSelectedItem() != null){
                Serie serie = (Serie)ui.getCbAvgOfWatchedSerie().getSelectedItem();
                boolean deleted = serieManager.delete(serie.getId());
                message = (deleted) ? "De serie " + serie.getTitle() + " is succesvol verwijderd." : "Er is iets fout gegaan tijdens het verwijderen van de serie" + serie.getTitle() + ". Probeer het nog eens.";
            }
            else{
                message = "U heeft nog geen serie geselecteerd. Selecteer eerst een serie en probeer het dan opnieuw.";
            }

        }
        catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
