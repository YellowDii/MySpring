package nju.software.pattern.singleton_pattern;

/**
 * 饿汉式单例模式
 * 在类加载的时候就立即初始化，并且创建单例对象。它绝对线程安全，在线程还没出现以前就实例化了，不可能存在 访问安全问题
 * 优点：没有加任何锁、执行效率比较高，用户体验比懒汉式单例模式更好。
 * 缺点： 类加载的时候就初始化，不管用与不用都占着空间，浪费了内存，有可能“占着茅坑不拉屎“
 */
public class HungrySingleton {
    private static final HungrySingleton hunrySingleton=new HungrySingleton();
    private HungrySingleton(){

    }
    public static HungrySingleton getInstance(){
        return  hunrySingleton;
    }
}
//也可以下面这样写
/*

public class HungryStaticSingleton{
    private static final HungrySingleton hungrySingleton;
    static {
        hungrySingleton=new HungryStaticSingleton();
    }
    public static HungryStaticSingleton getInstance(){
        return hungrySingleton;
    }
}

 */