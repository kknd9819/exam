package controller.user;

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
import java.util.List;
import java.util.Map;

@WebServlet(urlPatterns = "/api/getAllUser")
public class UserGetServlet extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        int page = Integer.parseInt(req.getParameter("page"));
        int limit = Integer.parseInt(req.getParameter("limit"));

        //查询一共有多少个用户？
        int count = userDao.findCount();
        //查询所有用户数据
        List<User> list = userDao.findPage(page,limit);

        //数据封装
        Map<String,Object> map = new HashMap<>();
        map.put("code",0);
        map.put("count",count);
        map.put("data",list);

        resp.getWriter().write(JSON.toJSONString(map));
    }
}
