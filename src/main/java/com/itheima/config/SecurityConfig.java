package com.itheima.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.config.annotation.authentication.builders.*;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

import javax.servlet.RequestDispatcher;
import javax.sql.DataSource;
import java.net.URL;
import java.util.Collection;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 密码编码器
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        // 使用JDBC进行身份认证
        String userSQL = "select username, password, valid from t_user where username=?";
        String authoritySQL = "select u.username, a.authority from t_user u, t_authority a, " +
                "t_user_authority ua where ua.user_id=u.id and ua.authority_id=a.id and u.username=?";
        auth.jdbcAuthentication().passwordEncoder(encoder)
                .dataSource(dataSource)
                .usersByUsernameQuery(userSQL)
                .authoritiesByUsernameQuery(authoritySQL);
    }

    @Value("${COOKIE.VALIDITY}")
    private Integer COOKIE_VALIDITY;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 1.自定义用户访问控制
        http.authorizeRequests()
                .antMatchers("/", "/page/**", "/article/**", "/login").permitAll()
                .antMatchers("/back/**", "/assets/**", "/user/**", "/article_img/**").permitAll()
                .antMatchers("/admin/**").hasRole("admin")
                .anyRequest().authenticated();
        // 2.自定义用户登录控制
        http.formLogin()
                .loginPage("/login")
                .usernameParameter("username").passwordParameter("password")
                .successHandler((httpServletRequest, httpServletResponse, authentication) -> {
                   String url = httpServletRequest.getParameter("url");
                   // 获取被拦截登录的原始访问路径
                    HttpSessionRequestCache requestCache = new HttpSessionRequestCache();
                    SavedRequest savedRequest = requestCache.getRequest(httpServletRequest, httpServletResponse);
                    if(savedRequest != null)
                        // 如果存在原始拦截路径，登录成功后重定向到原始访问路径
                        httpServletResponse.sendRedirect(savedRequest.getRedirectUrl());
                    else if(url != null && !url.equals("")) {
                        // 跳转到之前所在页面
                        URL fullURL = new URL(url);
                        httpServletResponse.sendRedirect(fullURL.getPath());
                    } else {
                        // 直接登录的用户，根据用户角色分别重定向到后台首页和前台首页
                        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
                        boolean isAdmin = authorities.contains(new SimpleGrantedAuthority("ROLE_admin"));
                        if(isAdmin)
                            httpServletResponse.sendRedirect("/admin");
                        else
                            httpServletResponse.sendRedirect("/");
                    }
                })
                // 用户登录失败处理
                .failureHandler((httpServletRequest, httpServletResponse, e) -> {
                    String url = httpServletRequest.getParameter("url");
                    httpServletResponse.sendRedirect("/login?error&url=" + url);
                });
        // 3.设置用户登录后Cookie有效期，默认值
        http.rememberMe().alwaysRemember(true).tokenValiditySeconds(COOKIE_VALIDITY);
        // 4.自定义用户退出控制
        http.logout().logoutUrl("/logout").logoutSuccessUrl("/");
        // 5.针对访问无权限页面出现的403页面进行定制处理
        http.exceptionHandling().accessDeniedHandler((httpServletRequest, httpServletResponse, e) -> {
            RequestDispatcher dispatcher = httpServletRequest.getRequestDispatcher("/errorPage/comm/error_403");
            dispatcher.forward(httpServletRequest, httpServletResponse);
        });
    }
}
