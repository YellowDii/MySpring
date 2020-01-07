package nju.software.pattern.proxy_pattern;

import net.sf.cglib.core.DebuggingClassWriter;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestProxyPattern {
    public static void main(String[] args) {
        try{
            //静态代理的测试
            Order order=new Order();
            SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd");
            Date date=sdf.parse("2017/02/01");
            order.setCreateTime(date.getTime());

            IOrderService orderService=new OrderServiceStaticProxy(new OrderService());
            orderService.createOrder(order);

            //动态代理的测试
            Person obj=(Person) new JDKMeipo().getInstance(new Customer());
            obj.findLove();

            Order order1=new Order();
            Date date1=sdf.parse("2018/02/01");
            order.setCreateTime(date1.getTime());
            IOrderService orderService1=(IOrderService)new OrderServiceDynamicProxy().getInstance(new OrderService());
            orderService1.createOrder(order1);

            //测试CGLib
            //利用CGlib的代理类可以将内存中的.class文件写入本地磁盘
            System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"E://cglib_proxy_class/");
            Customer obj1=(Customer)new CglibMeipo().getInstance(Customer.class);
            obj1.findLove();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
