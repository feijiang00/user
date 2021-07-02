
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<!-- 网页使用的语言 -->
<html lang="zh-CN">
<head>
    <!-- 指定字符集 -->
    <meta charset="utf-8">
    <!-- 使用Edge最新的浏览器的渲染方式 -->
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- viewport视口：网页可以根据设置的宽度自动进行适配，在浏览器的内部虚拟一个容器，容器的宽度与设备的宽度相同。
    width: 默认宽度与设备的宽度相同
    initial-scale: 初始的缩放比，为1:1 -->
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>学生信息管理系统</title>

    <!-- 1. 导入CSS的全局样式 -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
    <script src="js/jquery-2.1.0.min.js"></script>
    <!-- 3. 导入bootstrap的js文件 -->
    <script src="js/bootstrap.min.js"></script>
    <style type="text/css">
        td, th {
            text-align: center;
        }
    </style>
    <script>
        //需要页面加载完成后再去获取,通过获取《删除选择》超链接的id，绑定了一个点击事件，
        //当我们点击该超链接，就会提交表单，表单是复选框的，
        window.onload = function (){

            document.getElementById("delSelected").onclick = function (){
                //获取表单并提交，给表单加id
                //onsubmit()这个方法就是完成表单提交
                if(confirm("确定删除？")){
                    var flag = false;
                    var cbs = document.getElementsByName("userId");
                    for(var i=0;i<cbs.length;i++){
                        if(cbs[i].checked){
                            flag=true;
                            break
                        }
                    }
                }
                console.log(flag);//这里未定义，下面的if判断为false
                if(flag) {
                    document.getElementById("form").submit();
                    // alert("aaa");
                }
            }


            document.getElementById("firstCb").onclick = function (){
                //获取所有的cb
                var cbs = document.getElementsByName("userId");
                for(var i=0;i<cbs.length;i++){
                    cbs[i].checked = this.checked;
                }
            }


        }
    </script>
</head>
<body>

<div class="container">

    <h3 style="text-align: center">学生信息列表</h3>
    <!--左浮动-->
    <div style="float: left">
        <form class="form-inline">
            <div class="form-group">
                <label for="exampleInputName2">姓名</label>
                <input type="text" class="form-control" id="exampleInputName2" >
            </div>
            <div class="form-group">
                <label for="exampleInputName3">籍贯</label>
                <input type="text" class="form-control" id="exampleInputName3" >
            </div>
            <div class="form-group">
                <label for="exampleInputEmail2">邮箱</label>
                <input type="email" class="form-control" id="exampleInputEmail2">
            </div>
            <button type="submit" class="btn btn-default">查询</button>
        </form>
    </div>

    <!--右浮动-->
    <form id="form" action="${pageContext.request.contextPath}/DelSelectedServlet" method="post">
    <table border="1" class="table table-bordered table-hover">
        <div style="float: right; margin:5px ">
            <a class="btn btn-primary" href="${pageContext.request.contextPath}/add.jsp">添加联系人</a></td>
            <%--这里的void(0)比较难理解，其实是href点击会跳转，变成void(0)则是什么也不执行，那么删除选中是如何实现的呢，通过id属性得到这个a标签对象
                        就能获取到它的所有东西了！！！比如它的属性onclick，我们直接再js代码中进行处理--%>
            <a class="btn btn-primary" href="javascript:void(0);" id="delSelected">删除选中</a></td>
        </div>
        <tr class="success">
            <th><input type="checkbox" id="firstCb"></th>
            <th>编号</th>
            <th>姓名</th>
            <th>性别</th>
            <th>年龄</th>
            <th>籍贯</th>
            <th>QQ</th>
            <th>邮箱</th>
            <th>操作</th>
        </tr>
        <%--要把这整个列表进行遍历--%>
        <c:forEach items="${pageBean.list}" var="user" varStatus="s">
        <tr>
            <td><input type="checkbox" name="userId" value="${user.id}"></td>
            <td>${s.count}</td>
            <td>${user.userName}</td>
            <td>${user.sex}</td>
            <td>${user.age}</td>
            <td>${user.address}</td>
            <td>${user.qq}</td>
            <td>${user.email}</td>
            <td><a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/UserFindServlet?userId=${user.id}">修改</a>&nbsp;
                <a class="btn btn-default btn-sm" href="${pageContext.request.contextPath}/UserDeleteServlet?userId=${user.id}">删除</a>
            </td>
        </tr>
        </c:forEach>
        </form>
    </table>
    <div>
        <nav aria-label="Page navigation">
            <ul class="pagination">
                <c:if test="${pageBean.currentPage == 1}">
                    <li class="disabled">
                </c:if>

                <c:if test="${pageBean.currentPage != 1}">
                     <li>
                </c:if>

                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pageBean.currentPage - 1}&rows=5" aria-label="Previous">
                        <span aria-hidden="true">&laquo;</span>
                    </a>
                </li>


                <c:forEach begin="1" end="${pageBean.totalPage}" var="i">

                    <c:if test="${pageBean.currentPage == i}">
                        <li class="active"><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5">${i}</a></li>
                    </c:if>

                    <c:if test="${pageBean.currentPage != i}">
                        <li><a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${i}&rows=5">${i}</a></li>
                    </c:if>

                </c:forEach>


                <c:if test="${pageBean.currentPage == pageBean.totalPage}">
                    <li class="disabled">
                </c:if>

                <c:if test="${pageBean.currentPage != pageBean.totalPage}">
                    <li>
                </c:if>

                    <a href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=${pageBean.currentPage + 1}&rows=5" aria-label="Next">
                        <span aria-hidden="true">&raquo;</span>
                    </a>
                </li>
                <span style="font-size: 20px ;margin-left:5px;">
                    共${pageBean.totalCount}条记录，共${pageBean.totalPage}页
                </span>
            </ul>
        </nav>
    </div>
</div>



</body>
</html>
