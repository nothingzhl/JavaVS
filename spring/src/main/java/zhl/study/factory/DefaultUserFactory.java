package zhl.study.factory;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;

public class DefaultUserFactory implements IUserFactory, InitializingBean, DisposableBean {

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

    @PreDestroy
    public void preDestroy(){
        System.out.println("@PreDestroy:准备销毁");
    }

    public void destroy(){
        System.out.println("Destroy:准备销毁");
    }

    public void doDestroy(){
        System.out.println("自定义销毁方法");
    }

    @Override
    public void finalize() throws Throwable {
        System.out.println("finalize....");
    }
}
