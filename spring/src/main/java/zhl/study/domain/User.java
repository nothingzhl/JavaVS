package zhl.study.domain;

import lombok.Data;

@Data
public class User {

    private String name;
    private Integer age;

    public static User createUser(){

        User user = new User();
        user.setName("原始天尊");
        user.setAge(10000);
        return user;
    }

}
