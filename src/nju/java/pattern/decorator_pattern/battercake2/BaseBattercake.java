package nju.java.pattern.decorator_pattern.battercake2;

public class BaseBattercake extends Battercake{
    @Override
    public int getPrice() {
        return 5;
    }

    @Override
    protected String getMsg() {
        return "煎饼";
    }
}
