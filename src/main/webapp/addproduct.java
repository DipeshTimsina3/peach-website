package controller;


import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Optional;

import com.informatics.databaseconnection.databaseconnection;

import static java.lang.String.valueOf;

/**
 * Servlet implementation class AddProductDAO
 */
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10 MB
maxFileSize = 1024 * 1024 * 1000, // 1 GB
maxRequestSize = 1024 * 1024 * 1000)   	// 1 GB
@WebServlet("/addproduct")
public class addproduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private databaseconnection dc;
	private Connection conn;

	/**
     * @see HttpServlet#HttpServlet()
     */
    public addproduct() {
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
		response.setContentType("text/html");
		String p_name = request.getParameter("name");
		int p_id = Integer.parseInt(request.getParameter("p_id"));
		double p_price = Double.parseDouble(request.getParameter("p_price"));
		String p_desc = request.getParameter("p_desc");
		int stock = Integer.parseInt(request.getParameter("stock"));
		int pc_id = Integer.parseInt(request.getParameter("pc_id"));
		Part filePart = request.getPart("image");
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
		String query = "insert into coursework.products (p_id, p_name, p_price, p_desc, stock, photo, pc_id) value (?,?,?,?,?,?,?)";
		try {
			this.dc = new databaseconnection();
			this.conn = this.dc.getConnect();
			Class.forName("com.mysql.cj.jdbc.Driver");
			PreparedStatement ps = conn.prepareStatement(query);
			ps.setString(1, Optional.ofNullable(p_id).<String>map(i -> String.valueOf(i)).orElse(null));
			ps.setString(2, p_name);
			ps.setString(3, valueOf(p_price));
			ps.setString(4, p_desc);
			ps.setString(5, valueOf(stock));
			ps.setString(6, "C:/Users/dipes/eclipse-web/coursework/src/main/webapp" + fileName);
			ps.setString(7, valueOf(pc_id));
			ps.executeQuery();
		} catch (SQLException | ClassNotFoundException e) {
			throw new RuntimeException(e);
		}
	}

}