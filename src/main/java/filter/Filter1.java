package filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = "/*")
public class Filter1 implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;

        //所有请求字符集统一UTF-8
        req.setCharacterEncoding("UTF-8");

        //添加允许跨域访问设置
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
        resp.setHeader("Access-Control-Max-Age","0");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        //统一设置相应内容为json
        resp.setContentType("application/json;charset=utf-8");

        chain.doFilter(req,resp);
    }

    @Override
    public void destroy() {

    }
}
