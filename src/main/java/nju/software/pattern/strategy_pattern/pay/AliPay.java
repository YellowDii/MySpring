package nju.software.pattern.strategy_pattern.pay;

public class AliPay extends Payment{
    @Override
    public String getName() {
        return "支付宝";
    }

    @Override
    protected double queryBalance(String uid) {
        //用来模拟
        return 900;
    }
}
