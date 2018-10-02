package domain;

import java.time.LocalDate;

public class Profile {
    private String profileName;
    private LocalDate dateOfBirth;
    private int accountNumber;
    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getProfileName() {
        return profileName;
    }

    public void setProfileName(String profileName) {
        this.profileName = profileName;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String toString() {
        return "Profile{" +
                "profileName='" + profileName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", accountNumber=" + accountNumber +
                '}';
    }
}
