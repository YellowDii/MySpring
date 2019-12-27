package nju.java.pattern.singleton_pattern;

/**
 * 线程单例实现ThreadLocal
 * ThreadLocal 保证其创建的对象是全局唯一的，
 * 但是能保证在单个线程中是唯一的，天生是线程安全的
 */
public class ThreadLocalSingleton {
    public static final ThreadLocal<ThreadLocalSingleton> threadLocalInstance=
            new ThreadLocal<ThreadLocalSingleton>(){
                @Override
                protected ThreadLocalSingleton initialValue(){
                    return new ThreadLocalSingleton();
                }
            };
    private ThreadLocalSingleton(){}

    public static ThreadLocalSingleton getInstance(){
        return threadLocalInstance.get();
    }
}
