package application;

import datastorage.SerieDAO;
import domain.Serie;
import presentation.GUI;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieManagerImpl {
    private SerieDAO serieDAO = new SerieDAO();
    private ArrayList<Serie> serieList;
    private GUI gui;

    public SerieManagerImpl(GUI gui){
        this.gui = gui;
    }

    public void setSerieList(){
        // Returns an ArrayList filled with all series in the database.
        try {
            serieList = serieDAO.getSeries();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
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
