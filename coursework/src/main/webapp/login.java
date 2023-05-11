package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import com.informatics.databaseconnection.databaseconnection;
import com.informatics.password.encryptDecrypt;

/**
 * Servlet implementation class login
 */
public class login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
				} catch (ClassNotFoundException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				databaseconnection db= new databaseconnection();
				Connection con=db.getConnect();
				
				
				String email = request.getParameter("email");
				String password= request.getParameter("password");
				
				
				
				String query = "Select * from customer where email = ?";
				PreparedStatement ps = null;
				try {
					ps = con.prepareStatement(query);
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				try {
					ps.setString(1, email);
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				ResultSet rs = null;
				try {
					rs = ps.executeQuery();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				
				
				try {
					if(rs.next()){
						String savedpass = rs.getString("password");
						String decryptpass = encryptDecrypt.decrypt(savedpass);
						Integer customer_id = rs.getInt("customer_id");
						
						if (decryptpass.equals(password)){
							System.out.println("Logged in successfully");
							HttpSession session = request.getSession();
							session.setAttribute("userID", rs.getString("userID"));
							session.setAttribute("isAdmin", rs.getString("isAdmin"));
							response.sendRedirect("home.html");
							
							
						}else{
							System.out.println("Failed to login");
							response.sendRedirect("login.html");
						}
						
					}else{
						response.sendRedirect("login.html");
					}
				} catch (SQLException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
		doGet(request, response);
	}

}
