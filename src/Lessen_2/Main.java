package Lessen_2;

import java.sql.*;

public class Main {

    private static final String url = "jdbc:mysql://127.0.0.1:3306/Lessen_2?serverTimezone=UTC";
    private static final String USERS_TABLE = "users";
    private static final String USERS_ID = "idusers";
    private static final String USERS_NAME = "name";
    private static final String USERS_PASSWORD = "password";

    private static Connection con;
    private static PreparedStatement prSt;
    private static Statement st;
    private static ResultSet res;

    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        Main m = new Main();
        //m.show();                                                                     // показать содержимое
        //m.addUser("demo", "222");                                                     // добавить запись
        //m.delete("vvv");                                                              // удалить запись
        m.update("ccc", "test");                                                // изменить значение
    }

    public Connection connection() throws SQLException {
        con = DriverManager.getConnection(url,"root", "Wolf-013");
        return con;
    }

    public void addUser(String name, String password) throws SQLException {
        String find = "SELECT * FROM users WHERE name LIKE '%" + name + "%'";
        String insert = "INSERT INTO" + " " + USERS_TABLE + "("
                + USERS_NAME + "," + USERS_PASSWORD + ")" + "VALUES(?,?)";
        prSt = connection().prepareStatement(insert);
        prSt.setString(1, name);
        prSt.setString(2, password);
        prSt.executeUpdate();
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

    public void delete(String name) throws SQLException {
        String s = "DELETE FROM users WHERE name LIKE '%" + name + "%'";
        prSt = connection().prepareStatement(s);
        prSt.executeUpdate();
    }

    public void update(String s1, String s2) throws SQLException {
        String upD = "UPDATE users SET name = '" + s2 + "' WHERE name = '" + s1 + "'";
        prSt = connection().prepareStatement(upD);
        prSt.executeUpdate();
    }
}
