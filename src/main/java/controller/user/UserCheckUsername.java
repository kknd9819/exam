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
import java.util.Map;

@WebServlet(urlPatterns = "/checkUsername")
public class UserCheckUsername extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        Map<String,Object> map = new HashMap<>();

        if(!username.equals("")){
            User user = userDao.findByUsername(username);
            if(user == null){
                map.put("code",0);
            }
        }

        resp.getWriter().write(JSON.toJSONString(map));
    }
}
