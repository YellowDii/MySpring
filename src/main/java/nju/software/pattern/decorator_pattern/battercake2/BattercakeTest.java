package nju.software.pattern.decorator_pattern.battercake2;

public class BattercakeTest {
    public static void main(String[] args) {
        Battercake battercake;
        //要个煎饼
        battercake = new BaseBattercake();
        //加个鸡蛋
        battercake = new EggDecorator(battercake);
        //再加个鸡蛋
        battercake = new EggDecorator(battercake);
        //加个香肠
        battercake = new SausageDecorator(battercake);

        System.out.println(battercake.getMsg()+",总价"+battercake.getPrice());
    }
}
