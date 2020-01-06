package nju.java.pattern.observer_pattern.gperadvice;

public class ObserverTest {
    public static void main(String[] args) {
        GPer gper=GPer.getInstance();
        Teacher tom=new Teacher("Tom");
        Teacher mic=new Teacher("Mic");

        gper.addObserver(tom);
        gper.addObserver(mic);

        //业务逻辑代码
        Question question=new Question();
        question.setUserName("小明");
        question.setContent("观察者设计模式适用于哪些场景?");

        gper.publishQuestion(question);
    }
}
