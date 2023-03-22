package dto;
public class Contact {
    private final String name;
    private String phoneNumber;
    private String email;

    public Contact(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public Contact(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getPhoneNumber() {
        return phoneNumber;
    }


    public String getEmail() {
        return email;
    }


    @Override
    public String toString() {
        return String.format("%-20s %-20s %-20s", name, phoneNumber, email);
    }
}