package nju.software.pattern.prototype_pattern;

import java.util.ArrayList;

public class PrototypeTest {
    public static void main(String[] args) {
        //创建一个具体的需要克隆的对象
        ConcretePrototypeA concretePrototype=new ConcretePrototypeA();
        //随便填写点属性
        concretePrototype.setAge(22);
        concretePrototype.setName("原型");
        concretePrototype.setHobbbies(new ArrayList<String>());
        System.out.println(concretePrototype);

        //创建Client对象 开始克隆
        Client client=new Client(concretePrototype);
        ConcretePrototypeA concretePrototypeClone=(ConcretePrototypeA)client.startClone(concretePrototype);
        System.out.println(concretePrototypeClone);

        //测试其他的
        System.out.println("克隆对象中的引用类型地址值："+concretePrototypeClone.getHobbbies());
        System.out.println("原对象中的引用类型地地址值："+concretePrototype.getHobbbies());
        System.out.println("对象地址比较："+(concretePrototype.getHobbbies()==concretePrototypeClone.getHobbbies()));
    }
}
