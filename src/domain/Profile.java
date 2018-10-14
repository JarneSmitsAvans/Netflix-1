package domain;
import java.util.Date;

/**
 * Profile.java
 * This class has all variables that a profile in Netflix has.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class Profile {
    private int profileID;
    private int accountNumber;
    private String profileName;
    private Date dateOfBirth;

    // Get the accountNumber
    public int getAccountNumber() {
        return accountNumber;
    }

    // Set the accountNumber
    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    // Get the profileName
    public String getProfileName() {
        return profileName;
    }

    // Set the profileName
    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    // Get the dateOfBirth
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    // Set the dateOfBirth
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    // Get the profileID
    public int getProfileID() {
        return profileID;
    }

    // Set the profileID
    public void setProfileID(int profileID) {
        this.profileID = profileID;
    }

    // Prints the Profile object.
    @Override
    public String toString() {
        return "Profile{" +
                "profileName='" + profileName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
