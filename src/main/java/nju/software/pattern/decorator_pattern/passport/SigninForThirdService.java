package nju.software.pattern.decorator_pattern.passport;

public class SigninForThirdService implements ISigninForThirdService{
    private ISigninService signin;
    public SigninForThirdService(ISigninService iSignin){
        this.signin=iSignin;
    }

    public ResultMsg loginForQQ(String id) {
        return null;
    }

    public ResultMsg loginForWechat(String id) {
        return null;
    }

    public ResultMsg loginForToken(String token) {
        return null;
    }

    public ResultMsg loginForTelephone(String telphone, String code) {
        return null;
    }

    public ResultMsg loginForRegist(String username, String passport) {
        return null;
    }

    public ResultMsg regist(String username, String password) {
        return signin.regist(username,password);
    }

    public ResultMsg login(String username, String password) {
        return signin.login(username,password);
    }
}
