<%@ page language="java" 
		contentType="text/html; charset=UTF-8"
    	pageEncoding="UTF-8"
    	import="java.util.* , com.tavor.samples.model.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Task List</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
		<link rel="stylesheet" type="text/css" href="../includes/style.css">
 		<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
	</head>
	<body>
		<div class="wrapper">
			<h1 class="text-center"> Task List </h1>
			<br>
			<ul class="nav nav-tabs">
			  <li role="presentation">
			  	<a href="menu">Menu<span class="glyphicon glyphicon-home menuIcon"></a>
			  </li>
			  <li role="presentation" class="active">
			  	<a href="list">Task list<span class="glyphicon glyphicon-tasks menuIcon"></span></a>
			  </li>
			  <li role="presentation">
			  	<a href="about">About<span class="glyphicon glyphicon-info-sign menuIcon"></span></a>
			  </li>
			  <li role="presentation">
			  	<a href="logout">Logout<span class="glyphicon glyphicon-off menuIcon"></span></a>
			  </li>
			</ul>
			<br>
			<%
				HashMap<Integer, Integer> itemHash = new HashMap();
				List<Item> items = (List<Item>) request.getAttribute("items");
				out.println("<table class='table table-hover'>");
				out.println("<thead><tr><th>Task No.</th><th>description</th></tr></thead>");
				for(int i=0; i<items.size(); i++)
				{
					int line = i+1;
					itemHash.put(line, items.get(i).getId());
					out.println("<tr><td>" + line + "</td><td>"+
							items.get(i).getDescription() + "</td></tr>");
				}
				session.setAttribute("hash", itemHash);
				out.println("</table>"); 
			%>
			<%
				String result = (String) request.getAttribute("result");
				if(result != null) {
					out.println("<h5>" + result + "</h5>");	
				}
			%>
			<hr>
			<ul class="nav nav-pills controllPills">  
 			  <li><a href="additem">Add Task</a></li>
			  <li><a href="removeitem">Remove Task</a></li>
			  <li><a href="edititem">Edit Task</a></li>
			</ul>
		</div>
		<div id="footer"><div class="text-center">  Developed by Tavor Cohen and Inbar Takdim © </div></div>
	</body>
</html>