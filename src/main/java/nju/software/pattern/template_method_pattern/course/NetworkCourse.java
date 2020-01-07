package nju.software.pattern.template_method_pattern.course;

//模板会有一个或者多 未现实方法，而且这几个未 现方法有固 的执行顺序
public abstract class NetworkCourse {
    protected final void createCourse(){
        //发布预习资料
        this.postPreResource();
        //制作课堂ppt
        this.createPPT();
        //在线直播
        this.liveVideo();
        //提交课堂笔记
        this.postNote();
        //提交源码
        this.postSource();

        //布置作业，有些课是没有作业的，有些课是有作业的
        //如果有作业，检查作业，如果没有作业，流程结束
        if (needHomework()){
            checkHomework();
        }
    }
    abstract void checkHomework();

    //钩子方法：实现流程的微调
    protected boolean needHomework(){
        return false;
    }

    final void postPreResource() {
        System.out.println("发布预习资料");
    }

    final void postNote(){
        System.out.println("提交课堂笔记");
    }
    final void liveVideo(){
        System.out.println("在线直播");
    }
    final void createPPT(){
        System.out.println("制作课堂ppt");
    }
    final void postSource(){
        System.out.println("提交源码");
    }
}
