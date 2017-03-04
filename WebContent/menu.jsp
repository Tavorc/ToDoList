<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    import="java.util.* , com.tavor.samples.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Menu</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../includes/style.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		</head>
	<body>
		<div class="wrapper">
			<%
				User user = (User)session.getAttribute("user");
				String mail = user.getMail();
				out.println("<h1 class='text-center'> Welcome  " + mail.substring(0,mail.indexOf("@")) +"!" + "</h1>");
				request.setAttribute("mail", mail);
			%>
			<br>
		<div class="text-center">
				<ul class="nav nav-tabs">
			  <li role="presentation" class="active">
			  	<a href="menu">Menu<span class="glyphicon glyphicon-home menuIcon"></a>
			  </li>
			  <li role="presentation">
			  	<a href="itemlist">Task list<span class="glyphicon glyphicon-tasks menuIcon"></span></a>
			  </li>
			  <li role="presentation">
			  	<a href="about">About<span class="glyphicon glyphicon-info-sign menuIcon"></span></a>
			  </li>
			  <li role="presentation">
			  	<a href="logout">Logout<span class="glyphicon glyphicon-off menuIcon"></span></a>
			  </li>
			</ul>
			<br>
		</div>
			<div class="text-center">
			<h4>Hi,here you can manage your tasks,you can add,update and remove your tasks </h4>
				<br>
				<img src="../images/taskpic.png">
			 </div>
		</div>
	<div id="footer"><div class="text-center">  Developed by Tavor Cohen and Inbar Takdim © </div></div>
	</body>
</html>