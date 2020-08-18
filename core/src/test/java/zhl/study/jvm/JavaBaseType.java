package zhl.study.jvm;

import org.junit.jupiter.api.Test;

import sun.misc.Unsafe;

/**
 * java 基本类型的相关测试
 */
public class JavaBaseType {

    static boolean boolValue;

    @Test
    void testBoolValue() {
        boolValue = true; // 将这个true替换为2或者3，再看看打印结果
        if (boolValue) {
            System.out.println("Hello, Java!");
        }
        if (boolValue == true) {
            System.out.println("Hello, JVM!");
        }
    }
}
