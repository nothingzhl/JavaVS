package zhl.stydy;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import zhl.study.domain.User;

/**
 * ioc 容器
 *
 */
class IocContainer {

    @Test
    void iocTest() {
        //ioc container
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();

        //reosurce load
        XmlBeanDefinitionReader reader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        int beansCount = reader.loadBeanDefinitions("classpath:/METE-INF/dependency-look-up.xml");
        System.out.println(beansCount);

        User bean = defaultListableBeanFactory.getBean(User.class);
        System.out.println(bean);
    }

    @Test
    void annotationTest() {
        AnnotationConfigApplicationContext configApplicationContext =
                new AnnotationConfigApplicationContext();

        configApplicationContext.register(BeanConfig.class);
        configApplicationContext.refresh();

        System.out.println(configApplicationContext.getBean(User.class));
        configApplicationContext.close();
    }
}
