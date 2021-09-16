package client;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

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
        try {
            OutputStream out= socket.getOutputStream();
            String str="hello this is a message";
            byte[] mes=str.getBytes(StandardCharsets.UTF_8);
            out.write(mes);
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
