package nju.software.pattern.decorator_pattern.battercake2;

public class SausageDecorator extends BattercakeDecorator{
    public SausageDecorator(Battercake battercake){
        super(battercake);
    }
    protected void doSomething(){}

    @Override
    protected String getMsg() {
        return super.getMsg()+"+1个香肠";
    }

    @Override
    protected int getPrice() {
        return super.getPrice()+2;
    }
}
