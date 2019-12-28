package nju.java.pattern.prototype_pattern;

import java.util.ArrayList;

public class DeepCloneTest {
    public static void main(String[] args) {
        QiTianDaSheng qiTianDaSheng=new QiTianDaSheng();
        try{
            QiTianDaSheng clone=(QiTianDaSheng)qiTianDaSheng.clone();
            System.out.println("深克隆："+(qiTianDaSheng.jingubang==clone.jingubang));
        }catch (Exception e){
            e.printStackTrace();
        }

        QiTianDaSheng q=new QiTianDaSheng();
        QiTianDaSheng n=q.shallowClone(q);
        System.out.println("潜克隆："+(q.jingubang==n.jingubang));
    }
}
