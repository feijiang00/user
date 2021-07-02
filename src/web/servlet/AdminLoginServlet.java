package web.servlet;

import domain.Admin;
import org.apache.commons.beanutils.BeanUtils;
import service.AdminService;
import service.impl.AdminServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");
        //判断验证码是否正确
        HttpSession session = request.getSession();
        String serverCheckCode = (String) session.getAttribute("CHECKCODE_SERVER");
        String userCheckCode = request.getParameter("verifycode");
        session.removeAttribute("CHECKCODE_SERVER");//确保验证码的一次性
        if(serverCheckCode == null || !serverCheckCode.equalsIgnoreCase(userCheckCode)){
            //验证码不正确
            request.setAttribute("login_msg","验证码错误");
            //跳转登录页面
             request.getRequestDispatcher("/login.jsp").forward(request,response);
             return;
        }

        Map<String,String[]> map = request.getParameterMap();
        //封装用户对象
        Admin admin = new Admin();
        try{
            //使用BeanUtils封装用户对象很方面，即将request方法中的参数封装成为一个类中，但必须变量名相同
            BeanUtils.populate(admin,map);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        //调用Service查询
        AdminService service = new AdminServiceImpl();
        Admin loginAdmin = service.login(admin);
        if(loginAdmin!=null){
            //登录成功
            session.setAttribute("admin",loginAdmin);
            //跳转页面
            response.sendRedirect(request.getContextPath()+"/index.jsp");
        }else {
            request.setAttribute("login_msg","用户名或密码错误");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }
    }
}
