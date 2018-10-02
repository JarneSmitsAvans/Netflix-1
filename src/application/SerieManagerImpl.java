package application;

import datastorage.SerieDAO;
import domain.Serie;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class SerieManagerImpl {
    private SerieDAO serieDAO = new SerieDAO();

    public ArrayList<Serie> getSerie() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all series in the database.
        ArrayList<Serie> serieList = serieDAO.getSeries();
        return serieList;
    }

    public void appendComboBox(JComboBox comboBox, ArrayList<Serie> series)
    {
        for (Serie serie : series)
        {
            comboBox.addItem(serie.getTitle());
        }
    }
}
