package Lessen_3.Klient;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

import java.sql.*;

import static Lessen_3.Klient.Const.*;

public class DataBaseHandler extends Config{

    private static Connection con;
    private static PreparedStatement prSt;
    private static Statement st;
    private static ResultSet res;

    public Connection connection() throws SQLException {
        con = DriverManager.getConnection(url,"root", "Wolf-013");
        return con;
    }

    public void addUser(Users users) throws SQLException {
        String insert = "INSERT INTO" + " " + USERS_TABLE + "("
                + USERS_NAME + "," + USERS_PASSWORD + "," + USERS_NICK + ")" + "VALUES(?,?,?)";
        prSt = connection().prepareStatement(insert);
        prSt.setString(1, users.getName());
        prSt.setString(2, users.getPass());
        prSt.setString(3, users.getNick());
        prSt.executeUpdate();
    }

    public ResultSet getUsers(Users users) throws SQLException {
        res = null;

        String select = "SELECT * FROM " + Const.USERS_TABLE + " WHERE " + Const.USERS_NAME +
                " =? AND " + Const.USERS_PASSWORD + " =?";

        prSt = connection().prepareStatement(select);
        prSt.setString(1, users.getName());
        prSt.setString(2, users.getPass());
        res = prSt.executeQuery();
        return  res;
    }

    public void show() throws SQLException {
        String result = "SELECT * FROM users";
        st = connection().createStatement();
        res = st.executeQuery(result);

        while(res.next()){
            int id = res.getInt(1);
            String name = res.getString(2);
            String pas = res.getString(3);
            System.out.println("id: " + id + " , " + "name: " + name + " , " + "password: " + pas);
        }
    }

    public void delete(Users users) throws SQLException {
        String s = "DELETE FROM users WHERE name LIKE '%" + users.getName() + "%'";
        prSt = connection().prepareStatement(s);
        prSt.executeUpdate();
    }

    public void update(String s1, String s2) throws SQLException {
        String upD = "UPDATE users SET nick = '" + s2 + "' WHERE nick = '" + s1 + "'";
        prSt = connection().prepareStatement(upD);
        prSt.executeUpdate();
    }
}
