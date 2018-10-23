package domain.Listeners.SerieListeners;

import application.*;
import domain.Episode;
import domain.Profile;
import domain.Serie;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SerieGetRecommendedSerieFromProfileListener implements ActionListener {
    private GUI ui;
    private SerieManagerImpl serieManager ;
    private EpisodeManagerlmpl episodeManager ;
    private ProfileManagerImpl profileManager;
    private AccountManagerImpl accountManager;
    private WatchBehaviourManagerImpl watchBehaviourManager;

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
            String profileName = ui.getCbRecommendedSerieForProfile().getSelectedItem().toString();
            if (profileName != "" && ui.getCbRecommendedSerieForAccount() != null) {

                String accountName = ui.getCbRecommendedSerieForAccount().getSelectedItem().toString();


                int profileID = profileManager.getIdOfProfile(profileName, accountName);
                Serie serie = watchBehaviourManager.getLastWatchedSerie(profileID);

                StringBuilder sb = new StringBuilder();

                sb.append("Aanbevolen series: \n");
                if (serie.getRecommendedSerie() != 0) {
                    Serie recommendedSerie = serieManager.getSerieById(serie.getRecommendedSerie());
                    sb.append("Omdat u als laatste naar " + serie.getTitle() + " hebt gekeken.\n \n");
                    sb.append("Bevelen wij u de volgende serie aan: \n");
                    sb.append(recommendedSerie.getTitle() + " \n");
                } else {
                    sb.append("U heeft nog nergens naar gekeken. Bekijk eerst een serie.");
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
