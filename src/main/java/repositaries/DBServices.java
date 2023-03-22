package repositaries;
import dto.Contact;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class DBServices {
    private final ArrayList<Contact> contactsList = new ArrayList<>();
    DBConnector connector = new DBConnector();

    public void handleAddContact(String name, String phone, String email) {
        try {
            Statement stmt = connector.getConnection().createStatement();
            stmt.executeUpdate("INSERT INTO contacts (name, phone, email) VALUES ('" + name + "', '" + phone + "', '" + email + "')");
            Contact newContact = new Contact(name, phone, email);
            contactsList.add(newContact);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public void handleRemoveContact(String name) {
        try {
            Statement stmt = connector.getConnection().createStatement();
            stmt.executeUpdate("DELETE FROM contacts WHERE name = '" + name + "'");
            for (Contact contact : contactsList) {
                if (contact.getName().equals(name)) {
                    contactsList.remove(contact);
                    break;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void handleUpdateContact(String name, String phone, String email) {
        try {
            Statement stmt = connector.getConnection().createStatement();
            stmt.executeUpdate(("UPDATE contacts SET phone = '" + phone + "', email = '" + email + "' WHERE name = '" + name + "'"));
            Contact contactToDelete = new Contact(name);
            contactsList.remove(contactToDelete);
            Contact contactToUpdate = new Contact(name, phone, email);
            contactsList.add(contactToUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String handleSearchContactsByName(String name) {
        StringBuilder result = new StringBuilder();
        try {
            Statement stmt = connector.getConnection().createStatement();
            String query = "SELECT * FROM contacts WHERE name LIKE '%" + name + "%'";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String foundName = resultSet.getString("name");
                String foundPhone = resultSet.getString("phone");
                String foundEmail = resultSet.getString("email");
                result.append("Name: ").append(foundName).append(", Phone: ").append(foundPhone).append(", Email: ").append(foundEmail).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }
    public String handleSearchContactsByPhone(String phone) {
        StringBuilder result = new StringBuilder();
        try {
            Statement stmt = connector.getConnection().createStatement();
            String query = "SELECT * FROM contacts WHERE phone LIKE '%" + phone + "%'";
            ResultSet resultSet = stmt.executeQuery(query);
            while (resultSet.next()) {
                String foundName = resultSet.getString("name");
                String foundPhone = resultSet.getString("phone");
                String foundEmail = resultSet.getString("email");
                result.append("Name: ").append(foundName).append(", Phone: ").append(foundPhone).append(", Email: ").append(foundEmail).append("\n");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    public String getAllContactsString() {
        ArrayList<Contact> contactsList = handleGetAllContacts();
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("%-20s %-20s %-20s%n", "Name", "Phone", "Email"));
        sb.append("--------------------------------------------------------------\n");
        for (Contact contact : contactsList) {
            sb.append(String.format("%-20s %-20s %-20s%n", contact.getName(), contact.getPhoneNumber(), contact.getEmail()));
        }
        return sb.toString();
    }
    public ArrayList<Contact> handleGetAllContacts() {
        ArrayList<Contact> contactsList = new ArrayList<>();

        try {
            Statement stmt = connector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contacts");

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");

                Contact contact = new Contact(name, phone, email);
                contactsList.add(contact);
            }

            rs.close();
            stmt.close();
            connector.closeConnection();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return contactsList;
    }

    public void handleExportContactsToCsv() {
        try {
            FileWriter csvWriter = new FileWriter("Contacts.csv");
            Statement stmt = connector.getConnection().createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM contacts");

            csvWriter.append("Name,Phone,Email\n");

            while (rs.next()) {
                String name = rs.getString("name");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                csvWriter.append(name).append(",").append(phone).append(",").append(email).append("\n");
            }

            csvWriter.flush();
            csvWriter.close();
            rs.close();
            stmt.close();
            connector.closeConnection();

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public void importContactsFromCSV(String fileName) {
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();
            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                String name = data[0];
                String phone = data[1];
                String email = data[2];
                Contact contact = new Contact(name, phone, email);
                contactsList.add(contact);
            }
            bufferedReader.close();
            fileReader.close();
            System.out.println("Contacts imported from CSV file: " + fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
