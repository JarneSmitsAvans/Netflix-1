package application;

import datastorage.ProfileDAO;
import domain.Account;
import domain.Profile;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;

public class ProfileManagerImpl extends GeneralManager {
    private ProfileDAO profileDAO = new ProfileDAO();
    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        boolean created = profileDAO.create(profile);
        if (created) {
            return true;
        } else {
            return false;
        }
    }
    public boolean update(int id, Profile profile) throws SQLException, ClassNotFoundException {
        boolean updated = profileDAO.update(id, profile);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean deleted = profileDAO.delete(id);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }
    public ArrayList<Profile> getProfiles() throws SQLException, ClassNotFoundException {
        ArrayList<Profile> arrayList = profileDAO.getProfiles();
        return arrayList;
    }
    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
