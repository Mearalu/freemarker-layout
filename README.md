# freemarker-layout
freemarker布局实现 透明化处理 使用拦截器实现

设置默认布局文件
```java
    @Bean
    public FreemarkerHandlerInterceptor freemarkerInterceptor() {
        FreemarkerHandlerInterceptor freemarkerHandlerInterceptor = new FreemarkerHandlerInterceptor();
        //设置默认布局文件
        freemarkerHandlerInterceptor.setDefaultLayout("layout/main");
        return freemarkerHandlerInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(freemarkerInterceptor()).addPathPatterns("/**");
    }
```
设置布局文件设置 为空字符则表示不应用布局
```java
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

```