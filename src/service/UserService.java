package service;

import domain.PageBean;
import domain.User;

import java.util.List;

/**
 * 用户管理的业务接口
 */
public interface UserService{
    /**
     * 查询所有用户信息
     * @return
     */
    public List<User> findAll();

    /**
     * 登录方法
     * @param user
     * @return
     */
    User login(User user);

    void add(User user);

    void delete(String userName);

    User find(String userName);

    void update(User user);

    /**
     * 删除选中用户
     */
    void delSelectedUser(String[] ids);

    PageBean findUserByPage(int currentPage, int rows);
}
