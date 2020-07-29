package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import zhl.study.domain.User;
import zhl.study.factory.DefaultUserFactory;
import zhl.study.factory.IUserFactory;

import java.util.Iterator;
import java.util.ServiceLoader;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.stream.StreamSupport;

/**
 * 创建 bean
 */
class BeanCreateTest {

    private BeanFactory beanFactor;



    @BeforeEach
    void setUp() {
        beanFactor = new ClassPathXmlApplicationContext("META-INF/bean-create.xml");
    }

    @Test
    void TestStaticMethod() {
        User aStatic = beanFactor.getBean("static", User.class);
        System.out.println(aStatic);
    }

    @Test
    void TestBeanFromFactory() {
        User factory = beanFactor.getBean("factory", User.class);
        System.out.println(factory);
    }

    @Test
    void testFactoryBean() {
        User userFactoryBean = beanFactor.getBean("userFactoryBean", User.class);
        System.out.println(userFactoryBean);
    }

    @Test
    void testServiceLoad() {
        ServiceLoader<IUserFactory> load =
                ServiceLoader.load(IUserFactory.class, DefaultUserFactory.class.getClassLoader());
        Iterator<IUserFactory> iterator = load.iterator();
        while (iterator.hasNext()){
            System.out.println(iterator.next().creatUser());
        }
    }

    @Test
    void testSpringServiceLoad() {
        ServiceLoader<IUserFactory> springServiceLoad = beanFactor.getBean("springServiceLoad", ServiceLoader.class);
        StreamSupport.stream(Spliterators.spliteratorUnknownSize(springServiceLoad.iterator(), Spliterator.ORDERED),false)
                .map(iUserFactory -> iUserFactory.creatUser())
                .forEach(System.out::println);
    }

    @Test
    void testAutowireCapable() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("META-INF/bean-create.xml");
        AutowireCapableBeanFactory beanFactory = applicationContext.getAutowireCapableBeanFactory();
        DefaultUserFactory bean = beanFactory.createBean(DefaultUserFactory.class);
        System.out.println(bean.creatUser());
    }

    @AfterEach
    void tearDown() {

    }
}
