package Lessen_3.Server;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientHandler {

    private Server server;
    private Socket socket;
    private DataInputStream in;
    private DataOutputStream out;

    BufferedWriter bf = null;
    private String name;

    public String getName() {
        return name;
    }

    public ClientHandler(Server server, Socket socket) {
        try {
            this.server = server;
            this.socket = socket;
            this.in = new DataInputStream(socket.getInputStream());
            this.out = new DataOutputStream(socket.getOutputStream());
            this.name = "";

            new Thread(() -> {
                try {
                    authentication();
                    history();                      // не могу понять, клиент переподключается каждый раз при
                    readMessages();                 // отправке сообщения
                    System.out.println("123");
                } catch (IOException e) {
                    e.printStackTrace();
                }
                finally {
                    closeConnection();
                }
            }).start();
        } catch (IOException e) {
            throw new RuntimeException("Проблемы при создании обработчика клиента");
        }
    }

    private void history() {
        List<String> list = new ArrayList<>();
        String s;
        try{
            BufferedReader br = new BufferedReader(new FileReader(name + ".txt"));
            while((s = br.readLine()) != null){
                list.add(s);
            }
            for(int i=list.size()-10; i<list.size()+1; i++){
                out.writeUTF(list.get(i));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void authentication() throws IOException {
        while (true){
            String str = in.readUTF();
            if(str.startsWith("/auth")){
                String[] parts = str.split("\\s");
                String nick = server.getAuthService().getNickByLoginPass(parts[1], parts[2]);
                if(nick != null){
                    bf = new BufferedWriter(new FileWriter(nick + ".txt", true));
                    sendMsg("/authok " + nick);
                    name = nick;
                    server.subscribe(this);
                    server.broadcastMsg(name + " зашел в чат");

                    return;
                }
                else {
                    sendMsg("Неверные логин/пароль");
                }
            }
        }
    }

    public void readMessages() throws IOException {

        while (true) {
            String strFromClient = in.readUTF();
            System.out.println("от " + name + ": " + strFromClient);
            bf.append(strFromClient);
            if (strFromClient.equals("/end")) {
                return;
            }
            server.broadcastMsg(name + ": " + strFromClient);
        }
    }

    public void sendMsg(String msg) {
        try {
            out.writeUTF(msg);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeConnection() {
        server.unsubscribe(this);
        server.broadcastMsg(name + " вышел из чата");
        try {
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
