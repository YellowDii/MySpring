package nju.java.pattern.proxy_pattern;

//持久层操作类
public class OrderDao {
    public int insert(Order order){
        System.out.println("OrderDao 创建Order成功");
        return 1;
    }
}
