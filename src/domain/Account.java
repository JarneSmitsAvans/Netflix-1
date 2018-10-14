package domain;
import java.util.List;

/**
 * Account.java
 * This class has all variables that an account in Netflix has.
 * <p>
 * Author: Dylan ten Böhmer
 */

public class Account {
    private int id;
    private String name;
    private String address;
    private String residence;
    private List<Profile> profiles;

    // Get the id
    public int getId() {
        return id;
    }

    // Set the id
    public void setId(int id) {
        this.id = id;
    }

    // Get the name
    public String getName() {
        return name;
    }

    // Set the name
    public void setName(String name) {
        this.name = name;
    }

    // Get the address
    public String getAddress() {
        return address;
    }

    // Set the address
    public void setAddress(String address) {
        this.address = address;
    }

    // Get the residence
    public String getResidence() {
        return residence;
    }

    // Set the residence
    public void setResidence(String residence) {
        this.residence = residence;
    }

    // Get the list of profiles
    public List<Profile> getProfiles() {
        return profiles;
    }

    // Set the list of profiles
    public void setProfiles(List<Profile> profiles) {
        this.profiles = profiles;
    }

    // Prints the Account object
    @Override
    public String toString() {
        return "Account{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", residence='" + residence + '\'' +
                ", profiles=" + profiles +
                ", id=" + id +
                '}';
    }
}


