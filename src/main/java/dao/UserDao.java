package dao;

import model.User;

import java.util.List;

public interface UserDao {

    List<User> findAll();
    int findCount();
    List<User> findPage(int pageNumber,int pageSize);

    int insert(User user);

    User findByUsername(String username);

    int update(User user);

    int deleteByUserId(int id);
}
