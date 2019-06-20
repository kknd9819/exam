package controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = "/kuayu")
public class KuaYuServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //添加允许跨域访问设置
        resp.setHeader("Access-Control-Allow-Origin","*");
        resp.setHeader("Access-Control-Allow-Methods","GET,POST,OPTIONS,DELETE,PUT");
        resp.setHeader("Access-Control-Max-Age","0");
        resp.setHeader("Access-Control-Allow-Credentials", "true");

        //响应类型为纯文本消息
        resp.setContentType("text/plain;charset=utf-8");

        resp.getWriter().write("服务器宕机");


    }
}
