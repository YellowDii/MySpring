package nju.java.pattern.observer_pattern.gperadvice;

import java.lang.reflect.Method;

/**
 * 监听器的一种包装，标准事件源格式的定义
 */
public class Event {
    //事件源，事件是由谁发起的，保存起来
    private Object source;
    //事件触发，要通知谁
    private Object target;
    //事件触发，要做什么动作，回调
    private Method callback;
    //事件的名称，触发的是什么事件
    private String trigger;
    //事件触发时间
    private long time;
    public Event(Object target,Method callback){
        this.target=target;
        this.callback=callback;
    }
    public Event setSource(Object source){
        this.source=source;
        return this;
    }

    public Object getSource() {
        return source;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public Object getTarget() {
        return target;
    }

    public Event setTrigger(String trigger) {
        this.trigger=trigger;
        return this;
    }

    public Method getCallback() {
        return callback;
    }

    @Override
    public String toString() {
        return "Event{" +
                "\tsource=" + source.getClass()+ ",\n" +
                "\ttarget=" + target.getClass()+ ",\n" +
                "\tcallback=" + callback + ",\n" +
                "\ttrigger='" + trigger + ",\n" +
                "\ttime=" + time+ ",\n" +
                '}';
    }
}
