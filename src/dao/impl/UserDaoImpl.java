package dao.impl;

import dao.UserDao;
import domain.User;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

import java.util.List;

public class UserDaoImpl implements UserDao {
    JdbcTemplate template = new JdbcTemplate(JDBCUtils.getDataSource());

    @Override
    public User findUserBYUsernameAndPassword(String userName, String password) {
        try{
            String sql = "select * from user where user_name = ? and password = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<User>(User.class),userName,password);
            return user;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void add(User user) {
        try{
            String sql = "insert into user values(null,?,?,?,?,?,?)";
            template.update(sql,user.getUserName(),user.getSex(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(String userId) {
       try {
           String sql = "delete from user where id = ?";
           template.update(sql,userId);
       }catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public User findById(String userId) {
        try{
            String sql = "select * from user where id = ?";
            User user = template.queryForObject(sql,new BeanPropertyRowMapper<>(User.class),userId);
            return user;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public void update(User user) {
        try{
            String sql = "update user set user_name=?, sex=?,age=?,address=?,qq=?,email=? where user_name = ?";
            template.update(sql,user.getUserName(),user.getSex(),user.getAge(),user.getAddress(),user.getQq(),user.getEmail(),user.getUserName());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() {
        //使用JDBC来操作数据库
        String sql = "select * from user";
        List<User> userList = template.query(sql,new BeanPropertyRowMapper<User>(User.class));
        return userList;
    }

    @Override
    public int findTotalCount() {
        String sql = "SELECT COUNT(*) FROM USER";
        //这里的返回值是一个int类型，那么我们使用int的封装类型，使用queryForObject来查询
        int totalCount = template.queryForObject(sql,Integer.class);
        System.out.println(totalCount);
         return totalCount;
    }

    @Override
    public List<User> finByPageList(int start, int rows) {
        try{
            String sql = "select * from user limit ?,?";
            List<User> user = template.query(sql,new BeanPropertyRowMapper<>(User.class),start,rows);//
            return  user;
        }catch (Exception e ){
            e.printStackTrace();
            return null;
        }
    }
}
