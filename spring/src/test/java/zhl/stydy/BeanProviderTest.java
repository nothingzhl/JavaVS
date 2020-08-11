package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import zhl.study.factory.IUserFactory;

/**
 * bean provider 实现
 */
public class BeanProviderTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(BeanProviderTest.class);
        applicationContext.refresh();
    }

    @Test
    void testPostConstruct() {
        ObjectProvider<String> beanProvider = applicationContext.getBeanProvider(String.class);
        System.out.println(beanProvider.getIfAvailable());
    }

    @Bean
    public String helloWorld(){
        return "hello world!";
    }

    @AfterEach
    void tearDown() {
        applicationContext.close();
    }


}
