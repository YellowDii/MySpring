package nju.software.pattern.decorator_pattern.battercake1;

public class BattercakeWithEggAndSausage extends BattercakeWithEgg {
    @Override
    protected String getMsg() {
        return super.getMsg()+"+1根香肠";
    }

    @Override
    public int getPrice() {
        return super.getPrice()+2;
    }
}
