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

@WebServlet(urlPatterns = "/user/update")
public class UserUpdateServlet extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        int userId = Integer.parseInt(req.getParameter("userId"));

        //更新
        User user = new User();
        user.setUserId(userId);
        user.setUsername(username);
        user.setPassword(password);

        int c = userDao.update(user);

        Map<String,Object> map = new HashMap<>();
        if(c > 0){
            map.put("code",0);
            map.put("username",user.getUsername());
        }

        resp.getWriter().write(JSON.toJSONString(map));

    }
}
