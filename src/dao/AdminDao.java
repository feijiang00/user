package dao;

import domain.Admin;

public interface AdminDao {
    /**
     * 管理员操作的DAO
     */

    /**
     * 管理员登录
     * @param userName
     * @param password
     * @return
     */
    Admin findAdminByUserNameAndPassword(String userName,String password);

    void add(Admin admin);

}
