package web.servlet;


import domain.Admin;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminService;
import service.impl.AdminServiceImpl;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/UserRegisterServlet")
public class UserRegisterServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        Map<String,String[]> map = request.getParameterMap();

        Admin admin = new Admin();

        try{
            //使用BeanUtils封装用户对象很方面，即将request方法中的参数封装成为一个类中，但必须变量名相同
            //这里，封装如果前端传来的值是多个，它只会封装，domain中User已经存在的，并且字段名需要对应
            BeanUtils.populate(admin,map);
            /**
             * 打印出封装后的user看看
             */
            System.out.println(admin);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Service
        AdminService service = new AdminServiceImpl();
        service.add(admin);

        HttpSession session = request.getSession();
        //注册后，就不存了，因为跳去了登录页面，而不是首页
//        session.setAttribute("user",user);
        session.setAttribute("success","注册成功！");

        response.sendRedirect(request.getContextPath()+"/login.jsp");


    }
}
