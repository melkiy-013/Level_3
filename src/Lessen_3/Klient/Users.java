package Lessen_3.Klient;

public class Users {

    private String name;
    private String pass;
    private String nick;

    public Users() {

    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {

        return name;
    }

    public String getPass() {
        return pass;
    }

    public String getNick() {
        return nick;
    }

    public Users(String name, String pass, String nick) {

        this.name = name;
        this.pass = pass;
        this.nick = nick;
    }
}
