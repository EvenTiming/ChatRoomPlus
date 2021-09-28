package client;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import java.sql.*;
public class ServerConnect {
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection conn;
        Class.forName("com.mysql.cj.jdbc.Driver");
        conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/ChatRoom?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC", "root", "password");
        System.out.println(conn.isClosed());
    }
}
