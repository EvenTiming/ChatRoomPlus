package Server;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
public class Server {
    public static void main(String[] args) {
        Socket socekt = null;
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
            InputStream in=socekt.getInputStream();
            byte[] mes=new byte[1024];
            int len=in.read(mes);
            System.out.println(new String(mes,0,len));
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
