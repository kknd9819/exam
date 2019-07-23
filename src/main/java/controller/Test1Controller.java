package controller;

import com.alibaba.fastjson.JSON;
import dao.UserDao;
import dao.impl.UserDaoImpl;
import model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/test1")
public class Test1Controller extends HttpServlet {

    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");

        Map<String,Object> map = new HashMap<>();
        User user = userDao.findByUsername(username);
        if(user != null){ //说明用户名被占用
            map.put("msg","用户名被占用");
            resp.getWriter().write(JSON.toJSONString(map));
            return;
        }

        User u = new User();
        u.setUsername(username);
        u.setPassword(password);

        int c = userDao.insert(u);

        if(c > 0){ //保存进数据库成功
            map.put("msg","保存成功");
        } else {
            map.put("msg","保存失败，服务端有异常");
        }

        resp.getWriter().write(JSON.toJSONString(map));
    }
}
