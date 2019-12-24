package nju.java.pattern.singleton_pattern;
//懒汉单例模式在外部需要使用的时候才进行实例化
public class LazySimpleSingleton {
    private LazySimpleSingleton(){}
    //静态块，公共内存区域
    private static LazySimpleSingleton lazy=null;
    //第一版本 这个构建方法在多线程情况下存在危险 因为后执行的线程会覆盖前面的 导致单例并不是单例
    /*
    public static LazySimpleSingleton getInstance(){
        if (lazy==null){
            lazy=new LazySimpleSingleton();
        }
        return lazy;
    }
     */
    //第二版本 加上synchronized之后 线程安全问题解决了。但是，用synchronized加锁时，在线程数量较多的情况下，
    // 如果CPU分配压力上升，则会导致大批线程阻塞，从而导致程序性能大幅下降。
    /*
    public static synchronized LazySimpleSingleton getInstance(){
      if (lazy==null){
          lazy=new LazySimpleSingleton();
      }
      return lazy;
    }
     */
    //下面这种方法 兼顾了线程安全又能提升程序性能
    //当然还是会遇到synchronized 会遇到上锁 可以参考LazyInnerClassSingleton
    public static LazySimpleSingleton getInstance(){
        if (lazy==null){
            synchronized (LazySimpleSingleton.class){
                if (lazy==null){
                    lazy=new LazySimpleSingleton();
                    //1.分配内存给这个对象
                    //2.初始化对象
                    //3.设置lazy指向刚分配的内存地址
                }
            }
        }
        return lazy;
    }
}
