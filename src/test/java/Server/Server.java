//package Server;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    class Thread1 extends Thread{
        Socket socket;
        public Thread1(Socket socket){
            this.socket=socket;
        }
        public void run(){
            System.out.println(socket.getInetAddress()+"scuessly connect!");
            while(true){
                try{
                    InputStream in=socket.getInputStream();
                    byte[] mes=new byte[1024];
                    in.read(mes);
                    System.out.println(new String(mes));
                    OutputStream out=socket.getOutputStream();
                    out.write(mes);
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }
    public static void main(String[] args) {
        Socket socket=null;
            try {
                    ServerSocket serverSocket=new ServerSocket(9999);
                    while(true) {
                        socket = serverSocket.accept();
                        Server s=new Server();
                        Thread1 thread1 = s.new Thread1(socket);
                        thread1.start();
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
