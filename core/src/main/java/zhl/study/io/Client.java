package zhl.study.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;

/**
 * 客户端应用
 */
public final class Client {

    private static final String url = "127.0.0.1";
    private static final int targetPort = 1000;

    public static void main(String[] args) {

        System.out.println("启动");

        try(Socket socket = new Socket(url,targetPort)){

            InputStream inputStream = socket.getInputStream();
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);

            OutputStream outputStream = socket.getOutputStream();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            BufferedWriter bufferedWriter = new BufferedWriter(outputStreamWriter);


            BufferedReader console = new BufferedReader(new InputStreamReader(System.in));

            System.out.println("写入");

            bufferedWriter.write(console.readLine());
            bufferedWriter.flush();

            Thread.sleep(1000L);

            System.out.println("回显");
            while (!bufferedReader.ready()){}
            System.out.println(bufferedReader.readLine());

            Thread.sleep(1000L);

        }catch (Exception e){
            e.printStackTrace();
        }


    }
}
