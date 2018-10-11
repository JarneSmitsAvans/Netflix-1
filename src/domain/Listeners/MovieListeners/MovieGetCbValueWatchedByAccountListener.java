package domain.Listeners.MovieListeners;

import application.AccountManagerImpl;
import application.MovieManagerImpl;
import domain.Account;
import presentation.GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class MovieGetCbValueWatchedByAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private MovieManagerImpl movieManager;
    private Account account;

    public MovieGetCbValueWatchedByAccountListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.movieManager = new MovieManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            if(ui.getCbWatchedMoviesByAccount().getSelectedItem() != null) {
                String strSelectedAccount = ui.getCbWatchedMoviesByAccount().getSelectedItem().toString();
                account = accountManager.getAccountByName(strSelectedAccount);
                int id = account.getId();
                ArrayList<String> watchedMoviesList = movieManager.watchedMovieByAccountArrayList(id);
                String watchedMovies = "";
                if(!watchedMoviesList.isEmpty()) {
                    Set<String> uniqueMovies = new HashSet<String>(watchedMoviesList);
                    for (String movie : uniqueMovies) {
                        int howManyTimes = Collections.frequency(watchedMoviesList, movie);
                        watchedMovies += howManyTimes + "x " + movie + "\n";
                    }
                } else {
                    watchedMovies += "Er zijn nog geen films bekeken door dit account";
                }
                this.ui.getTxtMoviesWatchedByAccount().setText(watchedMovies);
            } else {
                return;
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
