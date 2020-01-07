package nju.software.pattern.observer_pattern.gperadvice;

public class MouseEventTest {
    public static void main(String[] args) {
        try{
            MouseEventCalback callback=new MouseEventCalback();
            //注册事件
            Mouse mouse=new Mouse();
            mouse.addLisener(MouseEventType.ON_CLICK,callback);
            mouse.addLisener(MouseEventType.ON_MOVE,callback);
            mouse.addLisener(MouseEventType.ON_DOUBLE_CLICK,callback);

            //调用方法
            mouse.click();
            //双击方法
            mouse.doubleClick();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
