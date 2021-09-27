//package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.sql.*;
public class Server {
    private ArrayList<Socket> onlinelist=new ArrayList<Socket>();
    class Thread1 extends Thread{
        //聊天线程
        Socket socket;
        public Thread1(Socket socket){
            this.socket=socket;
        }
        public void run(){
            System.out.println(socket.getInetAddress()+"scuessly connect!");
            onlinelist.add(socket);
            while(true){
                try{
                    InputStream in=socket.getInputStream();
                    byte[] mes=new byte[1024];
                    in.read(mes);
                    System.out.println(new String(mes));
                    OutputStream out=socket.getOutputStream();
                    out.write(mes);
                }catch (IOException e){
                    System.out.println(socket.getLocalAddress()+" is disconnect!");
                    onlinelist.remove(socket);
                    break;
                }
            }
        }
    }
    class LoginProcess extends Thread{
        Socket socket2;
        private Connection conn;
        public LoginProcess(Socket socket){
            this.socket2=socket;
        }
        public void run() {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/test_demo?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC","root","password");
                InputStream in=socket2.getInputStream();
                byte[] mes=new byte[1024];
                in.read(mes);
                String logm=new String(mes);
                String username=logm.substring(0,logm.indexOf("/"));
                String password=logm.substring(logm.indexOf("/")+1);
                String sql="select uid from User where uid="+username;
                Statement statement = conn.createStatement();
                ResultSet result = statement.executeQuery(sql);
                if(result.wasNull()){
                    System.out.println("no user!");
                }
                else{
                    System.out.println("welcome!");
                    Server s=new Server();
                    Thread1 thread1 = s.new Thread1(socket2);
                    thread1.start();
                }
            } catch (ClassNotFoundException | SQLException | IOException e) {
                e.printStackTrace();
            }

        }
    }
    public static void main(String[] args) {
        Socket socket=null;
            try {

                    ServerSocket serverSocket=new ServerSocket(9999);
                    while(true) {
                        //监听是否有客户端连接
                        socket = serverSocket.accept();
                        Server s=new Server();
                        LoginProcess lg = s.new LoginProcess(socket);
                        lg.start();
                    }
            } catch (IOException e) {
                e.printStackTrace();
            }finally {
                try {
                    socket.close();

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }

    }
}
