package zhl.study.factory;

import zhl.study.domain.User;

public interface IUserFactory {

    default User creatUser(){
        return User.createUser();
    }

    default void initMethod() {
        throw new RuntimeException("空实现");
    }
}
