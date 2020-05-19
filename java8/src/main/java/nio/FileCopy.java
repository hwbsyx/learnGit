package nio;

import org.junit.Test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class FileCopy {

    @Test
    public void fileCopy2() throws IOException {
        FileChannel inchannel = FileChannel.open(Paths.get("222.png"), StandardOpenOption.READ);
        FileChannel outchannel = FileChannel.open(Paths.get("444.png"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);
        //inchannel.transferTo(0, inchannel.size(), outchannel);
        outchannel.transferFrom(inchannel, 0, inchannel.size());
        outchannel.close();
        inchannel.close();

    }

    @Test
    public void newFileCopy() throws IOException {
        //使用直接缓冲区完成文件的复制
        FileChannel inchannel = FileChannel.open(Paths.get("222.png"), StandardOpenOption.READ);
        FileChannel outchannel = FileChannel.open(Paths.get("333.png"), StandardOpenOption.WRITE, StandardOpenOption.READ,StandardOpenOption.CREATE);
        //内存映射文件
        MappedByteBuffer inMapBuf = inchannel.map(FileChannel.MapMode.READ_ONLY, 0, inchannel.size());
        MappedByteBuffer outMapBuf = outchannel.map(FileChannel.MapMode.READ_WRITE, 0, inchannel.size());
        //直接对内存进行读写操作。不需要通过通道进行
        byte[] bytes = new byte[inMapBuf.limit()];
        inMapBuf.get(bytes);
        outMapBuf.put(bytes);
        outchannel.close();
        inchannel.close();

    }

    @Test
    public void oldFileCopy() {
        //使用非直接缓冲区进行文件复制
        FileInputStream fis = null;
        FileOutputStream fos = null;
        FileChannel inchannel = null;
        FileChannel outchannel = null;
        try {
            //利用通道完成文件复制
            fis = new FileInputStream("222.png");
            fos = new FileOutputStream("222new.png");

            //获取通道
            inchannel = fis.getChannel();
            outchannel = fos.getChannel();
            //获取指定大小的缓冲区
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            //将通道的数据放入缓冲区
            while (inchannel.read(buffer) != -1) {
                buffer.flip();//切换到写模式
                //将缓冲区的数据写到通道中
                outchannel.write(buffer);
                buffer.clear();//清空缓冲区,重复读取
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (outchannel != null) {
                try {
                    outchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (inchannel != null) {
                try {
                    inchannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
