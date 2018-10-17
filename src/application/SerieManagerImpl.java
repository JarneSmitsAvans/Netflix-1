package application;

import datastorage.SerieDAO;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieManagerImpl {
    private SerieDAO serieDAO = new SerieDAO();
    private ArrayList<Serie> serieList;
    private GUI ui;

    public SerieManagerImpl(GUI ui){
        this.ui = ui;
    }

    //Creates a arraylist with all the series
    public void setSerieList() {
        try {
            this.serieList = serieDAO.getSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Serie getSerieByName(String name) {
        Serie serie = null;
        try {
            serie = serieDAO.getSerieByName(name);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serie;
    }

    public Serie getSerieById(int id) {
        Serie serie = null;
        try {
            serie = serieDAO.getSerieById(id);
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serie;
    }

    //Returns a list with all the series
    public ArrayList<Serie> getSerie()
    {
        return serieList;
    }

    //Creates a arraylist with all the comboboxes that need to be filed with series and starts filling them
    public void fillAllSerieCbx() {
        setSerieList();
        JComboBox[] allSerieCb = new JComboBox[]{ui.getCbSerieAvgWatchedByEpisode(),ui.getCbGetUpdateSerie(),ui.getCbGetdeleteSerie(),ui.getCbSerieAvgWatchedBySerie(),ui.getCbCreateEpisodeForSerie(),ui.getCbDeleteEpisodeFromSerie(),ui.getCbUpdateEpisodeOfSerie(),ui.getCbCreateSerieRecomendedSerie(),ui.getCbUpdateSerieRecomendedSerie(),ui.getCbSerieAvgOfSerie()};

        for(int i= 0; i < allSerieCb.length; i++){
            allSerieCb[i].setSelectedItem(-1);
            allSerieCb[i].removeAllItems();
            allSerieCb[i].addItem("Selecteer serie");
            appendComboBox(allSerieCb[i],serieList);
        }
    }

    //Fill al the comboboxes With series
    public void appendComboBox(JComboBox seriecb, ArrayList<Serie> series)
    {
        for (Serie serie : series)
        {
            seriecb.addItem(serie);
        }
    }

    //Returns al boolean if a new serie has been created or not
    public boolean create(Serie serie) throws SQLException, ClassNotFoundException {
        boolean serieCreated = serieDAO.create(serie);
        return serieCreated;
    }

    //Returns al boolean if a new serie has been updated or not
    public boolean update(Serie serie) throws SQLException, ClassNotFoundException {
        boolean serieUpdated = serieDAO.update(serie);
        return serieUpdated;
    }

    //Returns al boolean if a new serie has been deleted or not
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean serieDelete = serieDAO.delete(id);
        return serieDelete;
    }

    //Returns al boolean if a new serie has been deleted or not
    public Serie checkSerieTitle(String serieTitle, int serieId) throws SQLException, ClassNotFoundException {
        Serie checkedSerie = serieDAO.checkSerieTitle(serieTitle,serieId);
        return checkedSerie;
    }


}
