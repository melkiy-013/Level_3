package Lessen_3.Klient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import java.sql.SQLException;

public class RegController {

    @FXML
    private Button registr;

    @FXML
    private TextField nIck;

    @FXML
    private PasswordField password;

    @FXML
    private TextField login;

    @FXML
    void initialize(){

        registr.setOnAction(event -> {
            signUpNewUser();
        });
    }

    private void signUpNewUser() {

        DataBaseHandler db = new DataBaseHandler();

        String name = login.getText();
        String pass = password.getText();
        String nick = nIck.getText();

        Users users = new Users(name, pass, nick);

        try {
            db.addUser(users);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}