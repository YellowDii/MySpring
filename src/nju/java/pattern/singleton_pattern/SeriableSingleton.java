package nju.java.pattern.singleton_pattern;

import java.io.Serializable;

//反序列化导致破坏单例模式
public class SeriableSingleton implements Serializable {
    //序列化就是把内存中的状态通过转换成字节码的形式
    //从而转换一 I/O 写入其他地方（可以是磁盘、网络 I/O
    // 内存中的状态会永久保存
    // 反序列化就是将已经持久化的字节码内容转换为 I/O
    // 通过 I/O 流的读取，进而将读取的内容转换为 Java 对象
    // 在转换过程中会重新创建对象 new
    public final static SeriableSingleton INSTANCE = new SeriableSingleton();
    private SeriableSingleton(){}
    public static SeriableSingleton getInstance(){
        return INSTANCE;
    }
    public Object readResolve(){
        return INSTANCE;
    }
}
