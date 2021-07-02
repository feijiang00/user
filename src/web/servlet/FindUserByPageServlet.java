package web.servlet;

import domain.PageBean;
import service.UserService;
import service.impl.UserServiceImpl;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/FindUserByPageServlet")
public class FindUserByPageServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //0.设置编码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        //1. 接收请求参数currentPage rows
        int currentPage = Integer.parseInt(request.getParameter("currentPage"));
        int rows = Integer.parseInt(request.getParameter("rows"));

//        int currentPage = 1;
//        int rows =5;

        //2.调用Service查询PageBean
        UserService service = new UserServiceImpl();
        PageBean pageBean = service.findUserByPage(currentPage,rows);

        //3.将Page存入request
        request.setAttribute("pageBean",pageBean);

        System.out.println(pageBean);
        //4.转发到list.jsp展示
        request.getRequestDispatcher("/list.jsp").forward(request,response);


    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doPost(request, response);
    }
}
