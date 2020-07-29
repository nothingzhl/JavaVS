package zhl.study.proxy;

import org.junit.jupiter.api.Test;

public class ProxyTest {

    @Test
    void proxyTest(){
        ProxyFactory proxyFactory = new ProxyFactory(Say.class);

        Say proxySay = (Say) proxyFactory.getProxyInstance();

        proxySay.say();

    }

}
