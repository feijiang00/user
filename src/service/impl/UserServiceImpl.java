package service.impl;

import dao.UserDao;
import dao.impl.UserDaoImpl;
import domain.PageBean;
import domain.User;
import service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    //这里使用UserDao接口父类声明了子类UserDaoImpl对象，并可以调用其里面的方法，下面大多数函数都基于它的成员函数
    private UserDao dao = new UserDaoImpl();
    @Override
    public List<User> findAll() {
        return dao.findAll();
    }

    @Override
    public User login(User user) {
        return null;
    }

    @Override
    public void add(User user) {
        dao.add(user);
    }

    public void delete(String userId){
        dao.delete(userId);
    }

    @Override
    public User find(String userId) {
        User user = dao.findById(userId);
        return user;
    }

    @Override
    public void update(User user) {
        dao.update(user);
    }

    @Override
    public void delSelectedUser(String[] ids) {
        for (String id : ids) {
            dao.delete(id);
        }
    }

    /**
     * 分页查询
     * @param currentPage
     * @param rows
     * @return
     */
    @Override
    public PageBean findUserByPage(int currentPage, int rows) {

        PageBean<User> pageBean = new PageBean<User>();
        //设置参数
        pageBean.setCurrentPage(currentPage);
        pageBean.setRows(rows);
//        System.out.println("11111111");

        //调用dao查询totalCount总记录数
        int totalCount = dao.findTotalCount();
        pageBean.setTotalCount(totalCount);

        //计算总页码数
        int totalPage = totalCount % rows == 0 ? totalCount / rows : totalCount /rows +1;
        pageBean.setTotalPage(totalPage);
//        System.out.println("222222222");
        //计算最后的list集合
        int start = (currentPage - 1)*rows;
        List<User> userList = dao.finByPageList(start,rows);
        pageBean.setList(userList);
//        System.out.println("33333333");
        return pageBean;//得到pageBean对象
    }
}
