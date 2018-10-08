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
        JComboBox[] allSerieCb = {gui.getCbAvgOfWatchedSerie(),gui.getCbGetUpdateSerie(),gui.getCbGetdeleteSerie(),gui.getCbAvgWatchedSerie()};

        for(int i= 0; i < allSerieCb.length; i++){
            allSerieCb[i].removeAllItems();
            allSerieCb[i].addItem("Selecteer serie");
            appendComboBox(allSerieCb[i],serieList);
        }
    }

    public void ReFill() {
        //setSerieList();
        gui.getCbAvgOfWatchedSerie().removeAllItems();
        gui.getCbGetUpdateSerie().removeAllItems();
        gui.getCbGetdeleteSerie().removeAllItems();

        appendComboBox(gui.getCbAvgOfWatchedSerie(),serieList);
        appendComboBox(gui.getCbGetUpdateSerie(),serieList);
        appendComboBox(gui.getCbGetdeleteSerie(),serieList);

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
