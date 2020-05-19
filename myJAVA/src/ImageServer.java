//建立一个服务器允许多客户端访问，向客户端发送一张图片，并且记录IP地址和已经访问人数
import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashSet;

public class ImageServer extends Thread{
    Socket socket;
    static HashSet<String> ips = new HashSet<String>();  //建立hashset存储IP地址，不会重复记录
    public ImageServer(Socket socket){
        this.socket = socket;
    }

    @Override
    public void run() {

        //向客户端写数据
        try {
            OutputStream outputStream = socket.getOutputStream();
            FileInputStream fileInputStream = new FileInputStream("D:\\12345.jpg");
            //边读边写
            byte [] buf = new byte[1024];
            int length = 0;
            while ((length = fileInputStream.read(buf))!=-1){
                outputStream.write(buf,0,length);
            }
            String ip = socket.getInetAddress().getHostAddress();    //记录IP地址
            if (ips.add(ip)){
                System.out.println("欢迎"+ip+"访问"+"当前访问人数是"+ips.size());
            }


            fileInputStream.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    public static void main(String[] args) throws IOException {
        //建立TCP服务
        ServerSocket serverSocket = new ServerSocket(9090);

        while (true){
            //接受客户端访问
            Socket socket = serverSocket.accept();
            new ImageServer(socket).start();

        }
    }

}

