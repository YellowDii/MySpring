package nju.software.pattern.singleton_pattern;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SeriableSingletonTest {
    //通过下面的测试之后 会发现两次创建的对象 s1 s2不一致 内存地址不同
    //如何解决？ 在SeriableSingleton中加上readResolve就行了
    public static void main(String[] args) {
        SeriableSingleton sl = null;
        SeriableSingleton s2 = SeriableSingleton.getInstance();
        FileOutputStream fos = null;
        try{
            fos = new FileOutputStream("SeriableSingleton.obj");
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(s2);
            oos.flush();
            oos.close();
            FileInputStream fis = new FileInputStream("SeriableSingleton.obj");
            ObjectInputStream ois = new ObjectInputStream(fis);
            sl = (SeriableSingleton) ois.readObject();
            ois.close();
            System.out.println(sl);
            System.out.println(s2);
            System.out.println(sl == s2);
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
