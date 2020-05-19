import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class ImageClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket(InetAddress.getLocalHost(),9090);
        //获取socket的输入流对象
        InputStream inputStream = socket.getInputStream();
        //获取输出流对象
        FileOutputStream fileOutputStream = new FileOutputStream("E:\\12345.jpg");
        byte [] buf = new byte[1024];
        int length = 0;
        while ((length = inputStream.read(buf))!=-1){
            fileOutputStream.write(buf,0,length);
        }
        fileOutputStream.close();
        socket.close();

    }
}
