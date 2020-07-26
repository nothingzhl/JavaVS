package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.stereotype.Component;

import zhl.study.domain.User;

/**
 * 各种方式注册
 */
@Import({BeanReTest.UserConfig.class})
class BeanReTest {

    private AnnotationConfigApplicationContext applicationContext;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();

    }

    @Test
    void testAnnotationRe() {

        applicationContext.register(BeanReTest.class);
        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(User.class));
        System.out.println(applicationContext.getBeansOfType(UserConfig.class));
    }

    @Test
    void testApiRe() {

        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        AbstractBeanDefinition beanDefinition = beanDefinitionBuilder
                .addPropertyValue("name", "哪吒")
                .addPropertyValue("age", "134")
                .getBeanDefinition();

        applicationContext.registerBeanDefinition("nz",beanDefinition);
        BeanDefinitionReaderUtils.registerWithGeneratedName(beanDefinition,applicationContext);
        applicationContext.refresh();
        System.out.println(applicationContext.getBeansOfType(User.class));

    }

    @AfterEach
    void tearDown() {
        applicationContext.close();
    }

    @Component
    public static class UserConfig {

        @Bean(name = {"user", "zhl_user"})
        public User getUser() {
            User user = new User();
            user.setAge(12);
            user.setName("萨达姆");
            return user;
        }

    }

}
