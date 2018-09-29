package application;

import datastorage.MediaDAO;
import domain.Account;
import domain.Serie;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

public class MediaManagerImpl {
    private MediaDAO mediaDAO = new MediaDAO();

    public ArrayList<Serie> getSerie() throws SQLException, ClassNotFoundException {
        // Returns an ArrayList filled with all series in the database.
        ArrayList<Serie> serieList = mediaDAO.getSeries();
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
