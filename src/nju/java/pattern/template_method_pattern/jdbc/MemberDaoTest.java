package nju.java.pattern.template_method_pattern.jdbc;

import java.util.List;

public class MemberDaoTest {
    public static void main(String[] args) {
        //数据源自己选择~
        MemberDao memberDao=new MemberDao(null);
        List<?> result=memberDao.selectAll();
        System.out.println(result);
    }
}
