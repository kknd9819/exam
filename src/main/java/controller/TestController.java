package controller;

import com.alibaba.fastjson.JSON;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/test")
public class TestController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String parameter = req.getParameter("fuck");
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("msg","成功");
        map.put("data","你传过来的参数是：" + parameter);

        String jsonp = req.getParameter("jsonpCallback");
        String result = jsonp + "(" + JSON.toJSONString(map) + ")";
        resp.getWriter().write(result);
    }
}
