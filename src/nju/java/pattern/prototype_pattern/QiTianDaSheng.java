package nju.java.pattern.prototype_pattern;

import java.io.*;
import java.util.Date;

public class QiTianDaSheng extends Monkey implements Cloneable, Serializable {
    public Jingubang jingubang;
    public QiTianDaSheng(){
        this.birthday=new Date();
        this.jingubang=new Jingubang();
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return this.deepClone();
    }

    private Object deepClone() {
        try{
            ByteArrayOutputStream bos=new ByteArrayOutputStream();
            ObjectOutputStream oos=new ObjectOutputStream(bos);
            oos.writeObject(this);

            ByteArrayInputStream bis=new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois=new ObjectInputStream(bis);

            QiTianDaSheng copy=(QiTianDaSheng)ois.readObject();
            copy.birthday=new Date();
            return copy;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
    public QiTianDaSheng shallowClone(QiTianDaSheng target){
        QiTianDaSheng qiTianDaSheng=new QiTianDaSheng();
        qiTianDaSheng.height=target.height;
        qiTianDaSheng.weight=target.weight;

        qiTianDaSheng.jingubang=target.jingubang;
        qiTianDaSheng.birthday=new Date();
        return qiTianDaSheng;
    }
}
