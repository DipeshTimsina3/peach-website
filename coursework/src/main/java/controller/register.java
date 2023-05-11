package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.informatics.databaseconnection.databaseconnection;
import com.informatics.password.encryptDecrypt;

@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 1000, // 1 GB
maxRequestSize = 1024 * 1024 * 1000)   	// 1 GB

public class register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

			
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

					Part filePart = request.getPart("image");
					String fileName = filePart.getSubmittedFileName();		
			        InputStream is = filePart.getInputStream();
			        Files.copy(is, Paths.get("C:/Users/dipes/eclipse-web/coursework/src/main/webapp"+fileName), StandardCopyOption.REPLACE_EXISTING);
					
					String query="INSERT INTO `customer`( `firstname`, `lastname`, `address`, `contact`, `email`,`photo`,`password`,`is_admin`) VALUES (?,?,?,?,?,?,?,?)";
					
					PreparedStatement ps= con.prepareStatement(query);
					ps.setString(1,firstname);
					ps.setString(2,lastname);
					ps.setString(3,address);
					ps.setString(4,contact);
					ps.setString(5,email);
					ps.setString(6, fileName);
					ps.setString(7,pass);
					ps.setInt(8,0);
					
					
					
					
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
	}

}
