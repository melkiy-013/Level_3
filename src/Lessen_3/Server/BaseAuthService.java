package Lessen_3.Server;

import Lessen_3.Klient.Const;

import java.sql.*;

public class BaseAuthService implements AuthService {

    public static final String USERS_TABLE = "users";
    public static final String USERS_NAME = "name";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_NICK = "nick";

    protected String url = "jdbc:mysql://127.0.0.1:3306/Lessen_2?serverTimezone=UTC";
    protected String bdUser = "root";
    protected String bdPass = "Wolf-013";

    private  Connection con;
    private  PreparedStatement prSt;
    private  Statement st;
    private  ResultSet res;

    public Connection connection() throws SQLException {
        con = DriverManager.getConnection(url,"root", "Wolf-013");
        return con;
    }

    public ResultSet getUsers(String name, String pass) throws SQLException {
        res = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_NAME +
                " =? AND " + Const.USERS_PASSWORD + " =?";

        prSt = connection().prepareStatement(select);
        prSt.setString(1, name);
        prSt.setString(2, pass);
        res = prSt.executeQuery();
        return  res;
    }


    @Override
    public void start() {
        System.out.println("Сервис аутентификации запущен");
    }

    @Override
    public String getNickByLoginPass(String login, String pass) {
        String s = null;
        try {
            ResultSet rs = getUsers(login, pass);
            while (rs.next()){
                String name = rs.getString("name");
                String pas = rs.getString("password");
                String nick = rs.getString("nick");
                if(name.equals(login) && pas.equals(pass)){
                    return nick;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public void stop() {
        System.out.println("Сервис аутентификации остановлен");
    }
}
