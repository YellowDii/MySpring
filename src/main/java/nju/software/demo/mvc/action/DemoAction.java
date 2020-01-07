package nju.software.demo.mvc.action;

import nju.software.demo.service.IDemoService;
import nju.software.mvcframework.annotation.MyAutowired;
import nju.software.mvcframework.annotation.MyController;
import nju.software.mvcframework.annotation.MyRequestMapping;
import nju.software.mvcframework.annotation.MyRequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@MyController
@MyRequestMapping("/demo")
public class DemoAction {
    @MyAutowired
    private IDemoService demoService;
    @MyRequestMapping("/query")
    public void query(HttpServletRequest req, HttpServletResponse resp, @MyRequestParam("name") String name){
        String result=demoService.get(name);
        try{
            resp.getWriter().write(result);
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @MyRequestMapping("/add")
    public void add(HttpServletRequest req, HttpServletResponse resp,
                    @MyRequestParam("a") Integer a,@MyRequestParam("b") Integer b){
        try {
            resp.getWriter().write(a+"+"+b+"+"+"="+(a+b));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
    @MyRequestMapping("remove")
    public void remove(HttpServletRequest req,HttpServletResponse resp,
                       @MyRequestParam("id") Integer id){

    }
}
