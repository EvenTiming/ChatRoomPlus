package client;
import java.io.IOException;
import java.net.Socket;
public class ServerConnect {
    public static void main(String[] args) {
        Socket socket = null;
        try {
            socket = new Socket("1.15.226.19",9999);
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(socket);
        System.out.println(socket.isConnected());
    }
}
