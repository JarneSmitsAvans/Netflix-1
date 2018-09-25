package application;

import datastorage.ProfileDAO;
import domain.Profile;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.Period;

public class ProfileManagerImpl {
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

    public static int calculateAge(LocalDate birthDate, LocalDate currentDate) {
        if ((birthDate != null) && (currentDate != null)) {
            return Period.between(birthDate, currentDate).getYears();
        } else {
            return 0;
        }
    }
}
