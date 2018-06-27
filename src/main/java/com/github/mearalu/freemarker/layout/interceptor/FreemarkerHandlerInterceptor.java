package com.github.mearalu.freemarker.layout.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Objects;

/**
 * Created with IntelliJ IDEA.
 *
 * @author meara
 * @date 2018/6/27
 */
public class FreemarkerHandlerInterceptor implements HandlerInterceptor {
    public static final String DEFAULT_LAYOUT = "layout/default";
    public static final String FREEMARKER_LAYOUT_KEY = "freemarker_layout_template";

    private String errorPage = "/error";
    private String defaultLayout = DEFAULT_LAYOUT;

    public String getErrorPage() {
        return errorPage;
    }

    public FreemarkerHandlerInterceptor setErrorPage(String errorPage) {
        this.errorPage = errorPage;
        return this;
    }

    public String getDefaultLayout() {
        return defaultLayout;
    }

    public FreemarkerHandlerInterceptor setDefaultLayout(String defaultLayout) {
        this.defaultLayout = defaultLayout;
        return this;
    }

    /**
     * logger
     **/
    public static final Logger logger = LoggerFactory.getLogger(FreemarkerHandlerInterceptor.class);

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        if (modelAndView != null) {
            String bodyTemplateName = modelAndView.getViewName();
            Object layout = modelAndView.getModel().get(FREEMARKER_LAYOUT_KEY);
            layout = layout == null ? request.getAttribute(FREEMARKER_LAYOUT_KEY) : layout;
            if (layout == null) {
                layout = defaultLayout;
            }
            String error=errorPage.replaceAll("^/","");
            if (!"".equals(layout) && !error.equals(bodyTemplateName)) {
                modelAndView.addObject("bodyTemplateName", bodyTemplateName);
                modelAndView.setViewName(Objects.toString(layout));
                logger.debug("视图:[{}]已经应用布局:[{}]", bodyTemplateName, modelAndView.getViewName());
            }
        }
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
