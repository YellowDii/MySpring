package nju.software.pattern.delegate_pattern.simple;

public class DelegateTest {
    public static void main(String[] args) {
        //代理模式注重的是过程，委派模式注重的是结果
        //策略模式注重可扩展性（外部扩展性），委派模式注重内部的灵活性和可复用性
        //委派模式的核心就是分发、调度、派遣，委派模式是静态代理和策略模式的一种特殊组合
        new Boss().command("登录",new Leader());
    }
}
