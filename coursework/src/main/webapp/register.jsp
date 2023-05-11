<%@page import="java.io.PrintWriter"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.sql.Date"%>
<%@page import="com.informatics.password.encryptDecrypt"%>
<%@page import="com.informatics.databaseconnection.databaseconnection"%>
<%@page import="java.sql.SQLException"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
    %>

 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>register</title>
</head>
<body>
	
	<%
	
		try{
	
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			databaseconnection db= new databaseconnection();
			Connection con=db.getConnect();
			
			
			String firstname= request.getParameter("first_name");
			String lastname= request.getParameter("last_name");
			String address = request.getParameter("address");
			String contact = request.getParameter("contact");
			String email = request.getParameter("email");
			String password= request.getParameter("password");
			
			
			String pass = encryptDecrypt.encrypt(password);
			
			String query="INSERT INTO `customer`( `firstname`, `lastname`, `address`, `contact`, `email`,`password`,`is_admin`) VALUES (?,?,?,?,?,?,?)";
			
			PreparedStatement ps= con.prepareStatement(query);
			ps.setString(1,firstname);
			ps.setString(2,lastname);
			ps.setString(3,address);
			ps.setString(4,contact);
			ps.setString(5,email);
			ps.setString(6,pass);
			ps.setInt(7,0);
			
			
			
			int row = ps.executeUpdate();
			
			if (row > 0 ){
				System.out.println("data saved");
				response.sendRedirect("login.html");
			}else{
				System.out.println("not saved");
			}
			
			
		}catch(SQLException e){
			e.printStackTrace();
		}catch(ClassNotFoundException es){
			es.printStackTrace();
		}
	%>
</body>
</html>