package nju.software.pattern.prototype_pattern;

import java.util.List;

public class ConcretePrototypeA implements Prototype{
    private int age;
    private String name;
    private List hobbbies;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List getHobbbies() {
        return hobbbies;
    }

    public void setHobbbies(List hobbbies) {
        this.hobbbies = hobbbies;
    }

    @Override
    public ConcretePrototypeA clone() {
        ConcretePrototypeA concretePrototypeA=new ConcretePrototypeA();
        concretePrototypeA.setAge(this.age);
        concretePrototypeA.setName(this.name);
        concretePrototypeA.setHobbbies(this.hobbbies);
        return concretePrototypeA;
    }
}
