package com.zpstudio.ws;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.HashSet;
import java.util.Set;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2018/6/28 16:43
 **/
@Controller
public class TestController extends BaseController{
    public static Set<String> set = new HashSet<>();
    @Autowired
    private HttpServletRequest requestAutowired;
    private HttpServletRequest requestByModelAttribute;
    @ModelAttribute
    public void bindRequest(HttpServletRequest request){
        this.requestByModelAttribute = request;
    }

    @RequestMapping("/getRequestByParam")
    public void test(HttpServletRequest request) throws InterruptedException{
        checkValue(request);
    }

    @RequestMapping("/getRequestByAutowired")
    public void test2() throws InterruptedException{
        checkValue(requestAutowired);
    }

    @RequestMapping("/getRequestByParentAutowired")
    public void test3() throws InterruptedException{
        checkValue(super.requestByBaseController);
    }

    @RequestMapping("/getRequestByContextHolder")
    public void test4() throws InterruptedException{
        HttpServletRequest request = ((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes())
                .getRequest();
        checkValue(request);
    }

    @RequestMapping("/getRequestByModelAttribute")
    public void test5() throws InterruptedException{
        checkValue(requestByModelAttribute);
    }

    private void checkValue(HttpServletRequest request) throws InterruptedException{
        String value = request.getParameter("key");
        if(set.contains(value)){
            System.out.println(value + "\t repeat, request not security while concurrency");
        }else{
            System.out.println(value);
            set.add(value);
        }
        Thread.sleep(1000);
    }
}
