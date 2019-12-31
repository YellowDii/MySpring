package nju.java.pattern.proxy_pattern;

public class Customer implements Person{

    @Override
    public void findLove() {
        System.out.println("有钱有颜");
        System.out.println("脾性好");
        System.out.println("讨人喜欢");
    }
}
