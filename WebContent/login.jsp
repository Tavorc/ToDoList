<?xml version="1.0" encoding="windows-1255" ?>
<%@ page language="java" contentType="text/html; charset=windows-1255"
    pageEncoding="windows-1255"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=windows-1255" />
	<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
	<link rel="stylesheet" type="text/css" href="../includes/style.css">
 	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
<title>Login</title>
</head>
<body>
<div class= "wrapper">
<div class="text-center">
<h1>Welcome To ToDo List Application</h1>
</div>
		<div class="wrapperRegister" >
		<h2>Login</h2>
		<br>
		<form class="form-inline" action="login" method="post">
		  <div class="form-group">
		    <label for="exampleInputName2">Email</label>
		    <input type="email" name="mail" class="form-control" id="exampleInputName2" placeholder="Email">
		  </div>
		  <div class="form-group">
		    <label for="exampleInputEmail2">Password</label>
		    <input type="password" name="password" class="form-control" id="exampleInputEmail2" placeholder="password">
		  </div>
		  <button type="submit" name="login" class="btn btn-default">LOGIN</button>
		</form>
		<br><br>
		<ul class="nav nav-pills controllPills">  
 			  <li><a href="signup" class="signup-label">SIGNUP</a></li>
			</ul>
			<%
				String result = (String) request.getAttribute("result");
				if(result != null)
				{
				out.println("<h5 style='color:#ff0000'>" + result + "</h5>");	
				}
			%>
	</div>
		
		</div>
</body>
</html>