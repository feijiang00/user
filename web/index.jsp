<%--
  Created by IntelliJ IDEA.
  User: 江野
  Date: 2021/4/3
  Time: 13:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
  <meta charset="utf-8"/>
  <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
  <meta name="viewport" content="width=device-width, initial-scale=1"/>
  <title>首页</title>

  <!-- 1. 导入CSS的全局样式 -->
  <link href="css/bootstrap.min.css" rel="stylesheet">
  <!-- 2. jQuery导入，建议使用1.9以上的版本 -->
  <script src="js/jquery-2.1.0.min.js"></script>
  <!-- 3. 导入bootstrap的js文件 -->
  <script src="js/bootstrap.min.js"></script>
  <script type="text/javascript">
  </script>

  <style>
    #svgBox{
      width:100%;
      margin:100px auto;
    }
    .text{
      font-size: 64px;
      font-weight: bold;
      text-transform: uppercase;
      fill: none;
      stroke-width: 2px;
      stroke-dasharray: 90 310;
      animation: stroke 6s infinite linear;
    }
    .text-1{
      stroke: #3498db;
      text-shadow: 0 0 5px #3498db;
      animation-delay: -1.5s;
    }
    .text-2{
      stroke: #f39c12;
      text-shadow: 0 0 5px #f39c12;
      animation-delay: -3s;
    }
    .text-3{
      stroke: #e74c3c;
      text-shadow: 0 0 5px #e74c3c;
      animation-delay: -4.5s;
    }
    .text-4{
      stroke: #9b59b6;
      text-shadow: 0 0 5px #9b59b6;
      animation-delay: -6s;
    }

    @keyframes stroke {
      100% {
        stroke-dashoffset: -400;
      }
    }
    .svgText{
      width:600px;
      margin:0 auto;
    }
    .svgText h3{
      font-size:18px;
      color:#333;
      line-height:2;
    }
    .svgText p{
      font-size:16px;
      color:#888;
      line-height:2;
    }
    .svgText p a,.svgText p a:hover{
      color:#01a6fc;
      font-weight:600;
    }
    .svgText ul li{
      font-size:16px;
      color:#888;
      line-height:2;
    }
  </style>

</head>
<body>

<div id="svgBox">
  <svg width="100%" height="100">
    <text text-anchor="middle" x="50%" y="50%" class="text text-1">
      欢迎进入学生信息管理系统
    </text>
    <text text-anchor="middle" x="50%" y="50%" class="text text-2">
      欢迎进入学生信息管理系统
    </text>
    <text text-anchor="middle" x="50%" y="50%" class="text text-3">
      欢迎进入学生信息管理系统
    </text>
    <text text-anchor="middle" x="50%" y="50%" class="text text-4">
      欢迎进入学生信息管理系统
    </text>
  </svg>
</div>

<div align="center">
  <a
          href="${pageContext.request.contextPath}/FindUserByPageServlet?currentPage=1&rows=5" style="text-decoration:none;font-size:33px">查询所有学生信息
  </a>
</div>
</body>
</html>
