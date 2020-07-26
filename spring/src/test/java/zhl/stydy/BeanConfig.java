package zhl.stydy;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

import zhl.study.domain.User;

@Configurable
public class BeanConfig {

    @Bean
    public User getUser() {
        User user = new User();
        user.setName("达芬奇");
        user.setAge(1000);
        return user;
    }

}
