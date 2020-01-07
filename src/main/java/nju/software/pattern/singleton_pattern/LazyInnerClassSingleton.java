package nju.software.pattern.singleton_pattern;

public class LazyInnerClassSingleton {
    //使用 LazyinnerClassGeneral 的时候，默认会先初始化内部
    //如果没使用，则内部类是不加载的
    //原先版本
//    private LazyInnerClassSingleton(){}

    //每一个关键字都不是多余的，static是为了使单例的空间分享，保证这个方法不会重写、重载
    public static final LazyInnerClassSingleton getInstance(){
        return LazyHolder.LAZY;
    }
    //默认不加载
    private static class LazyHolder{
        private static final LazyInnerClassSingleton LAZY=new LazyInnerClassSingleton();
    }
    //上面版本有个问题 就是如果调用LazyInnerClassSingletonTest 是会出现问题的
    //所以下面有个书中所说的无敌强的完美的版本 实现单例模式
    private LazyInnerClassSingleton(){
        if (LazyHolder.LAZY!=null){
            throw new RuntimeException("不允许创建多个实例");
        }
    }
}
