package nju.java.pattern.decorator_pattern.battercake2;

public class EggDecorator extends BattercakeDecorator{
    public EggDecorator(Battercake battercake){
        super(battercake);
    }
    protected void doSomething(){}

    @Override
    protected int getPrice() {
        return super.getPrice()+1;
    }

    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个鸡蛋";
    }
}
