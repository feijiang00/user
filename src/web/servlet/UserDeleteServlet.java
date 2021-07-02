package web.servlet;

import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/UserDeleteServlet")
public class UserDeleteServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //1.设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

//        Integer id = Integer.parseInt(request.getParameter("id"));
        String userid = request.getParameter("userId");
        System.out.println(userid);
        UserService userService = new UserServiceImpl();
        userService.delete(userid);//这里有一个自动拆箱

        //这里要跳转到查询用户列表，而不是用户列表
        response.sendRedirect(request.getContextPath()+"/FindUserByPageServlet?currentPage=1&rows=5");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }


}
