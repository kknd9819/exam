package controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/checkCode")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session域中获取checkCode
        HttpSession session = req.getSession();
        System.out.println(session.getId());
        String checkCode = (String)session.getAttribute("checkCode");
        Map<String,Object> map = new HashMap<>();
        map.put("checkCode",checkCode);
        resp.getWriter().write(JSON.toJSONString(map));
    }
}
