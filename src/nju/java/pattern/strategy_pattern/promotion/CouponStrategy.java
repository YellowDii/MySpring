package nju.java.pattern.strategy_pattern.promotion;

public class CouponStrategy implements PromotionStrategy {
    public void doPromotion() {
        System.out.println("领取优惠券，课程价格直接减优惠券面值抵扣");
    }
}
