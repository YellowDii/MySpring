package nju.java.pattern.proxy_pattern;

import java.text.SimpleDateFormat;
import java.util.Date;

public class OrderService implements IOrderService {
    private OrderDao orderDao;
    public OrderService(){
        //如果使用Spring 应该是自动注入
        //为了使用方便，直接在构造函数中初始化
        orderDao=new OrderDao();
    }

    public int createOrder(Order order) {
        System.out.println("OrderService调用orderDao创建订单");
        return orderDao.insert(order);
    }

}
