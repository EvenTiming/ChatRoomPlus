package client;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;

public class Sound {

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException {
         AudioInputStream audioStream;
         AudioFormat audioFormat;
         SourceDataLine sourceDataLine;
        try{
            //获取音频输入流
            audioStream = AudioSystem.getAudioInputStream(new File("4.wav"));
            //获取音频的编码对象
            audioFormat = audioStream.getFormat();
            //包装音频信息
            DataLine.Info dataLineInfo = new DataLine.Info(SourceDataLine.class,
                    audioFormat,AudioSystem.NOT_SPECIFIED);
            //使用包装音频信息后的Info类创建源数据行，充当混频器的源
            sourceDataLine = (SourceDataLine)AudioSystem.getLine(dataLineInfo);

            sourceDataLine.open(audioFormat);
            sourceDataLine.start();

        }catch(UnsupportedAudioFileException ex){
            ex.printStackTrace();
        }catch(LineUnavailableException ex){
            ex.printStackTrace();
        }catch(IOException ex){
            ex.printStackTrace();
        }



        }


}
