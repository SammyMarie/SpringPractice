package uk.co.sammy.model;

/**
 * Created by smlif on 05/12/2015.
 */
public class Contact {
    private int contactID;
    private String name;
    private String email;
    private String address;
    private String telephone;

    public Contact() {

    }

    public Contact(int contactID, String name, String email, String address, String telephone) {
        this.contactID = contactID;
        this.name = name;
        this.email = email;
        this.address = address;
        this.telephone = telephone;
    }

    public int getContactID() {
        return contactID;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setContactID(int contactID) {
        this.contactID = contactID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

}
