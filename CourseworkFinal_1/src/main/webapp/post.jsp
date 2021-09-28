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
	#post_, th , td {
  	border:1px solid black;
	border-collapse:collapse; 
	margin-left:auto;
	margin-right:auto; 

	
}

#post_ th{
background-color: #04AA6D
}







table{
	width:60%
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
	<table id="post_">
		<tr>
			<th>Date</th>
			<th>Post</th>
		</tr>
		<tbody>
			<c:forEach var="post" items="${listPost}" >
				<script>
					console.log("${post.post_name}");
				</script>
			<tr>
			<td><c:out value="${post.post_timestamp}"/></td>
			<td><c:out value="${post.post_name}"/></td>
			</tr>
			</c:forEach>
		</tbody>
	</table>
	<form action="InsertPostServlet"method='post' >
	<br>Post Name: <br><textarea style= 'width: 500px; height: 20px;' name='post-name' maxlength='40'></textarea>
	<br>Post Message<br><textarea style= 'width: 500px; height: 200px;' name='post-message'></textarea><br>
	<br><br><input type="submit" value="Submit Reply" />
	
</form>


 
 
 </body>
</html>