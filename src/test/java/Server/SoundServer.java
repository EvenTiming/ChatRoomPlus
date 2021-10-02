
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

//            serverSocket2 = new ServerSocket(7777);
//            socket2=serverSocket.accept();
            out=socket2.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioInputStream audioStream;
        try{
//            //获取音频输入流
             // audioStream = AudioSystem.getAudioInputStream(new File("/root/CharRoomServer/4.wav"));
          //    audioStream= (AudioInputStream) in;
//            //获取音频的编码对象
//            audioFormat = audioStream.getFormat();
//            //包装音频信息
//            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat,AudioSystem.NOT_SPECIFIED);
//            //使用包装音频信息后的Info类创建源数据行，充当混频器的源
//            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
//
//            sourceDataLine.open(audioFormat);
//            sourceDataLine.start();

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

