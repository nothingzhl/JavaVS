package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.SingletonBeanRegistry;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import zhl.study.factory.DefaultUserFactory;
import zhl.study.factory.IUserFactory;

import java.util.concurrent.TimeUnit;

/**
 * Single Bean
 */
public class SingleBeanRegistrationTest {

    private AnnotationConfigApplicationContext applicationContext;
    private ConfigurableListableBeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();
        beanFactory = applicationContext.getBeanFactory();
    }

    @Test
    void testPostConstruct() {
        IUserFactory userFactory = new DefaultUserFactory();
        beanFactory.registerSingleton("user",userFactory);
        IUserFactory user = beanFactory.getBean("user", IUserFactory.class);
        System.out.println(user==userFactory);
    }

    @AfterEach
    void tearDown() throws InterruptedException {
        applicationContext.close();
    }

}
