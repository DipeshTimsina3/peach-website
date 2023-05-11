package controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.informatics.databaseconnection.databaseconnection;

/**
 * Servlet implementation class EditProductDAO
 */
public class EditProductDAO extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private databaseconnection dc;
	private Connection conn;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public EditProductDAO() {
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
		doGet(request, response);
		String p_name = request.getParameter("p_name");
		String p_id = request.getParameter("p_id");
		String p_price = request.getParameter("p_price");
		String p_desc = request.getParameter("p_desc");
		String stock = request.getParameter("stock");
		String pc_id = request.getParameter("pc_iD");
		Part filePart = request.getPart("photo");
		String fileName = filePart.getSubmittedFileName();
		InputStream fileContent = filePart.getInputStream();

		// Save the file to disk
		File file = new File("C:/Users/dipes/eclipse-web/coursework/src/main/webapp" + fileName);
		OutputStream out = new FileOutputStream(file);
		int read = 0;
		byte[] bytes = new byte[1024];
		while ((read = fileContent.read(bytes)) != -1) {
			out.write(bytes, 0, read);
		}
		out.close();
		fileContent.close();
		String query = "update coursework.products set p_name = ?, p_price =?, p_desc =?, stock =?, photo =?, pc_id =? where p_id = ?";
		try {
			this.dc = new databaseconnection();
			this.conn = this.dc.getConnect();
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, p_name);
			ps.setString(2, p_price);
			ps.setString(3, p_desc);
			ps.setString(4, stock);
			ps.setString(5, "C:/Users/dipes/eclipse-web/coursework/src/main/webapp" + fileName);
			ps.setString(6, pc_id);
			ps.setString(7, p_id);
			ps.executeQuery();
		} catch (ClassNotFoundException | SQLException e) {
			throw new RuntimeException(e);
		}
	}

}