package nju.java.pattern.decorator_pattern.passport;

public interface ISigninForThirdService extends ISigninService{
    ResultMsg loginForQQ(String id);
    ResultMsg loginForWechat(String id);
    ResultMsg loginForToken(String token);
    ResultMsg loginForTelephone(String telphone,String code);
    ResultMsg loginForRegist(String username,String passport);
}
