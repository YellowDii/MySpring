package nju.software.mvcframework.v2.servlet;

import nju.software.mvcframework.annotation.MyAutowired;
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
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;

public class MyDispatchServlet extends HttpServlet {

    //保存application.properties配置文件中的内容
    private Properties contextConfig=new Properties();
    //保存扫描的所有类名
    private List<String> classNames=new ArrayList<String>();
    //传说中的IOC容器，我们来揭开它的神秘面纱
    //为了简化程序，暂时不考虑ConcurrentHashMap
    //主要还是关注设计思想和原理
    private Map<String,Object> ioc=new HashMap<String, Object>();

    //保存url和Method的对应关系
    private Map<String,Method> handlerMapping=new HashMap<String, Method>();
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
        if (!this.handlerMapping.containsKey(url)){
            resp.getWriter().write("404 Not Found!!!");
            return;
        }
        Method method=(Method) this.handlerMapping.get(url);
        //第一个参数：方法所在的实例
        //第二个参数：调用时所需要的实参
        Map<String,String[]> paramas=req.getParameterMap();
        //投机取巧的方式
        String beanName=toLowerFirstCase(method.getDeclaringClass().getSimpleName());
        method.invoke(ioc.get(beanName),new Object[]{req,resp,paramas.get("name")[0]});
        //System.out.println(method);
    }



    @Override
    public void init(ServletConfig config) throws ServletException {
        //1.加载配置文件
        doLoadConfig(config.getInitParameter("contextConfigLocation"));
        //2.扫描相关的类
        doScanner(contextConfig.getProperty("scanPackage"));
        //3.初始扫描到的类，并且将它们放入IoC容器中
        doInstance();
        //4.完成依赖注入
        doAutowired();
        //5.初始化HandlerMapping
//        initHandlerMapping();

        System.out.println("MySpring framework is init");
    }


    private void doLoadConfig(String contextConfigLocation) {
        //直接通过类路径找到Spring主配置文件所在的路径
        //并且将其读取出来放到Properties对象中
        //相当于将scanPackage=nju.software.demo保存到了内存中
        InputStream fis=this.getClass().getClassLoader().getResourceAsStream(contextConfigLocation);
        try{
            contextConfig.load(fis);
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (null!=fis){
                try{
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    private void doScanner(String scanPackage) {
        //scanPackage = nju.software.demo,存储的是包路径
        //转换为文件路径，实际上就是把.替换为/
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
                classNames.add(clazzName);
            }
        }
    }

    private void doInstance() {
        //初始化,为DI（dependency injection）做准备
        if (classNames.isEmpty()){
            return;
        }
        try{
            for (String className:classNames){
                Class<?> clazz=Class.forName(className);

                //什么样的类才需要初始化呢？
                //加了注解的类才初始化，怎么判断
                //为了简化代码逻辑，主要体会设计思路，只用@Controller和@Service举例
                //@Componment等就不一一举例了
                if (clazz.isAnnotationPresent(MyController.class)){
                    Object instance=clazz.newInstance();
                    //Spring默认类名首字母小写
                    String beanName=toLowerFirstCase(clazz.getSimpleName());
                    ioc.put(beanName,instance);
                }else if (clazz.isAnnotationPresent(MyService.class)){
                    //1.自定义的beanName
                    MyService service=clazz.getAnnotation(MyService.class);
                    String beanName=service.value();
                    //2.默认类名首字母小写
                    if ("".equals(beanName.trim())){
                        beanName=toLowerFirstCase(clazz.getSimpleName());
                    }
                    Object instance=clazz.newInstance();
                    ioc.put(beanName,instance);

                    //3.根据类型自动赋值，小技巧
                    for (Class<?> i:clazz.getInterfaces()){
                        if (ioc.containsKey(i.getName())){
                            throw new Exception("The “"+i.getName()+"”is exists!");
                        }
                        //把接口的类型直接当成Key
                        ioc.put(i.getName(),instance);
                    }
                }else {
                    continue;
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private String toLowerFirstCase(String simpleName) {
        char[] chars=simpleName.toCharArray();
        chars[0]+=32;
        return String.valueOf(chars);
    }

    private void doAutowired() {
        if (ioc.isEmpty()){
            return;
        }
        for (Map.Entry<String,Object> entry:ioc.entrySet()){
            //获取所有的字段，包括private、protected、default类型的
            //正常来说，普通的OOP编程只能获得public类型的字段
            Field[] fields=entry.getValue().getClass().getDeclaredFields();
            for (Field field:fields){
                if (!field.isAnnotationPresent(MyAutowired.class)){
                    continue;
                }
//                MyAutowired autowired=field.getAnnotations(MyAutowired.class);

                //如果用户没有自定义beanName
            }
        }
    }
}
