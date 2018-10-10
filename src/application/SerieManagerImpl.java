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
            serieList = serieDAO.getSeries();
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

    //Returns a list with all the series
    public ArrayList<Serie> getSerie()
    {
        return serieList;
    }

    //Creates a arraylist with all the comboboxes that need to be filed with series and starts filling them
    public void fillAllSerieCbx() {
        setSerieList();
        JComboBox[] allSerieCb = new JComboBox[]{ui.getCbAvgOfWatchedSerie(),ui.getCbGetUpdateSerie(),ui.getCbGetdeleteSerie(),ui.getCbAvgWatchedSerie(),ui.getCbCreateEpisodeForSerie(),ui.getCbDeleteEpisodeFromSerie(),ui.getCbEditEpisodeOfSerie()};

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

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean updated = serieDAO.delete(id);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }


}
