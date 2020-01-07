package nju.software.pattern.observer_pattern.gperadvice;

public class MouseEventCalback {
    public void onClick(Event e){
        System.out.println("=======触发鼠标按下事件========"+"\n"+ e);
    }
    public void onDoubleClick(Event e){
        System.out.println("=======触发鼠标移动事件========"+"\n"+ e);
    }
    public void onUp(Event e){
        System.out.println("=======触发鼠标弹起事件========"+"\n"+ e);
    }
    public void onDown(Event e){
        System.out.println("=======触发鼠标按下事件========"+"\n"+ e);
    }
    public void onMove(Event e){
        System.out.println("=======触发鼠标移动事件========"+"\n"+ e);
    }
    //。。。。。。
}
