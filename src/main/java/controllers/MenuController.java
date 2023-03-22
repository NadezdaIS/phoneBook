package controllers;

import javax.swing.*;
public class MenuController {
    public void start() {
        JOptionPane.showMessageDialog(null, "Phone book is opened");
        this.displayMainMenu();
    }
    private void displayMainMenu()  {
    PhoneBookController controller = new PhoneBookController();
    String[] availableOptions = {"Add contact", "Remove contact", "Find by name", "Find by phone number",
            "Update contact info", "See all contacts", "Export to file", "Exit"};

    String option = (String) JOptionPane.showInputDialog(
            null,
            "Select option",
            null,
            JOptionPane.QUESTION_MESSAGE,
            null,
            availableOptions,
            availableOptions[0]
    );

       switch (option) {
            case "Add contact":
                controller.addContact();
                break;
            case "Remove contact":
                controller.removeContact();
                break;
            case "Find by name":
                controller.searchByName();
                break;
            case "Find by phone number":
                controller.searchByPhone();
                break;
            case "Update contact info":
                controller.updateContact();
                break;
            case "See all contacts":
                controller.seeAllContacts();
                break;
           case "Export to file":
               controller.exportToFile();
               break;
           case "Exit":
                System.exit(0);
        }
        this.displayMainMenu();
    }

}
