package client;
import javax.sound.sampled.*;
import java.io.*;
import java.net.Socket;

public class Sound {
    //录制音频
    public void getAudio_A() {//提供wave格式信息
        try {
            AudioFormat format = new AudioFormat(22050, 16, 1, true, false);
            TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(format);
            targetDataLine.open(format);
            targetDataLine.start();
            System.out.println("录音中");
            //直接播放出来
            SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(format);
            sourceDataLine.open(format);
            sourceDataLine.start();
            System.out.println("播放中");
            //开子线程进行播放
            byte[] b = new byte[1024];//缓存音频数据
            new Thread(new Runnable() {
                public void run() {
                    int a = 0;
                    while(a!=-1) {
                        System.out.println("录制中");
                        a = targetDataLine.read(b, 0, b.length);//捕获录音数据
                        if(a!=-1) {
                            sourceDataLine.write(b, 0, a);//播放录制的声音
                        }
                    }
                }
            }).start();
        }catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws UnsupportedAudioFileException, IOException, LineUnavailableException {
        InputStream in = null;
        OutputStream out=null;
        try {
           Socket socket = new Socket("1.15.226.19",9999);
           in=socket.getInputStream();
           out=socket.getOutputStream();

        } catch (IOException e) {
            e.printStackTrace();
        }
//        AudioInputStream audioStream = AudioSystem.getAudioInputStream(new File("4.wav"));
//        AudioFormat format = audioStream.getFormat();

        AudioFormat format = new AudioFormat(22050, 16, 1, true, false);
        TargetDataLine targetDataLine = AudioSystem.getTargetDataLine(format);
        targetDataLine.open(format);
        targetDataLine.start();

        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(format);
        sourceDataLine.open(format);
        sourceDataLine.start();

        byte[] b = new byte[1024];
        byte[] c = new byte[1024];
        int a = 0;
        int m=0;
        while(a!=-1) {
           // System.out.println("录制中");
          a = targetDataLine.read(b, 0, b.length);//捕获录音数据

           // System.out.println(b.length);
            if(a!=-1) {
                out.write(b, 0, a);//转发录制的声音
               m=in.read(c,0,c.length);
               sourceDataLine.write(c, 0,m);


            }
        }


    }


}
