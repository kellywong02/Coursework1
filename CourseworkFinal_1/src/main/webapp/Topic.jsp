<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Threads</title>

<link rel="stylesheet" href="<%=request.getContextPath()%>/resources/css/headerCSS.css" />
<style>
	#thread_, th , td {
  	border:1px solid black;
	border-collapse:collapse; 
	margin-left:auto;
	margin-right:auto; 

	
}

#thread_ th{
background-color: #04AA6D
}





table{
		width: 60%;	
}

</style>

</head>
<body>
<div class="pointer navbar">
<ul>
<li><a href="index.php" title="about">Login</a></li>
	<li><a  href = "index.php" title="Home">Home</a></li>
        <li><a href="<%=request.getContextPath()%>/InsertThreadServlet">Threads</a></li>
        <li><a  href = "Topic.jsp" >Topic</a></li>
        
</ul>
</div>
	<br>
	<table id="thread_">
		<tr>
			<th>Created By:</th>
			<th>Threads</th>
			<th>Number of Topics</th>
			<th>Created Date:</th>
			<th>Delete</th>
		</tr>
		<tbody>
			<c:forEach var="thread" items="${listThread}" >
				<script>
					console.log("${thread.thread_name}");
					console.log("${thread.thread_id}");
				</script>
			<tr>
			<td><c:out value="${thread.thread_username}"/></td>
			<td><a href="<%=request.getContextPath()%>/InsertPostServlet"><c:out value="${thread.thread_name}"/></a></td>
			<td>3</td>
			<td><c:out value="${thread.thread_timestamp}"/></td>
			</tr>
			</c:forEach>
		
		</tbody>
		
	</table>
	<form action="InsertThreadServlet"method='post' >

	<br>Thread Header: <br><textarea style= 'width: 500px; height: 20px;' name='thread-name' maxlength='40'></textarea>
	<br>Thread Message:<br> <textarea style= 'width: 500px; height: 200px;' name='thread-message'></textarea>
	<br><br><input type="submit" value="Submit Thread" />
	
</form>


 
 
 </body>
</html>