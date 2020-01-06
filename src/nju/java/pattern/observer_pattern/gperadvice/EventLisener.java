package nju.java.pattern.observer_pattern.gperadvice;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

public class EventLisener {
    //JDK底层的Lisener通常也是这样来设计的
    protected Map<String,Event> events=new HashMap<String,Event>();
    public void addLisener(String  eventType,Object target){
        try{
            this.addLisener(eventType,
                    target,target.getClass().getMethod("on"+toUpperFirstCase(eventType),Event.class));
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void addLisener(String eventType, Object target, Method callback) {
        //注册事件
        events.put(eventType,new Event(target,callback));
    }
    //触发 只要有动作就触发
    private void trigger(Event event){
        event.setSource(this);
        event.setTime(System.currentTimeMillis());

        try{
            //发起回调
            if (event.getCallback()!=null){
                //用反射调用它的回调函数
                event.getCallback().invoke(event.getTarget(),event);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    //事件名称触发
    protected void trigger(String trigger){
        if (!this.events.containsKey(trigger)){
            return;
        }
        trigger(this.events.get(trigger).setTrigger(trigger));
    }
    //编辑处理的私有方法，首字母大写
    private String toUpperFirstCase(String str) {
        char[] chars=str.toCharArray();
        chars[0]-=32;
        return String.valueOf(chars);
    }
}
