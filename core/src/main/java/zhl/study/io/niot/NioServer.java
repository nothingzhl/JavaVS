package zhl.study.io.niot;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channel;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SelectableChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

import com.sun.tools.classfile.ConstantPool;

import lombok.extern.slf4j.Slf4j;
import sun.nio.ch.ChannelInputStream;

/**
 * nio server test
 */
public class NioServer {

    public final static int port = 9999;

    private static Selector selector;

    //调整缓存的大小可以看到打印输出的变化a
    private ByteBuffer readBuffer = ByteBuffer.allocate(1024);
    private ByteBuffer sendBuffer = ByteBuffer.allocate(1024);

    static {
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(selector);
        }
    }

    private static void close(Closeable closeable) {
        try {
            closeable.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void start() {

        try (ServerSocketChannel ssc = ServerSocketChannel.open()) {
            selector = Selector.open();

            ssc.configureBlocking(false);
            ssc.bind(new InetSocketAddress(port));
            ssc.register(selector, SelectionKey.OP_ACCEPT);

            while (!Thread.currentThread().isInterrupted()) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();

                    if (!key.isValid()) {
                        iterator.remove();
                        continue;
                    }

                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    } else {
                        iterator.remove();
                    }
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void write(SelectionKey key) throws IOException {
        SocketChannel socketChannel = (SocketChannel) key.channel();

        InputStream in = System.in;

        System.out.println("准备读入");
        ReadableByteChannel readableByteChannel = Channels.newChannel(in);

        // clear 将buffer 变为read
        readBuffer.clear();
        sendBuffer.clear();

        for (
                int readable = readableByteChannel.read(readBuffer);
                readable != -1;
                readable = readableByteChannel.read(readBuffer)
        ) {
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                byte b = readBuffer.get();
                sendBuffer.put(b);
            }
            readBuffer.clear();
        }

        sendBuffer.flip();

        socketChannel.write(sendBuffer);
        socketChannel.register(selector, SelectionKey.OP_READ);
    }

    private void read(SelectionKey key) throws IOException {

        SocketChannel socketChannel = (SocketChannel) key.channel();

        // clear 将buffer 变为read
        readBuffer.clear();

        for (
                int readable = socketChannel.read(readBuffer);
                readable != -1;
                readable = socketChannel.read(readBuffer)
        ) {
            readBuffer.flip();
            while (readBuffer.hasRemaining()) {
                byte b = readBuffer.get();
                System.out.print((char) b);
            }
            readBuffer.clear();
        }

        socketChannel.register(selector, SelectionKey.OP_WRITE);
    }

    private void accept(SelectionKey key) throws IOException {

        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel clientChannel = ssc.accept();
        clientChannel.configureBlocking(false);
        clientChannel.register(selector, SelectionKey.OP_READ);
        System.out.println("a new client connected " + clientChannel.getRemoteAddress());

    }

    public static void main(String[] args) {
        System.out.println("开始");
        new NioServer().start();
        System.out.println("结束");
    }

}
