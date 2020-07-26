package zhl.study.repository;

import java.util.Collection;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.core.env.Environment;

import lombok.Data;
import lombok.ToString;
import zhl.study.domain.User;

@Data
@ToString(callSuper = true)
public class UserRepository {

    /**
     * 自定义bean
     */
    private Collection<User> users;

    /**
     * spring 内建的依赖
     */
    private BeanFactory beanFactory;

    /**
     * 延迟获取的途径
     */
    private ObjectFactory<User> objectFactory;

    /**
     * 容器自建对象
     */
    private Environment environment;


}
