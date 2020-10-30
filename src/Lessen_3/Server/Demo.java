package Lessen_3.Server;

import Lessen_3.Klient.Const;
import Lessen_3.Klient.Users;

import java.sql.*;

public class Demo {

    public static final String USERS_TABLE = "users";
    public static final String USERS_NAME = "name";
    public static final String USERS_PASSWORD = "password";
    public static final String USERS_NICK = "nick";

    protected String url = "jdbc:mysql://127.0.0.1:3306/Lessen_2?serverTimezone=UTC";
    protected String bdUser = "root";
    protected String bdPass = "Wolf-013";

    private static Connection con;
    private static PreparedStatement prSt;
    private static Statement st;
    private static ResultSet res;

    public Connection connection() throws SQLException {
        con = DriverManager.getConnection(url,"root", "Wolf-013");
        return con;
    }

    public ResultSet getUsers(String name, String pass) throws SQLException {
        res = null;

        String select = "SELECT nick FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_NAME +
                " =? AND " + Const.USERS_PASSWORD + " =?";

        prSt = connection().prepareStatement(select);
        prSt.setString(1, name);
        prSt.setString(2, pass);
        res = prSt.executeQuery();
        return  res;
    }

    public static void main(String[] args) throws SQLException {
        Demo d  = new Demo();
        String s = null;
        ResultSet rs = d.getUsers("bob", "123");
        while(rs.next()){
            s = res.getString("nick");
        }
        System.out.println(s);
    }
}
