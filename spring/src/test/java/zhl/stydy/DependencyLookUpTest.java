package zhl.stydy;

import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ListableBeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import zhl.study.annotion.Super;
import zhl.study.domain.User;

class DependencyLookUpTest {

    private BeanFactory beanFactory;

    @BeforeEach
    void setUp() {
        beanFactory =
                new ClassPathXmlApplicationContext("METE-INF/dependency-look-up.xml");
    }

    @Test
    void testLookUpByName() {
        lookupInRealtime(beanFactory);
        lookupInLazy(beanFactory);
    }

    private void lookupInLazy(BeanFactory beanFactory) {
        ObjectFactory<User> userObjectFactory = (ObjectFactory<User>) beanFactory.getBean("objectFactory");
        User user = userObjectFactory.getObject();
        System.out.println("延迟查找:" + user);
    }

    private void lookupInRealtime(BeanFactory beanFactory) {
        //实时查找
        User user = (User) beanFactory.getBean("user");
        System.out.println("实时查找:" + user);
    }

    @Test
    void testLookupByType() {
        lookupInRealtimeByType(beanFactory);
        lookupCollectionByType(beanFactory);
        lookupByAnnotation(beanFactory);
    }

    private void lookupByAnnotation(BeanFactory beanFactory) {

        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ((ListableBeanFactory) beanFactory);

            Map<String, User> beansOfType = (Map)listableBeanFactory.getBeansWithAnnotation(Super.class);

            System.out.println("所有 @Super 注解的对象:"+beansOfType);

        }
    }

    private void lookupCollectionByType(BeanFactory beanFactory) {
        if (beanFactory instanceof ListableBeanFactory) {
            ListableBeanFactory listableBeanFactory = ((ListableBeanFactory) beanFactory);

            Map<String, User> beansOfType = listableBeanFactory.getBeansOfType(User.class);

            System.out.println("集合beans:"+beansOfType);

        }
    }

    private void lookupInRealtimeByType(BeanFactory beanFactory) {

        User user = beanFactory.getBean(User.class);
        System.out.println("实时查找:" + user);

    }
}