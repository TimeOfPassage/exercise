package nio;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class FileChannelTest {
	private static final Charset charset = Charset.forName("GBK");

	public static void main(String[] args) {
		doRead();
	}

	private static void doRead() {
		try {
			RandomAccessFile randomAccessFile = new RandomAccessFile(
					"E:\\workspace\\exercise\\src\\nio\\filechannel.txt", "rw");
			FileChannel fc = randomAccessFile.getChannel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			while (fc.read(byteBuffer) != -1) {
				byteBuffer.flip();
				System.out.println(charset.decode(byteBuffer));
				byteBuffer.clear();
			}
			randomAccessFile.close();
			fc.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
