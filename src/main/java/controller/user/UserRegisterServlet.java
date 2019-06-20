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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user/register")
public class UserRegisterServlet extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String checkCode = req.getParameter("checkCode");
        HttpSession session = req.getSession();
        String cc = (String)session.getAttribute("checkCode");
        if(cc.equals(checkCode)){
            String username = req.getParameter("username");
            String password = req.getParameter("password");

            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            int c = userDao.insert(user);

            Map<String,Object> map = new HashMap<>();
            if(c > 0){ //新增成功
                map.put("code",0);
            }
            resp.getWriter().write(JSON.toJSONString(map));
        }
    }
}
