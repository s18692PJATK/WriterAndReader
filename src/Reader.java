import java.io.*;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

public class Reader extends Thread{
    private final String fileName = "file";
    private final RandomAccessFile file =  new RandomAccessFile(fileName,"rw");
    private final FileChannel fileChannel =  file.getChannel();
    private final MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,4);


    public Reader() throws IOException {
    }


    @Override
    public void run(){
        int result = 0;
        int current = 0;
        int ttl = 40;
        try {
            while (ttl != 0) {
                fileChannel.read(mappedByteBuffer);
                mappedByteBuffer.flip();
                current = mappedByteBuffer.getInt();
                result += current;
                System.out.println(current);
                System.out.println(result);
                ttl--;
                Thread.sleep(1000);
            }


        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }


    }

}
