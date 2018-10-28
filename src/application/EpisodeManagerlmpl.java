package application;

import datastorage.EpisodeDAO;
import domain.Episode;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * EpisodeManagerImpl.java
 * This class has methods that do things with Episodes, like;
 * * Implementation for CRUD Operations
 * * Getting data for overviews
 * * Adding Episode objects to swing components.
 * Author: Marc Verwijmeren
 */

public class EpisodeManagerlmpl {
    private EpisodeDAO episodeDAO = new EpisodeDAO();
    private ArrayList<Episode> episodeList;
    private GUI ui;

    // Constructor
    public EpisodeManagerlmpl(GUI ui)
    {
        this.ui = ui;
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

    //Hide all the labels for episodes
    public void hideEpisodeLabels(){
        ui.getLblEpisodeTitle().setVisible(false);
        ui.getLblEpisodeNumber().setVisible(false);
        ui.getLblEpisodeDuration().setVisible(false);

        ui.getLblEpisodeTitleLabel().setVisible(false);
        ui.getLblEpisodeNumberLabel().setVisible(false);
        ui.getLblEpisodeDurationLabel().setVisible(false);

        ui.getLblEpisodeLastNumber().setVisible(false);
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
    public void fillAllEpisodesCbx(int id,JComboBox episodeCbToFill) {
        setEpisodeList(id);

        episodeCbToFill.removeAllItems();
        episodeCbToFill.setEnabled(true);
        appendComboBox(episodeCbToFill,episodeList);

    }

    //Fill al the comboboxes With episodes
    public void appendComboBox(JComboBox comboBox, ArrayList<Episode> episodes) {
        for ( Episode episode : episodes)
        {
            comboBox.addItem(episode);
        }
    }

    // Returns a episode based on the serienumber and title
    public Episode getEpisodeByNameAndSerieID(String episodeTitle, int serieID) throws SQLException, ClassNotFoundException{
        Episode getEpisode = episodeDAO.getEpisodeByNameAndSerieID(episodeTitle,serieID);
        return getEpisode;
    }

    // Get all of the durations from epicodes with the selected serieID
    public Episode getTotalDurationEpisodebySerieID(int serieID)throws SQLException, ClassNotFoundException{
        Episode getEpisode = episodeDAO.getTotalDurationEpisodebySerieID(serieID);
        return getEpisode;
    }

    // Get all of the durations from epicodes with the selected serieID
    public Episode getEpisodeBySerieID(int serieID)throws SQLException, ClassNotFoundException{
        Episode getEpisode = episodeDAO.getEpisodebySerieID(serieID);
        return getEpisode;
    }

    // Returns al boolean if a new serie has been created or not
    public boolean create(Episode episode) throws SQLException, ClassNotFoundException {
        boolean serieCreated = episodeDAO.create(episode);
        return serieCreated;
    }

    // Returns al boolean if a new serie has been updated or not
    public boolean update(Episode episode) throws SQLException, ClassNotFoundException {
        boolean serieUpdated = episodeDAO.update(episode);
        return serieUpdated;
    }

    // Returns al boolean if a new serie has been deleted or not
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean serieDelete = episodeDAO.delete(id);
        return serieDelete;
    }
}
