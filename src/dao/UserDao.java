package dao;

import domain.User;

import java.util.List;

public interface UserDao {
    /**
     * 用户操作的DAO
     */

    /**
     * 验证用户登录
     * @param userName
     * @param password
     * @return
     */
    User findUserBYUsernameAndPassword(String userName, String password);

    /**
     * 添加用户操作
     * @param user
     */
    void add(User user);

    /**
     * 删除用户根据用户id
     * @param userName
     */
    void delete(String  userName);

    /**
     * 查询某个用户通过用户id
     * @param userName
     * @return
     */
    User findById(String  userName);

    /**
     * 更新某个用户的资料根据用户id
     * @param user
     */
    void update(User user);

    /**
     * 查询总记录数
     * @return
     */
    public List<User> findAll();

    /**
     * 查询总记录数的个数
     * @return
     */
    int findTotalCount();

    /**
     * 根据start，rows进行分页查询
     * @param start
     * @param rows
     * @return
     */
    List<User> finByPageList(int start, int rows);
}
