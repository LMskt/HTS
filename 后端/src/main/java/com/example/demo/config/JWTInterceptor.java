package com.example.demo.config;

import com.example.demo.util.JWT;
import io.jsonwebtoken.*;
import org.springframework.transaction.reactive.TransactionalOperatorExtensionsKt;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JWTInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String myuri=request.getRequestURI();


        if (RequestMethod.OPTIONS.toString().equals(request.getMethod())){
            System.out.println("排除生成token的路径");
            return true;
        }
        String token=request.getHeader("jwt");
        if(token==null||"".equals(token.trim())){
            throw new ServletException("token获取失败");
        }

        try{
            Claims claims=JWT.parseJwt(token);
            request.setAttribute("ID",String.valueOf(claims.getSubject()));
            return true;
        }catch (ExpiredJwtException e){
            throw new ServletException("身份过期，请重新登录");
        }catch (UnsupportedJwtException | MalformedJwtException | SignatureException |IllegalArgumentException e){
            throw new ServletException("jwt解析失败");
        }
    }
}
