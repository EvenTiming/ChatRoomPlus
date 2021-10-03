//package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;

public class SoundT {
    private ArrayList<Socket> onlinelist=new ArrayList<Socket>();
    private HashMap<String, Socket> idsocektmap = new HashMap<>();
    InputStream in;
    class Thread1 extends Thread{
        //聊天线程
        Socket socket;
        public Thread1(Socket socket){
            this.socket=socket;
        }
        public void run(){
            //监听是否有新消息发送
            System.out.println(socket.getInetAddress()+"scuessly connect!");
            onlinelist.add(socket);
            try{
                int count;
                in = socket.getInputStream();
                byte tempBuff[] = new byte[1024];
                while((count = in.read(tempBuff,0,tempBuff.length))!=-1) {
                    for(int i=0;i<onlinelist.size();i++){
                        if(onlinelist.get(i).equals(socket))
                            continue;
                        OutputStream out=onlinelist.get(i).getOutputStream();
                        out.write(tempBuff);
                    }
                }
            } catch(IOException ex){
                System.out.println(socket.getLocalAddress()+" is disconnect!");
                onlinelist.remove(socket);
            }

        }
    }

    public static void main(String[] args) {
        Socket socket=null;
        try {

            ServerSocket serverSocket=new ServerSocket(9999);
            while(true) {
                //监听是否有客户端连接
                socket = serverSocket.accept();
                SoundT s=new SoundT();
                Thread1 t=s.new Thread1(socket);
                t.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                socket.close();

            } catch (IOException e) {

                e.printStackTrace();
            }
        }

    }
}
