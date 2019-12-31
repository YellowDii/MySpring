package nju.java.pattern.proxy_pattern;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CglibMeipo implements MethodInterceptor {
    public Object getInstance(Class<?> clazz) throws Exception{
        Enhancer enhancer=new Enhancer();
        //设置即将生成的新类的父类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);

        return enhancer.create();
    }
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy)throws Throwable{
        //业务的增强
        before();
        Object obj=methodProxy.invokeSuper(o,objects);
        after();
        return obj;
    }

    private void after() {
        System.out.println("如果合适的话，就准备办事吧");
    }

    private void before() {
        System.out.println("我是媒婆，我要给你找对象，现在已经确认你的需求");
        System.out.println("开始物色");
    }

}
