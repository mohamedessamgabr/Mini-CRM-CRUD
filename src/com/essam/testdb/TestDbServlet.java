package com.essam.testdb;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.*;
/**
 * Servlet implementation class TestDbServlet
 */
@WebServlet("/TestDbServlet")
public class TestDbServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		// setup connection variable
		String user = "springstudent";
		String password = "springstudent";
		String jdbcURL = "jdbc:mysql://localhost:3306/web_customer_tracker?useSSL=false";
		String driver = "com.mysql.cj.jdbc.Driver";
		
		// get a connection
		try {
			
			PrintWriter out = response.getWriter();
			out.println("Connecting to: " + jdbcURL);
			
			// Load database driver
			// The newInstance() call is a work around for some
            // broken Java implementations
			Class.forName(driver);
			
			Connection myConnection = DriverManager.getConnection(jdbcURL, user, password);
			out.println("Successful !!!!!");
			
			myConnection.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException(e);
		}
	}

}
