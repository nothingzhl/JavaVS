package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import zhl.study.factory.IUserFactory;

import java.util.concurrent.TimeUnit;

/**
 * bean gc
 */
public class BeanGCTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanInitializationTest.class);
        applicationContext.refresh();
    }


    @Test
    void testPostConstruct() {
        IUserFactory bean = applicationContext.getBean(IUserFactory.class);
        System.out.println(bean);
        System.out.println(bean.creatUser());
    }


    @AfterEach
    void tearDown() throws InterruptedException {
        applicationContext.close();
        TimeUnit.SECONDS.sleep(2);
        System.gc();
    }

}
