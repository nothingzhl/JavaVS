package zhl.study.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.StringReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketAddress;

import lombok.SneakyThrows;

/**
 * 服务端
 */
public final class Server {

    private static final int port = 1000;

    public static void main(String[] args) {
        System.out.println("启动");
        try (ServerSocket serverSocket = new ServerSocket(port);){
            while (!serverSocket.isClosed()) {
                Socket accept = serverSocket.accept();
                // reader
                InputStream inputStream = accept.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

                OutputStream outputStream = accept.getOutputStream();
                OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
                BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);

                System.out.println("读取");

                while (!bufferedReader.ready()){
                }

                System.out.println(bufferedReader.readLine());

                System.out.println("写入");
                bufferedWriter.write("wow");
                bufferedWriter.flush();

                Thread.sleep(1000L);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
