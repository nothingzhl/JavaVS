package zhl.study.refect;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * LookUp的学习
 */
public class LookUpCase {

    /**
     * (1) 创建MethodType,获取指定方法的签名
     * (2) 在Lookup中查找MethodType的方法句柄MethodHandle
     * (3) 传入方法参数通过MethodHandle调用方法
     *  ps:参考 https://www.cnblogs.com/Mrfanl/p/10544251.html
     * @param args
     */
    public static void main(String[] args) {
        MethodHandles.Lookup lookup = MethodHandles.lookup();
        MethodType mt = MethodType.methodType(String.class,char.class,char.class);
        try {
            //String中的replace的方法
            MethodHandle mh = lookup.findVirtual(String.class,"replace", mt);
            String handled_str = (String) mh.invoke("abc",'a','c');
            System.out.print(handled_str);

        }  catch (Throwable throwable) {
            throwable.printStackTrace();
        }
    }

}
