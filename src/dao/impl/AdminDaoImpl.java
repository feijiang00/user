package dao.impl;

import dao.AdminDao;
import domain.Admin;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import util.JDBCUtils;

public class AdminDaoImpl implements AdminDao {
    //要使用jdbcTemplate，则需要生成一个其对象，构造方法需要带入一个数据库连接池，我们不必在去释放资源什么的，因为
    //这个框架帮我们做好了，数据库连接池。数据库连接池有很多配置文件，必然放在配置文档中，则需要加载配置文档，那么
    //就有了工具类JDBCUtils来加载，统一放在那，具体看之前学的数据库。
    JdbcTemplate jdbcTemplate = new JdbcTemplate(JDBCUtils.getDataSource());
    @Override
    public Admin findAdminByUserNameAndPassword(String userName, String password) {
        try{
            String sql = "select * from admin where user_name = ? and password = ?";
            Admin admin = jdbcTemplate.queryForObject(sql,new BeanPropertyRowMapper<Admin>(Admin.class),userName,password);
            return admin;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public void add(Admin admin){
        try{
            String sql = "insert into admin values(null,?,?)";
            jdbcTemplate.update(sql,admin.getUserName(),admin.getPassword());
        } catch (DataAccessException e) {
            e.printStackTrace();
        }
    }
}
