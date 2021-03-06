package controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/checkCode")
public class CheckCodeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //从session域中获取checkCode
        HttpSession session = req.getSession();
        String checkCode = (String)session.getAttribute("checkCode");
        Map<String,Object> map = new HashMap<>();
        map.put("checkCode",checkCode);

        //组装为jsonp格式的数据
        String jsonpCallback = req.getParameter("jsonpCallback");
        String result = jsonpCallback + "(" + JSON.toJSONString(map) + ")";
        resp.getWriter().write(result);
    }
}
