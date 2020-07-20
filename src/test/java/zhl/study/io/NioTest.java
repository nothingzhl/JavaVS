package zhl.study.io;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;

public class NioTest {

    /**
     * 测试文件的路径
     */
    private Path testFilePath = Paths.get("src/test/resources/testIO");

    /**
     * 测试文件的父路径
     */
    private Path parentPath = testFilePath.getParent();

    private static String tmp = System.getProperty("java.io.tmpdir");


    @Test
    void testPaths() throws URISyntaxException {


        Path currentPath = Paths.get("src/test/resources/testIO");
        Assertions.assertTrue(Files.exists(currentPath, LinkOption.NOFOLLOW_LINKS));

        //class 是从当前class路径(即backage)下进行查找
        URI classPath = this.getClass().getResource("/testIO").toURI();
        Assertions.assertTrue(Files.exists(Paths.get(classPath), LinkOption.NOFOLLOW_LINKS));

        //classLoad是从当前的classPath路径下开始找
        URI classLoadPath = this.getClass().getClassLoader().getResource("testIO").toURI();
        Assertions.assertTrue(Files.exists(Paths.get(classLoadPath), LinkOption.NOFOLLOW_LINKS));
    }


    @Test
    void testFilePermission() throws IOException {

        Assertions.assertTrue(Files.isReadable(testFilePath));
        Assertions.assertTrue(Files.isWritable(testFilePath));
        Assertions.assertTrue(Files.isExecutable(testFilePath));
        Assertions.assertFalse(Files.isHidden(testFilePath));
        Assertions.assertTrue(Files.isRegularFile(testFilePath, LinkOption.NOFOLLOW_LINKS));
        Assertions.assertFalse(Files.isSymbolicLink(testFilePath));

        System.out.println(Files.createTempFile("test_temp",".test"));
    }

    @AfterAll
    static void tearDown() {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            Path tempPath = Paths.get(new File(tmp).toURI());
            try (DirectoryStream<Path> tmpPathStream = Files.newDirectoryStream(tempPath,path-> path.endsWith(".test"))) {
                for (Path path : tmpPathStream) {
                    System.out.println(path);
                    Files.deleteIfExists(path);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }));
    }
}
