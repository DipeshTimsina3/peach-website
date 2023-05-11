<%@page import="com.informatics.password.encryptDecrypt"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@page import="com.informatics.databaseconnection.databaseconnection"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

	<%
		Class.forName("com.mysql.cj.jdbc.Driver");
		databaseconnection db= new databaseconnection();
		Connection con=db.getConnect();
		
		
		String email = request.getParameter("email");
		String password= request.getParameter("password");
		
		
		
		String query = "Select * from customer where email = ?";
		PreparedStatement ps = con.prepareStatement(query);
		ps.setString(1, email);
		ResultSet rs = ps.executeQuery();
		
		
		
		if(rs.next()){
			String savedpass = rs.getString("password");
			String decryptpass = encryptDecrypt.decrypt(savedpass);
			Integer customer_id = rs.getInt("customer_id");
			
			if (decryptpass.equals(password)){
				System.out.println("Logged in successfully");
				response.sendRedirect("profile.jsp?customer_id=" + customer_id);
				
				
			}else{
				System.out.println("Failed to login");
				response.sendRedirect("login.html");
			}
			
		}else{
			response.sendRedirect("login.html");
		}
			
		

		
		
		
	%>
</body>
</html>