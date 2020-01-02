package nju.java.pattern.strategy_pattern.promotion;

public class EmptyStrategy implements PromotionStrategy {

    public void doPromotion() {
        System.out.println("无促销活动");
    }
}
