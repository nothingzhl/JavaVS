package zhl.study.beans;

import org.junit.jupiter.api.Test;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyEditorSupport;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void testBeanInfo() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Arrays.asList(beanInfo.getPropertyDescriptors()).forEach(System.out::println);
    }


    @Test
    void testPropertyEdit() throws IntrospectionException {
        BeanInfo beanInfo = Introspector.getBeanInfo(Person.class, Object.class);
        Arrays.asList(beanInfo.getPropertyDescriptors()).forEach(propertyDescriptor -> {
            if ("age".equals(propertyDescriptor.getName())) {
                propertyDescriptor.setPropertyEditorClass(StringToIntegerPropertyEdit.class);
            }
        });
    }

    static class StringToIntegerPropertyEdit extends PropertyEditorSupport{
        @Override
        public void setAsText(String text) throws IllegalArgumentException {
            setValue(Integer.valueOf(text));
        }
    }
}