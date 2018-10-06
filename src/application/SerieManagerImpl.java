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
    private GUI gui;

    public SerieManagerImpl(GUI gui){
        this.gui = gui;
    }

    public ArrayList<Serie> setSerieList() {
        // Returns an ArrayList filled with all series in the database.
        try {
            serieList = serieDAO.getSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return serieList;
    }

    public int getSerie(int id) {

        return id;
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

    public ArrayList<Serie> getSerie()  {
        return serieList;
    }

    public void fillAllSerieCbx(){
        setSerieList();
        appendComboBox(gui.getCbAvgOfWatchedSerie(),serieList);
        appendComboBox(gui.getcbAvgWatchedSerie(),serieList);
    }

    public void appendComboBox(JComboBox comboBox, ArrayList<Serie> series)
    {
        for (Serie serie : series)
        {
            comboBox.addItem(serie);
        }
    }


}
