package com.cx.edu.jwt;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.cx.edu.exception.ResultEnum;
import com.cx.edu.redis.service.RedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@WebFilter(filterName = "jwtFilter", urlPatterns = "/*")
@SuppressWarnings("unchecked")
public class JwtFilter extends GenericFilterBean {

    @Autowired
    private RedisService redisService;

    @SuppressWarnings("resources")
    @Override
    public void doFilter(final ServletRequest req, final ServletResponse res, final FilterChain chain) throws IOException, ServletException {
        final HttpServletResponse response = (HttpServletResponse) res;
        final HttpServletRequest request = (HttpServletRequest) req;
        final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String url = request.getRequestURI().substring(request.getContextPath().length());
        response.addHeader("Access-Control-Allow-Origin", "*");
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.setHeader("Access-Control-Allow-Headers", "x-requested-with,token,content-type");
        response.addHeader("Access-Control-Max-Age", "1800");
        if (url.contains("login") || url.contains("test") || url.contains("register")) {
            chain.doFilter(req, res);
            return;
        }
        //静态资源不拦截
        if (url.contains("/img/") || url.contains("/js/") || url.contains("/css/") || url.contains("/web/") || url.contains("/layui/")) {
            chain.doFilter(req, res);
            return;
        }
        if (authHeader == null) {
            response(req, res, ResultEnum.AUTHORIZATION_ERROR);
            return;
        }
        UserContext.AuthorizedUser authorizedUser = null;
        try {
            authorizedUser = JwtToken.verifyToken(authHeader);
        } catch (JWTVerificationException e) {
            log.warn("token验证失败", e);
            response(req, res, ResultEnum.AUTHORIZATION_FAIL);
            return;
        }
        String redisToken = (String)redisService.getValue(String.valueOf(authorizedUser.getId()));
        if (StringUtils.isEmpty(redisToken) || !authHeader.equals(redisToken)) {
            log.warn("token不存在");
            response(req, res, ResultEnum.AUTHORIZATION_IS_NOTE_EXIST);
            return;
        }
        new UserContext(authorizedUser);
        chain.doFilter(req, res);
        return;
    }

    private void response(ServletRequest req, final ServletResponse res, ResultEnum resultEnum) throws IOException {
        final HttpServletResponse response = (HttpServletResponse) res;
        req.setCharacterEncoding("UTF-8");
        res.setContentType("application/json;charset=utf-8");
        response.setStatus(401);
        response.getWriter().write(resultEnum.toJson());

    }
}