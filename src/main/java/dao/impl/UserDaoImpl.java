package dao.impl;

import dao.UserDao;
import model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JdbcUtil;

import java.util.List;

public class UserDaoImpl implements UserDao {

    private JdbcTemplate template = new JdbcTemplate(JdbcUtil.getDataSource());


    @Override
    public List<User> findAll() {
        String sql = "select * from t_user";
        BeanPropertyRowMapper<User> rm = BeanPropertyRowMapper.newInstance(User.class);
        return template.query(sql,rm);
    }

    @Override
    public int findCount() {
        String sql = "select count(1) from t_user";
        return template.queryForObject(sql,Integer.class);
    }

    @Override
    public List<User> findPage(int pageNumber, int pageSize) {
        int start = (pageNumber - 1) * pageSize;
        String sql = "select * from t_user limit ?,?";
        BeanPropertyRowMapper<User> rm = BeanPropertyRowMapper.newInstance(User.class);
        Object[] objs = new Object[2];
        objs[0] = start;
        objs[1] = pageSize;
        return template.query(sql,objs,rm);
    }

    @Override
    public int insert(User user) {
        String sql = "insert into t_user (username,password) values (?,?)";
        return template.update(sql,user.getUsername(),user.getPassword());
    }

    @Override
    public User findByUsername(String username) {
        String sql = "select * from t_user where username = ?";
        BeanPropertyRowMapper<User> rm = BeanPropertyRowMapper.newInstance(User.class);
        Object[] objs = new Object[]{username};
        List<User> list = template.query(sql,objs,rm);
        return list.size() == 0? null : list.get(0);
    }

    @Override
    public int update(User user) {
        String sql = "update t_user set username=?,password=? where userId=?";
        return template.update(sql,user.getUsername(),user.getPassword(),user.getUserId());
    }

    @Override
    public int deleteByUserId(int id) {
        String sql = "delete from t_user where userId = ?";
        return template.update(sql,id);
    }
}
