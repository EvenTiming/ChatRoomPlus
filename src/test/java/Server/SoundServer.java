
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class SoundServer {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        ServerSocket serverSocket;
        Socket socket;
        ServerSocket serverSocket2;
        Socket socket2;
        InputStream in = null;
        OutputStream out = null;
        byte[] mes;
        try {
            serverSocket = new ServerSocket(8888);
            socket=serverSocket.accept();
            System.out.println("out success connect!");
            serverSocket2 = new ServerSocket(7777);
            socket2=serverSocket2.accept();

            System.out.println("in success connect!");
            in=socket.getInputStream();
            out=socket2.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioInputStream audioStream;
        try{
            int count;
            byte tempBuff[] = new byte[10];
            while((count = in.read(tempBuff,0,tempBuff.length))!=-1) {
                System.out.println(count);
                out.write(tempBuff);
            }
        } catch(IOException ex){
            ex.printStackTrace();
        }

    }


}

