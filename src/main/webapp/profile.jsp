<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.informatics.databaseconnection.databaseconnection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<title>User Profile</title>
</head>
<body>
		<% 
		
		
		int customer_id = Integer.parseInt(request.getParameter("customer_id"));
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		databaseconnection db= new databaseconnection();
		Connection con=db.getConnect();
		
		
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
		
		
		String query = "Select * from customer where c_id = ?;";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setInt(1,customer_id);
		
		ResultSet rs = ps.executeQuery();
		
		if(rs.next()){ %>
			
			<div class="profile">
			
			<img src="profile-photo.jpg" alt="Profile Photo">
			<h2>User Profile</h2>
			<table>
				<tr>
					<td>First Name:</td>
					<td><%=rs.getString("firstname") %></td>
				</tr>
				<tr>
					<td>Last Name:</td>
					<td><%=rs.getString("lastname") %></td>
				</tr>
				<tr>
					<td>address :</td>
					<td><%=rs.getString("address") %></td>
				</tr>
				<tr>
					<td>contact :</td>
					<td><%=rs.getString("contact") %></td>
				</tr>
				<tr>
					<td>Email :</td>
					<td><%=rs.getString("email") %></td>
				</tr>
			</table>
			<div class="edit">
				<a href="edit-profile.jsp">Edit Profile</a>
			</div>
		</div>
			
			
			
		<%}
		
		%>
		
		
			
</body>
</html>
