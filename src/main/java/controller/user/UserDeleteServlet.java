package controller.user;

import com.alibaba.fastjson.JSON;
import dao.UserDao;
import dao.impl.UserDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = "/user/delete")
public class UserDeleteServlet extends HttpServlet {

    private static UserDao userDao = new UserDaoImpl();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userId = req.getParameter("userId");
        int id = Integer.parseInt(userId);


        //向数据访问层进行删除
        int c = userDao.deleteByUserId(id);

        Map<String,Object> map = new HashMap<>();
        if(c > 0){
            map.put("code",0);
        }

        resp.getWriter().write(JSON.toJSONString(map));
    }
}
