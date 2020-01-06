package nju.java.pattern.decorator_pattern.battercake1;

public class BattercakeWithEgg extends Battercake{
    @Override
    protected String getMsg() {
        return super.getMsg()+"1个鸡蛋";
    }

    @Override
    public int getPrice() {
        return super.getPrice()+1;
    }
}
