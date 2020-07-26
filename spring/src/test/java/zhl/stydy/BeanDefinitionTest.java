package zhl.stydy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.GenericBeanDefinition;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhl.study.domain.User;

/**
 * {@link org.springframework.beans.factory.config.BeanDefinition} 构建实例
 */
class BeanDefinitionTest {

    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        beanFactory =
                new ClassPathXmlApplicationContext("META-INF/bean-definition-context.xml");
    }

    @Test
    void testBeanDefinition() {
        BeanDefinitionBuilder beanDefinitionBuilder = BeanDefinitionBuilder.genericBeanDefinition(User.class);
        beanDefinitionBuilder
                .addPropertyValue("name","特朗普")
                .addPropertyValue("age","56");
        BeanDefinition beanDefinition = beanDefinitionBuilder.getBeanDefinition();
        System.out.println(beanDefinition);

        GenericBeanDefinition genericBeanDefinition = new GenericBeanDefinition();
        genericBeanDefinition.setBeanClass(User.class);
        MutablePropertyValues mutablePropertyValues = new MutablePropertyValues();
        mutablePropertyValues
                .add("name","奥巴马")
                .add("age",64);
        genericBeanDefinition.setPropertyValues(mutablePropertyValues);
        System.out.println(genericBeanDefinition);
    }

    @Test
    void testAlias() {

        User hanlinUser = beanFactory.getBean("hanlin-user", User.class);
        User superUser = beanFactory.getBean("superUser", User.class);
        System.out.println(hanlinUser == superUser);
        Assertions.assertTrue(hanlinUser == superUser);

    }
}
