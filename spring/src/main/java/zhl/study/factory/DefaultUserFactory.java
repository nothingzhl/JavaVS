package zhl.study.factory;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.InitializingBean;

public class DefaultUserFactory implements IUserFactory, InitializingBean {

    @PostConstruct
    public void init(){
        System.out.println("准备初始化 by postConstruct:"+this.getClass().getSimpleName());
    }

    @Override
    public  void  initMethod(){
        System.out.println("准备初始化 by 自定义初始化方法:"+DefaultUserFactory.class.getSimpleName());
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("准备初始化 by Initialization#afterPropertiesSet:"+DefaultUserFactory.class.getSimpleName());
    }
}
