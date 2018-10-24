package domain.Listeners.SerieListeners;

import application.*;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

/**
 * SerieGetRecommendedSerieFromProfileListener.java
 * This ActionListener will show all of the recommended series.
 * Author: Marc Verwijmeren
 */

public class SerieGetRecommendedSerieFromProfileListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;

    // Constructor
    public SerieGetRecommendedSerieFromProfileListener(GUI ui) {
        this.ui = ui;
        this.serieManager = new SerieManagerImpl(ui);
        this.episodeManager = new EpisodeManagerlmpl(ui);
        this.profileManager = new ProfileManagerImpl();
        this.accountManager = new AccountManagerImpl();
        this.watchBehaviourManager = new WatchBehaviourManagerImpl();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if (ui.getCbRecommendedSerieForAccount() != null  && ui.getCbRecommendedSerieForProfile().getItemCount() > 0) {

                String profileName = ui.getCbRecommendedSerieForProfile().getSelectedItem().toString();
                String accountName = ui.getCbRecommendedSerieForAccount().getSelectedItem().toString();

                int profileID = profileManager.getIdOfProfile(profileName, accountName);
                HashSet<Serie> serie = watchBehaviourManager.getWatchedSerie(profileID);

                StringBuilder sb = new StringBuilder();

                sb.append("Aanbevolen series: \n");
                sb.append("Bevelen wij u de volgende serie aan: \n");

                if(!serie.isEmpty()){
                    for(Serie getRecommendedSerie : serie) {
                        //sb.append("Omdat u als laatste naar " + getRecommendedSerie.getTitle() + " hebt gekeken.\n \n");
                        sb.append(getRecommendedSerie.getTitle() + " \n");
                    }
                }
                else{
                    sb.append("U heeft nog geen serie bekeken");
                }

                ui.getTxtRecommendedSerie().setText(sb.toString());
            }

            else{
                return;
            }
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
