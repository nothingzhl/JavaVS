package zhl.study.factory;

import org.springframework.beans.factory.FactoryBean;

import zhl.study.domain.User;

public class UserFactoryBean  implements FactoryBean<User> {
    @Override
    public User getObject() throws Exception {
        return User.createUser();
    }

    @Override
    public Class<?> getObjectType() {
        return User.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
