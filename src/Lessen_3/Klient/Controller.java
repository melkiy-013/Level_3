package Lessen_3.Klient;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Controller {
    @FXML
    private Button registr;

    @FXML
    private PasswordField password;

    @FXML
    private Button voiti;

    @FXML
    private TextField login;

    @FXML
    void initialize() {

        voiti.setOnAction(event -> {


            String loginText = login.getText();
            String loginPassword = password.getText();

            if (!loginText.equals("") && !loginPassword.equals("")) {

                Connect c = new Connect();

                try {
                    c.getOut().writeUTF("/auth " + loginText + " " + loginPassword);
                } catch (IOException e) {
                    e.printStackTrace();
                }


                voiti.getScene().getWindow().hide();

                FXMLLoader loader = new FXMLLoader();
                loader.setLocation(getClass().getResource("/Lessen_3/Klient/klientChat.fxml"));
                try {
                    loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                Parent root = loader.getRoot();
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.showAndWait();

            }else
                System.out.println("Login and Password is empty");
        });



        registr.setOnAction(event -> {
            registr.getScene().getWindow().hide();

            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/Lessen_3/Klient/registration.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();


        });
    }

//        private void loginUser(String loginText, String loginPassword) throws SQLException {
//            DataBaseHandler db = new DataBaseHandler();
//            Users user = new Users();
//            user.setName(loginText);
//            user.setPass(loginPassword);
//            ResultSet res = db.getUsers(user);
//
//            int couter = 0;
//
//            while(res.next()){
//                couter++;
//            }
//            if(couter >=1){
//                System.out.println("Find user");
//            }
//    }
}
