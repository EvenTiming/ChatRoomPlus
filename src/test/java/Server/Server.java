package Server;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        Socket socekt;
        try {
            ServerSocket serverSocket=new ServerSocket(9999);
            while(true){
              socekt =serverSocket.accept();
              if(socekt.isConnected()){
                  break;
              }
            }
            System.out.println(socekt);
            System.out.println(socekt.getInetAddress());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
