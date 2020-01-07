package nju.software.pattern.singleton_pattern;

public class ExectorThread implements Runnable{
    @Override
    public void run(){
        LazySimpleSingleton singleton=LazySimpleSingleton.getInstance();
        System.out.println(Thread.currentThread().getName()+":"+singleton);
    }
}
