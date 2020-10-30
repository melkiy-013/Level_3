package Lessen_3.Klient;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import java.io.IOException;

public class ChatController {

    Connect con ;

    @FXML
    private Button sey;

    @FXML
    public TextArea window;

    @FXML
    private TextField text;

    @FXML
    void initialize(){
        sey.setOnAction(event -> {
            window.appendText("Вы: " + text.getText());
            String s = text.getText();
            con = new Connect();
            try {
                con.getOut().writeUTF(s);
            } catch (IOException e) {
                e.printStackTrace();
            }
            text.clear();
        });
    }

    void chatSendWindow(String s){
        window.appendText(s + "\n");
    }
}
