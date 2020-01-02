package nju.java.pattern.strategy_pattern.pay;

public class PayStrategyTest {
    public static void main(String[] args) {
        //省略把商品添加到购物车再从购物车下单，直接从订单开始
        Order order=new Order("1","20180311001000009",324.45);

        //开始支付，选择支付宝支付、微信支付、银联支付或京东白条支付
        //每个渠道支付的具体算法是不一样的，基本算法是固定的

        //在支付的时候才决定这个值用哪个
        System.out.println(order.pay(PayStrategy.ALI_PAY));

    }
}
