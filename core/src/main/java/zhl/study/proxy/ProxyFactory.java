package zhl.study.proxy;

import java.lang.reflect.Proxy;

public class ProxyFactory {

    private Class target;

    public ProxyFactory(Class target) {
        this.target = target;
    }

    public Object getProxyInstance() {

        return Proxy.newProxyInstance(target.getClassLoader(), new Class[]{target}, (proxy, method, args) -> {
            System.out.println("hello");
            return null;
        });
    }

}
