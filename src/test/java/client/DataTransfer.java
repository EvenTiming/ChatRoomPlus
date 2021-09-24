package client;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class DataTransfer {
    public void transstring(Socket socket,String str){
        try {
            OutputStream out= socket.getOutputStream();
            byte[] mes=str.getBytes(StandardCharsets.UTF_8);
            out.write(mes);
          //  out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
