package nju.java.pattern.singleton_pattern;

import java.lang.reflect.Constructor;

public class LazyInnerClassSingletonTest {
    //通过反射破坏单例
    public static void main(String[] args) {
        try {
            //在很无聊的情况下，进行破坏
            Class<?> clazz = LazyInnerClassSingleton.class;

            //通过反射获取私有的构造方法
            Constructor c = clazz.getDeclaredConstructor(null);
            //强制访问
            c.setAccessible(true);
            //暴力初始化
            Object o1=c.newInstance();
            //调用了两次构造方法，相当于"new"了两次，犯了原则性错误
            Object o2=c.newInstance();

            System.out.println(o1==o2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
