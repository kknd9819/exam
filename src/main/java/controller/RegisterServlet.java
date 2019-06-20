package controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/register")
public class RegisterServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();
        Map<String,Object> map = new HashMap<>();

        //需要获取前端填的验证码和我们后端生成的验证码进行比对
        //如果比对正确才进行下面的操作
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String code = (String)session.getAttribute("checkCode");
        if(!code.equalsIgnoreCase(checkCode)){ //判断session存的验证码和我们输入的是否一样
            out.write(JSON.toJSONString(map));
            return;
        }

        //只有成功的情况下需要移出掉当前session存的这个验证码
        session.removeAttribute("checkCode");

        //保存进数据库
        String username = req.getParameter("username");
        String pwd = req.getParameter("pwd");


    }
}
