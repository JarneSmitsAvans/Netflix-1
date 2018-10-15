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

/**
 * MovieGetCbValueWatchedByAccountListener.java
 * This ActionListener will get the movies who has been watched by the selected Account.
 * Author: Kim van den Berg
 */

public class MovieGetCbValueWatchedByAccountListener implements ActionListener {
    private GUI ui;
    private AccountManagerImpl accountManager;
    private MovieManagerImpl movieManager;
    private Account account;

    // Constructor
    public MovieGetCbValueWatchedByAccountListener(GUI ui) {
        this.ui = ui;
        this.accountManager = new AccountManagerImpl();
        this.movieManager = new MovieManagerImpl();
        this.account = new Account();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            // If selected combobox item is not null, get watched movies by account.
            if(ui.getCbWatchedMoviesByAccount().getSelectedItem() != null) {
                // Get account
                String strSelectedAccount = ui.getCbWatchedMoviesByAccount().getSelectedItem().toString();
                // Get account by name.
                account = accountManager.getAccountByName(strSelectedAccount);
                // Get id of selected account
                int id = account.getId();
                // Get movieTitles who has been watched by selected account.
                ArrayList<String> watchedMoviesList = movieManager.watchedMovieByAccountArrayList(id);
                String watchedMovies = "";
                // If the arrayList is not empty, get frequency and title
                if(!watchedMoviesList.isEmpty()) {
                    // Get all unique movies from the list.
                    Set<String> uniqueMovies = new HashSet<String>(watchedMoviesList);
                    for (String movie : uniqueMovies) {
                        // Get frequency of current movie
                        int howManyTimes = Collections.frequency(watchedMoviesList, movie);
                        // Add rule to String watchedMovies.
                        watchedMovies += howManyTimes + "x " + movie + "\n";
                    }
                } else {
                    // If arraylist is empty show this.
                    watchedMovies += "Er zijn nog geen films bekeken door dit account";
                }
                // Fill textpane with variable watchedMovies.
                this.ui.getTxtMoviesWatchedByAccount().setText(watchedMovies);
            } else {
                // If combobox item is null.
                this.ui.getTxtMoviesWatchedByAccount().setText("Er is geen juist item gekozen.");
            }
        } catch (Exception ex) {
            // If something went wrong..
            this.ui.getTxtMoviesWatchedByAccount().setText("Er is iets fout gegaan bij het ophalen van de films.");
        }
    }
}
