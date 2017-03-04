<%@ page language="java" 
	import="java.util.* , com.tavor.samples.model.*"
	contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../includes/style.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
 		<title>Add Task</title>
	</head>
	<body>
		<div class="wrapper">
			<h1 class="text-center">Add Task</h1>
			<br>
			<a href="itemlist" class="backButton">Back</a>
			<br><hr><br>
			<form action="additem" method="POST">
				<label>Description  <input type="text" name="description"/></label>
				<input type="submit" name="additem" value="Add Task"/>
			</form>
			<%
				String result = (String) request.getAttribute("result");
				if(result != null)
				{
					out.println("<h5 style='color:#ff0000'>" + result + "</h5>");	
				}
			%>
		</div>
		<div id="footer"><div class="text-center">  Developed by Tavor Cohen and Inbar Takdim © </div>
		</div>
	</body>
</html>