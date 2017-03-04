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
<title>Remove Task</title>
</head>
<body>
	<div class="wrapper">
			<h1 class="text-center">Remove Task</h1>
			<br>
			<a href="itemlist" class="backButton">Back</a>
			<br><hr>
			<form action="removeitem" method="post">
				<label>Task Number <input type="text" name="number"></label>
				<input type="submit" name="remove" value="Remove Task"/>
			</form>
			<%
				String result = (String) request.getAttribute("result");
				if(result != null)
				{
					out.println("<h5 style='color:#ff0000'>" + result + "</h5>");	
				}
			%>
		</div>
		<div id="footer"><div class="text-center">  Developed by Tavor Cohen and Inbar Takdim © </div></div>
</body>
</html>