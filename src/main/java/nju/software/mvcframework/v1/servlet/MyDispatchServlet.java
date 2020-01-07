package nju.software.mvcframework.v1.servlet;

import nju.software.mvcframework.annotation.MyController;
import nju.software.mvcframework.annotation.MyRequestMapping;
import nju.software.mvcframework.annotation.MyService;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class MyDispatchServlet extends HttpServlet {
    private Map<String,Object> mapping=new HashMap<String, Object>();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        this.doPost(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try{
            doDisPatch(req,resp);
        }catch (Exception e){
            resp.getWriter().write("500 Exception "+ Arrays.toString(e.getStackTrace()));
        }
    }

    private void doDisPatch(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String url=req.getRequestURI();
        String contextPath=req.getContextPath();
        url=url.replace(contextPath,"").replaceAll("/+","/");
        if (!this.mapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        Method method=(Method) this.mapping.get(url);
        Map<String,String[]> paramas=req.getParameterMap();
        method.invoke(this.mapping.get(method.getDeclaringClass().getName()),
                new Object[]{req,resp,paramas.get("name")[0]});
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        InputStream is=null;
        try{
            Properties configContext=new Properties();
            is=this.getClass().getClassLoader().getResourceAsStream(
                    config.getInitParameter("contextConfiguration"));
            configContext.load(is);
            String scanPackage=configContext.getProperty("scanPackage");
            doScanner(scanPackage);
            for (String className:mapping.keySet()){
                if (!className.contains(".")){
                    continue;
                }
                Class<?> clazz=Class.forName(className);
                if (clazz.isAnnotationPresent(MyController.class)){
                    mapping.put(className,clazz.newInstance());
                    String baseUrl="";
                    if (clazz.isAnnotationPresent(MyRequestMapping.class)){
                        MyRequestMapping requestMapping=clazz.getAnnotation(MyRequestMapping.class);
                        baseUrl=requestMapping.value();
                    }
                    Method[] methods=clazz.getMethods();
                    for (Method method:methods){
                        if (!method.isAnnotationPresent(MyRequestMapping.class)){
                            continue;
                        }
                        MyRequestMapping requestMapping=method.getAnnotation(MyRequestMapping.class);
                        String url=(baseUrl+"/"+requestMapping.value()).replaceAll("/+","/");
                        mapping.put(url,method);
                        System.out.println("Mapped "+url+","+method);
                    }
                }else if (!clazz.isAnnotationPresent(MyService.class)){
                    MyService service=clazz.getAnnotation(MyService.class);
                    String beanName=service.value();
                    if ("".equals(beanName)){
                        beanName=clazz.getName();
                    }
                    Object instance=clazz.newInstance();
                    mapping.put(beanName,instance);
                    for (Class<?> i:clazz.getInterfaces()){
                        mapping.put(i.getName(),instance);
                    }
                }else {
                    continue;
                }
            }
        }catch (Exception e){

        }finally {
            if (is!=null){
                try {
                    is.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        System.out.println("MySpring framework is init");
    }

    /**
     * 对scanPackage下的.class文件 进行递推扫描 然后放入MAP中
     * @param scanPackage
     */
    private void doScanner(String scanPackage) {
        URL url=this.getClass().getClassLoader().getResource(
                "/"+scanPackage.replaceAll("\\.","/"));
        File classDir=new File(url.getFile());
        for (File file:classDir.listFiles()){
            if (file.isDirectory()){
                doScanner(scanPackage+"."+file.getName());
            }else {
                if (!file.getName().endsWith(".class")){
                    continue;
                }
                String clazzName=(scanPackage+"."+file.getName().replace(".class","."));
                mapping.put(clazzName,null);
            }
        }
    }
}
