package com.itheima.web.client;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@Controller
public class LoginController {

    /**
     *  登录请求
     * @param request
     * @param map
     * @return
     */
    @GetMapping("/login")
    public String login(HttpServletRequest request, Map map) {
        // 分别获取请求头和参数url中的原始非拦截的访问路径
        String referer = request.getHeader("Referer");
        String url = request.getParameter("url");
        // 如果参数url中已经封装了原始页面路径，直接返回该路径
        if(url != null && !url.equals(""))
            map.put("url", url);
        // 如果请求头本身包含登录，将重定向url为空，让后台通过角色进行跳转
        else if(referer != null && referer.contains("/login"))
            map.put("url", "");
        // 否则的话就记住请求头的原始访问路径
        else
            map.put("url", referer);
        return "comm/login";
    }

    @GetMapping("/errorPage/{page}/{code}")
    public String AccessExceptionHandler(@PathVariable String page, @PathVariable String code) {
        return page + "/" + code;
    }


}
