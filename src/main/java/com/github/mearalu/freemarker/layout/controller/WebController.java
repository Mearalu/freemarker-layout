package com.github.mearalu.freemarker.layout.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import static com.github.mearalu.freemarker.layout.interceptor.FreemarkerHandlerInterceptor.FREEMARKER_LAYOUT_KEY;

/**
 * Created with IntelliJ IDEA.
 *
 * @author meara
 * @date 2018/6/27
 */
@Controller
@RequestMapping("/")
public class WebController {

    @RequestMapping
    public String index() {
        return "index";
    }

    @RequestMapping("login")
    public String login(Model model) {
        //设置布局文件为空
        model.addAttribute(FREEMARKER_LAYOUT_KEY, "");
        return "login";
    }

    @RequestMapping("default_layout")
    public String defaultLayout(Model model, HttpServletRequest request) {
        //指定布局文件为空
        request.setAttribute(FREEMARKER_LAYOUT_KEY,"layout/default");
        return "default_layout";
    }

}
