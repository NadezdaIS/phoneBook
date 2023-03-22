import controllers.MenuController;
import repositaries.DBConnector;

public class Main {
    public static void main(String[] args) {
        MenuController menu = new MenuController();
        menu.start();
//        DBConnector connect = new DBConnector();
//        connect.getConnection();
    }
}
