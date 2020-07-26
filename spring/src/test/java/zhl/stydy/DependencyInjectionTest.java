package zhl.stydy;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.env.Environment;

import zhl.study.repository.UserRepository;

class DependencyInjectionTest {

    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        beanFactory =
                new ClassPathXmlApplicationContext("METE-INF/dependency-injection.xml");
    }

    @Test()
    void testAutoInjection() {
        UserRepository bean = beanFactory.getBean(UserRepository.class);
        System.out.println(bean);
        System.out.println(bean.getBeanFactory() == beanFactory);
        System.out.println(bean.getObjectFactory().getObject());
        System.out.println(beanFactory.getBean(Environment.class));
        Assertions.assertThrows(Exception.class,()->beanFactory.getBean(BeanFactory.class));
    }


}
