package zhl.study.io;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.Charset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import lombok.SneakyThrows;

/**
 * 网络io测试
 */
class NetIoConnectTest {

    String targetUrl = "https://www.baidu.com";

    private URLConnection urlConnection;

    @BeforeEach
    void setUp() throws IOException {
        urlConnection = new URL(this.targetUrl).openConnection();
    }

    @SneakyThrows
    @Test
    void testGetResp() {
        urlConnection.connect();
        InputStream inputStream = urlConnection.getInputStream();
        BufferedInputStream bin = new BufferedInputStream(inputStream,1024);
        InputStreamReader isr = new InputStreamReader(bin, Charset.defaultCharset());
        BufferedReader br = new BufferedReader(isr,1024);
        br.lines().forEach(System.out::println);
    }
}
