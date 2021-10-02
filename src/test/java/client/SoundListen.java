package client;
import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
public class SoundListen {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
        InputStream in = null;
        try {
            Socket socket = new Socket("1.15.226.19",7777);
            in=socket.getInputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
        AudioInputStream audioStream;
        AudioFormat audioFormat;
        SourceDataLine sourceDataLine;
        try{
            //获取音频输入流
            AudioFormat format = new AudioFormat(22050, 16, 1, true, false);
            //获取音频的编码对象
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class, format,AudioSystem.NOT_SPECIFIED);
            //使用包装音频信息后的Info类创建源数据行，充当混频器的源
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);
            sourceDataLine.open(format);
            sourceDataLine.start();
            in= (AudioInputStream) in;
            int count;
            byte tempBuff[] = new byte[1024];
            while((count = in.read(tempBuff,0,tempBuff.length))!=-1) {
                //System.out.println(i++);
                sourceDataLine.write(tempBuff, 0, count);
            }
        } catch(LineUnavailableException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }



    }


}
