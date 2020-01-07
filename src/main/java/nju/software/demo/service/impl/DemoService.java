package nju.software.demo.service.impl;

import nju.software.demo.service.IDemoService;
import nju.software.mvcframework.annotation.MyService;

/**
 * 核心业务逻辑
 */
@MyService
public class DemoService implements IDemoService {
    public String get(String name){
        return "My name is "+name;
    }
}
