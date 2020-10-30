package Lessen_3.Klient;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

public class Connect {

    private static final String SERVER_HOST = "localhost";
    private static final int SERVER_PORT = 8189;
    private Socket clientSocket;
    private DataInputStream in;
    private DataOutputStream out;
    ChatController chat = new ChatController();

    public ChatController chatController = new ChatController();

    public DataOutputStream getOut() {
        return out;
    }

    public Connect() {
        try {
            clientSocket = new Socket(SERVER_HOST, SERVER_PORT);
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());

            new Thread(() -> {
                try {
                    while (true) {
                        String strFromServer = in.readUTF();
                        System.out.println(in.readUTF());
                        chat.chatSendWindow(strFromServer);
                        if (strFromServer.equalsIgnoreCase("/end")) {
                            break;
                        }
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }).start();


        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
