package com.zpstudio.ws;

import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;

/**
 * @Description TODO
 * @Author zhangpeng
 * @Date 2018/6/28 17:10
 **/
public class BaseController {
    @Autowired
    protected HttpServletRequest requestByBaseController;
}
