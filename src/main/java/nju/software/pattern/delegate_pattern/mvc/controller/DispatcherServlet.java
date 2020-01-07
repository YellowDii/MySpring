package nju.software.pattern.delegate_pattern.mvc.controller;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends HttpServlet {
    //下面这种 如果面对上千个Controller if...else if会写到手抽筋~~~~
    //需要优化
    /*
    private void doDispatch(HttpServletRequest request,HttpServletResponse response)throws Exception {
        String uri=request.getRequestURI();
        String mid=request.getParameter("mid");

        if ("getMemberById".equals(uri)){
            new MemberController().getMemberById(mid);
        }else if ("getOrderById".equals(uri)){
            new OrderController().getOrderById(mid);
        }else if ("logout".equals(uri)){
            new SystemController().logout();
        }else {
            response.getWriter().write("404 Not Found!!");
        }
    }

     */
    private List<Handler> handlerMapping = new ArrayList<Handler>();

    public void init() throws ServletException {
        try {
            Class<?> memberContollerClass = MemberController.class;
            handlerMapping.add(new Handler()
                    .setController(memberContollerClass.newInstance())
                    .setMethod(memberContollerClass.getMethod("getMemberById", new Class[]{String.class}))
                    .setUrl("/web/getMemberById.json"));
        } catch (Exception e) {

        }
    }

    private void doDispatch(HttpServletRequest request, HttpServletResponse response) {
        //获取用户请求的URL
        //如果按照J2EE的标准，每个URL对应一个Serlvet, URL从浏览器输入
        String uri = request.getRequestURI();
        //Servlet拿到URL以后，要做权衡(要做判断，要做选择)
        //根据用户请求的URL,找到这个URL对应的某个Java类的方法
        //通过获取的URL去做handlerMapping (我们认为它是策略常量)
        Handler handle = null;
        for (Handler h : handlerMapping) {
            if (uri.equals(h.getUrl())) {
                handle = h;
                break;
            }
        }
        //将具体的任务分发给Method (通过反射调用对应的方法)
        Object object = null;
        try {
            object = handle.getMethod().invoke(handle.getController(), request.getParameter("mid"));
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
            doDispatch(req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    class Handler {
        private Object controller;
        private Method method;
        private String url;

        public Object getController() {
            return controller;
        }

        public Handler setController(Object controller) {
            this.controller = controller;
            return this;
        }

        public Method getMethod() {
            return method;
        }

        public Handler setMethod(Method method) {
            this.method = method;
            return this;
        }

        public String getUrl() {
            return url;
        }

        public Handler setUrl(String url) {
            this.url = url;
            return this;
        }
    }
}
