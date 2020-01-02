package nju.java.pattern.strategy_pattern.promotion;

public class CashbackStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("返现促销，返回的金额转到支付宝账号");
    }
}
