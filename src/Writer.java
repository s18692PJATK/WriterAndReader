import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Random;

public class Writer extends Thread{

    private final String fileName = "file";
    private final DataOutputStream out = new DataOutputStream(new FileOutputStream(fileName));
    private final RandomAccessFile file =  new RandomAccessFile(fileName,"rw");
    private final FileChannel fileChannel =  file.getChannel();
    private final MappedByteBuffer mappedByteBuffer = fileChannel.map(FileChannel.MapMode.READ_WRITE,0,4);

    public Writer() throws IOException {

    }

    @Override
    public void run(){
        try {
        int counter = 0;
        Random random = new Random();
        int randInt = 0;
        while (counter != 10){
            randInt = random.nextInt(0,10);
            mappedByteBuffer.putInt(randInt);
            fileChannel.write(mappedByteBuffer);
            mappedByteBuffer.flip();
            Thread.sleep(1000);
            counter++;
        }
        fileChannel.close();
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) throws IOException {
        new Writer().start();
        new Reader().start();
    }

}
