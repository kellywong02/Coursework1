package com.SDDEVOPS;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;

/**
 * Servlet implementation class InsertPostServlet
 */
@WebServlet("/InsertPostServlet")
public class InsertPostServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/coursework_final";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	int thread_id = 0;
	int user_id = 0;
	int topic_id = 0;
	int post_id = 0;
	private static final String SELECT_POST = "SELECT thread.topic_id, post.post_id, post.post_by_user,post.post_thread_id FROM post INNER JOIN user ON post.post_by_user = user.user_id INNER JOIN thread ON thread.thread_id = post.post_thread_id;";
	private static final String SELECT_ALL_POST = "SELECT * FROM post;";
	
	
	protected Connection getConnection() {
		Connection connection = null;
		try {
		Class.forName("com.mysql.jdbc.Driver");
		connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
		} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		}
		return connection;
		}
	
	/**
     * @see HttpServlet#HttpServlet()
     */
    public InsertPostServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    
    private void printSQLException(SQLException ex) {
		for (Throwable e: ex) {
			if (e instanceof SQLException) {
				e.printStackTrace(System.err);
				System.err.println("SQLState: " + ((SQLException) e).getSQLState());
				System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
				System.err.println("Message: " + e.getMessage());
				Throwable t = ex.getCause();
				while (t != null) {
					System.out.println("Cause: " + t);
					t = t.getCause();
				}
			}
		}
	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println("action is " + action);
		try {
			switch (action) {
			case "/InsertThreadServlet/showPost":
				break;
			default:
				listPost(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	

	
	private void listPost(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		// TODO Auto-generated method stub
		List<post> posts = new ArrayList<>();
		try(Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_POST);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				int post_id = rs.getInt("post_id");
				String post_name = rs.getString("post_name");
				String message = rs.getString("message");
				String post_timestamp = String.valueOf(rs.getDate("post_timestamp"));
				posts.add(new post(post_id,post_name,message,post_timestamp));
				System.out.println(post_name);
				System.out.println(message);
				System.out.println(post_timestamp);
			}
		}catch(SQLException e) {
			printSQLException(e);
		}
		System.out.println("Total post is:" + posts.size());
		request.setAttribute("listPost", posts);
		RequestDispatcher dispatcher = request.getRequestDispatcher("post.jsp");
		dispatcher.forward(request, response);
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try(
				Connection connection = getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_POST);){
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					thread_id = rs.getInt("post.post_thread_id");
					user_id = rs.getInt("post.post_by_user");
					topic_id = rs.getInt("thread.topic_id");
					post_id = rs.getInt("post.post_id");
				}
		}catch(SQLException e) {
			printSQLException(e);

		
	}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String post_name = request.getParameter("post-name");
		String post_message = request.getParameter("post-message");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String postTimestamp = String.valueOf(timestamp);
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework_final","root","");
			PreparedStatement ps = con.prepareStatement("insert into post values(?,?,?,?,?,?)");
			ps.setInt(1, post_id + 1);
			ps.setString(2,post_name);
			ps.setString(3, post_message);
			ps.setString(4, postTimestamp);
			ps.setInt(5, user_id);
			ps.setInt(6, thread_id);
			
			int i = ps.executeUpdate();
			if (i>0)
				out.print("Post added successfully!");
		}catch(Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}
	

}
