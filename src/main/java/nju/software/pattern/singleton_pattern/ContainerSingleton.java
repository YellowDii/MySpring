package nju.software.pattern.singleton_pattern;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 容器式单例（属于注册式单例）
 * Spring中也会用到这样的实现方法
 */
public class ContainerSingleton {
    private ContainerSingleton(){}
    private static Map<String,Object> ioc=new ConcurrentHashMap<String,Object>();
    public static Object getBean(String className){
        synchronized (ioc){
            if (!ioc.containsKey(className)){
                Object obj=null;
                try{
                    obj=Class.forName(className).newInstance();
                    ioc.put(className,obj);
                }catch (Exception e){
                    e.printStackTrace();
                }
                return obj;
            }else {
                return ioc.get(className);
            }
        }
    }
}
