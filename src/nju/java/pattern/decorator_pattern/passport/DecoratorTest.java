package nju.java.pattern.decorator_pattern.passport;

public class DecoratorTest {
    public static void main(String[] args) {
        ISigninForThirdService signinForThirdService=new SigninForThirdService(new SigninService());
        signinForThirdService.loginForQQ("xzcsadawdad");
        //动态增加或者覆盖原有方法时，采用装饰者模式
    }
}
