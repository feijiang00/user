package service;

import domain.Admin;

/**
 * 管理员业务管理接口
 */
public interface AdminService {

    Admin login(Admin admin);

    void add(Admin admin);
}
