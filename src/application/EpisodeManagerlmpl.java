package application;

import datastorage.EpisodeDAO;
import domain.Episode;
import presentation.GUI;

import javax.swing.*;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class EpisodeManagerlmpl {

    private EpisodeDAO episodeDAO = new EpisodeDAO();
    private ArrayList<Episode> episodeList;
    private GUI gui;

    public EpisodeManagerlmpl(GUI gui)
    {
        this.gui = gui;
    }

    //Creates a arraylist with all the episodes of a series
    public void setEpisodeList(int id)
    {
        // Returns an ArrayList filled with all episodes in the database.
        try {
            episodeList = episodeDAO.getEpisodes(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //Returns a list with episodes
    public ArrayList<Episode> getEpisode()
    {
        return episodeList;
    }

    //Returns a list with all the episodes
    public ArrayList<Episode> getAllEpisodes()
    {
        ArrayList<Episode> episodes = null;
        try {
            episodes = episodeDAO.getAllEpisodes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return episodes;

    }

    //Creates a arraylist with all the comboboxes that need to be filed with episodes and starts filling them
    public void fillAllEpisodesCbx(int id)
    {
        setEpisodeList(id);
        JComboBox[] allEpisodecb = {gui.getcbAvgOfWatchedEpisode(),gui.getCbEditEpisodeForSerie()};

        for(int i=0; i < allEpisodecb.length; i++){
            allEpisodecb[i].setSelectedIndex(-1);
            allEpisodecb[i].removeAllItems();
            allEpisodecb[i].setEnabled(true);
            appendComboBox(allEpisodecb[i],episodeList);
        }
    }

    //Fill al the comboboxes With episodes
    public void appendComboBox(JComboBox comboBox, ArrayList<Episode> episodes)
    {
        for ( Episode episode : episodes)
        {
            comboBox.addItem(episode);
        }
    }
}
