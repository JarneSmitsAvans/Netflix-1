package domain;

import java.time.LocalDate;

public class Profile {
    private String profileName;
    private LocalDate dateOfBirth;
    private Profile profile;

    public Profile(String profileName, LocalDate dateOfBirth, Profile profile) {
        this.profileName = profileName;
        this.dateOfBirth = dateOfBirth;
        this.profile = profile;
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

    public void setDateOfBith(LocalDate dateOfBith) {
        this.dateOfBirth = dateOfBith;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }
}
