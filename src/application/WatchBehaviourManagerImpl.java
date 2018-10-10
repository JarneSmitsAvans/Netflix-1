package application;

import datastorage.WatchBehaviourDAO;
import domain.Program;
import presentation.GUI;

import java.sql.SQLException;

public class WatchBehaviourManagerImpl {
    private WatchBehaviourDAO watchBehaviourDAO = new WatchBehaviourDAO();

    public void initializeWatchBehaviourComboBoxes(GUI gui) {

    }

    public boolean create(Program program, int profileId) throws SQLException, ClassNotFoundException {
        boolean created = watchBehaviourDAO.create(program, profileId);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

}
