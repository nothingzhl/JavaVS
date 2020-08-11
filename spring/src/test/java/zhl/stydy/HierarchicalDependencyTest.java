package zhl.stydy;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.HierarchicalBeanFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * 层次bean
 */
public class HierarchicalDependencyTest {

    private AnnotationConfigApplicationContext applicationContext;

    private HierarchicalBeanFactory parentBeanFactory;

    private ConfigurableListableBeanFactory childBeanFactory;

    @BeforeEach
    void setUp() {
        applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(HierarchicalDependencyTest.class);
        childBeanFactory = applicationContext.getBeanFactory();
        System.out.println("first:" + childBeanFactory.getParentBeanFactory());
        parentBeanFactory = createParentBeanFactory();
        childBeanFactory.setParentBeanFactory(parentBeanFactory);
        System.out.println("scened:" + childBeanFactory.getParentBeanFactory());
        applicationContext.refresh();
    }

    @Test
    void testHierarchical() {
        displayHierarchicalBeanFactory(childBeanFactory, "user");
        displayHierarchicalBeanFactory(parentBeanFactory, "user");
    }


    /**
     * parent beanfactory
     *
     * @return
     */
    private ConfigurableBeanFactory createParentBeanFactory() {
        DefaultListableBeanFactory defaultListableBeanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(defaultListableBeanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions("META-INF/dependency-look-up.xml");
        return defaultListableBeanFactory;
    }

    private void displayHierarchicalBeanFactory(HierarchicalBeanFactory beanFactory, String beanName) {
        containBean(beanFactory, beanName);
        System.out.printf("当前BeanFactory[%s]是否包含Bean[name:%s]:%s\n", beanFactory.getClass(), beanName, containBean(beanFactory, beanName));
    }

    private boolean containBean(HierarchicalBeanFactory beanFactory, String beanName) {
        BeanFactory parentBeanFactory = beanFactory.getParentBeanFactory();
        if (parentBeanFactory instanceof HierarchicalBeanFactory) {
            HierarchicalBeanFactory cast = HierarchicalBeanFactory.class.cast(parentBeanFactory);
            if (containBean(cast, beanName)) {
                return true;
            }
        }
        return beanFactory.containsLocalBean(beanName);
    }


    @AfterEach
    void tearDown() {
        applicationContext.close();
    }
}
