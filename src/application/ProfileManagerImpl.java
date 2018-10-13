package application;

import datastorage.ProfileDAO;
import domain.Profile;
import presentation.GUI;

import javax.swing.*;
import java.sql.SQLException;
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

    public void initializeProfileComboBoxes(GUI gui) throws SQLException, ClassNotFoundException {
        // Empty all comboBoxes should there be any data inside of them.
        gui.getCbDeleteProfile().removeAllItems();
        gui.getCbUpdateSelectedProfile().removeAllItems();
        gui.getCbWatchedProgramsBySelectedProfile().removeAllItems();
        gui.getCbAddWatchedMediaProfile().removeAllItems();
        gui.getCbEditWatchedMediaProfile().removeAllItems();
        gui.getCbDeleteWatchedMediaProfile().removeAllItems();
        gui.getCbWatchedProgramsBySelectedProfile().removeAllItems();

        // Fill the following JComboBoxes with profiles.
        //ArrayList<Profile> profileArrayList = this.getProfiles();
        //this.addProfilesToComboBox(gui.getCbWatchedProgramsBySelectedProfile(), profileArrayList);
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

    public Profile getProfileById(int id) throws SQLException, ClassNotFoundException {
        Profile profile = profileDAO.getProfileById(id);
        return profile;
    }

    public int getIdOfProfile(String profile, String account) throws SQLException, ClassNotFoundException {
        int idOfProfile = profileDAO.getIdOfProfile(profile, account);
        return idOfProfile;
    }

    public void addProfilesToComboBox(JComboBox comboBox, ArrayList<Profile> arrayList) {
        // For each Account in ArrayList, get the account name and add it to the parameter ComboBox
        for (Profile profile : arrayList) {
            comboBox.addItem(profile.getProfileName());
        }
    }

    public ArrayList<Profile> getMatchingProfiles(int id) throws SQLException, ClassNotFoundException {
        ArrayList<Profile> arrayList = profileDAO.getMatchingProfiles(id);
        return arrayList;
    }
}
