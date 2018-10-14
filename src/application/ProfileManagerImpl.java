package application;

import datastorage.ProfileDAO;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * ProfileManagerImpl.java
 * <p>
 * This class has methods that do things with profiles, like;
 * * Implementation for CRUD Operations
 * * Getting data for overviews
 * * Adding profile objects to swing components.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class ProfileManagerImpl extends GeneralManager {
    // Initialize a new profileDAO object.
    private ProfileDAO profileDAO = new ProfileDAO();

    // Sends an object of type Profile to the Profile DAO that needs to be inserted into the database.
    public boolean create(Profile profile) throws SQLException, ClassNotFoundException {
        boolean created = profileDAO.create(profile);
        if (created) {
            return true;
        } else {
            return false;
        }
    }

    /*Empty all ComboBoxes should there be any data inside of them so that
    there won't be double data inside ComboBoxes when we re-fill them. Then, re-fill them.*/
    public void initializeProfileComboBoxes(GUI gui) {
        gui.getCbDeleteProfile().removeAllItems();
        gui.getCbUpdateSelectedProfile().removeAllItems();
        gui.getCbWatchedProgramsBySelectedProfile().removeAllItems();
        gui.getCbAddWatchedMediaProfile().removeAllItems();
        gui.getCbEditWatchedMediaProfile().removeAllItems();
        gui.getCbDeleteWatchedMediaProfile().removeAllItems();
        gui.getCbWatchedProgramsBySelectedProfile().removeAllItems();
    }

    // Sends an object of type Profile to the Profile DAO that needs to be updated in the database.
    public boolean update(int id, Profile profile) throws SQLException, ClassNotFoundException {
        boolean updated = profileDAO.update(id, profile);
        if (updated) {
            return true;
        } else {
            return false;
        }
    }

    // Sends an object of type Profile the Profile DAO that needs to be deleted from the database.
    public boolean delete(int id) throws SQLException, ClassNotFoundException {
        boolean deleted = profileDAO.delete(id);
        if (deleted) {
            return true;
        } else {
            return false;
        }
    }

    // Returns an ArrayList filled with all profiles in the database.
    public ArrayList<Profile> getProfiles() throws SQLException, ClassNotFoundException {
        ArrayList<Profile> arrayList = profileDAO.getProfiles();
        return arrayList;
    }

    // Returns the requested Profile by id and parses it to an object of Profile type.
    public Profile getProfileById(int id) throws SQLException, ClassNotFoundException {
        Profile profile = profileDAO.getProfileById(id);
        return profile;
    }

    // Returns the id of the profile that matches the parameter profile and parameter account.
    public int getIdOfProfile(String profile, String account) throws SQLException, ClassNotFoundException {
        int idOfProfile = profileDAO.getIdOfProfile(profile, account);
        return idOfProfile;
    }

    // For each Profile in ArrayList, get the profile name and add it to the parameter comboBox
    public void addProfilesToComboBox(JComboBox comboBox, ArrayList<Profile> arrayList) {
        // For each Account in ArrayList, get the account name and add it to the parameter ComboBox
        for (Profile profile : arrayList) {
            comboBox.addItem(profile.getProfileName());
        }
    }

    // Returns an ArrayList filled with all profiles in the database that belong to the parameter account id.
    public ArrayList<Profile> getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Profile> arrayList = profileDAO.getMatchingProfiles(id);
        return arrayList;
    }
}
