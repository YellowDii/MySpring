package nju.java.pattern.singleton_pattern;

/**
 * 枚举式单例（属于注册式单例）
 * 这种单例模式序列化无法破坏
 * 反射也不能破坏 会抛异常"Cannot reflectively create enum objects" 即不能用反射来创建枚举类型
 */
public enum  EnumSingleton {
    INSTANCE;
    private Object data;
    public Object getData(){
        return data;
    }
    public void setData(Object data){
        this.data=data;
    }
    public static EnumSingleton getInstance(){
        return INSTANCE;
    }
}
