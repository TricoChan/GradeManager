<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" import="java.util.*"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<!-- Bootstrap -->
<link rel="stylesheet" type="text/css" href="css/bootstrap.css">
<link rel="stylesheet" type="text/css"
	href="fonts/font-awesome/css/font-awesome.css">

<!-- Stylesheet
    ================================================== -->
<link rel="stylesheet" type="text/css" href="css/style.css">
<link rel="stylesheet" type="text/css" href="css/responsive.css">
<link
	href='http://fonts.googleapis.com/css?family=Raleway:500,600,700,100,800,900,400,200,300'
	rel='stylesheet' type='text/css'>
<link href='http://fonts.googleapis.com/css?family=Playball'
	rel='stylesheet' type='text/css'>

<body>
<section class="first-section">
          <div class="container">
            <div class="row">
              <div class="col-md-12">
                <div class="text-content">
                  <h2>留言板</h2>
                  <div class="line-dec"></div>
                  <a href="index.html">返回首页</a>
                </div>
              </div>
            </div>
          </div>
        </section>
<center>
	<h3>所有留言</h3>
		<table class="table table-striped">
			<tr>
				<td>类型</td>
				<td>内容</td>
				<td>日期</td>
			</tr>
			<c:forEach items="${noteAllList}" var="noteall">
				<tr>
					<td>${noteall.type }</td>
					<td>${noteall.content }</td>
					<td>${noteall.date }</td>
				</tr>
			</c:forEach>
		</table>
		</center>

</body>
</html>