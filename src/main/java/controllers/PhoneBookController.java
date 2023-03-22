package controllers;
import repositaries.DBServices;

import javax.swing.*;

public class PhoneBookController {
    DBServices services = new DBServices();

    public void addContact() {
        String fullName = String.valueOf(this.getUserInput("Enter the name"));
        String phone = String.valueOf(this.getUserInput("Enter the phone number"));
        String email = String.valueOf(this.getUserInput("Enter the email address"));
        this.services.handleAddContact(fullName, phone, email);
        this.displayMessage("Contact successfully added!");
    }

    public void removeContact() {
        String name = String.valueOf(this.getUserInput("Enter the name"));
        this.services.handleRemoveContact(name);
        this.displayMessage("Contact successfully deleted!");
    }

    public void searchByName() {
        String fullName = String.valueOf(this.getUserInput("Enter the name"));
        this.displayMessage(services.handleSearchContactsByName(fullName));
    }

    public void searchByPhone() {
        String phone = String.valueOf(this.getUserInput("Enter the phone number"));
        this.displayMessage(services.handleSearchContactsByPhone(phone));
    }

    public void seeAllContacts() {
        this.displayMessage(services.getAllContactsString());

    }

    public void updateContact() {
        String fullName = String.valueOf(this.getUserInput("Enter the name of the contact"));
        String phone = String.valueOf(this.getUserInput("Enter the new phone number"));
        String email = String.valueOf(this.getUserInput("Enter the new email address"));
        this.services.handleUpdateContact(fullName, phone, email);
        this.displayMessage("Contact successfully updated!");
    }

    public void exportToFile() {
        String fileName = String.valueOf(this.getUserInput("Enter the file name"));
        this.displayMessage(this.services.exportContactsToCSV(fileName));
        this.displayMessage("File successfully created!");

    }
    public void importFromFile(){
         String fileName = String.valueOf(this.getUserInput("Enter the file name"));
         this.services.importContactsFromCSV(fileName);
         this.displayMessage("File successfully uploaded!");
    }

    private String getUserInput(String message) {
        return JOptionPane.showInputDialog(null, message);
    }
    private void displayMessage(String message) {
        JOptionPane.showMessageDialog(null, message);
    }
}

