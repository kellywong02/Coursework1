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
 * Servlet implementation class InsertThreadServlet
 */
@WebServlet("/InsertThreadServlet")
public class InsertThreadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String jdbcURL = "jdbc:mysql://localhost:3306/coursework_final";
	private String jdbcUsername = "root";
	private String jdbcPassword = "";
	int thread_id = 0;
	int user_id = 0;
	int topic_id = 0;
	int post_id = 0;
	
	private static final String INSERT_THREAD_SQL = "INSERT INTO thread" + "(thread_id, thread_name, thread_message, thread_timestamp,thread_by_user,topic_id) VALUES"
	+ " (?, ?, ?, ?, ?, ?);";
	
	  
	private static final String SELECT_ALL_THREADS = "SELECT * FROM thread;";
	private static final String SELECT_THREADS_USERNAME = "SELECT user.username,thread.thread_id,thread.thread_name, thread.thread_message, thread.thread_timestamp FROM thread INNER JOIN user ON thread.thread_by_user = user.user_id ;";
	private static final String DELETE_THREAD_SQL = "DELETE FROM thread WHERE thread_id = ?;";
    private static final String SELECT_THREAD_TOPIC = "SELECT thread.thread_id, thread.thread_by_user, thread.topic_id FROM thread INNER JOIN user ON thread.thread_by_user = user.user_id;";
    private static final String SELECT_THREAD_POST = "SELECT user.username, thread.thread_id,thread.thread_timestamp, post.post_id,post.post_name,post.message,post.post_timestamp,post.post_by_user, post.post_thread_id FROM post INNER JOIN thread ON post.post_thread_id = thread.thread_id INNER JOIN user ON post.post_by_user = user.user_id WHERE post.post_thread_id = thread.thread_id;";
    
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
    public InsertThreadServlet() {
        super();
        // TODO Auto-generated constructor stub
        
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		String action = request.getServletPath();
		System.out.println("action is " + action);
		try {
			switch (action) {
			case "/InsertThreadServlet/showPost":
				showPost(request,response);
				break;
			default:
				listThread(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}

	private void showPost(HttpServletRequest request, HttpServletResponse response) 
		// TODO Auto-generated method stub
		throws ServletException, IOException {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/InsertPostServlet");
			dispatcher.forward(request, response);
			
			
		}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THREAD_TOPIC);){
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				thread_id = rs.getInt("thread.thread_id");
				user_id = rs.getInt("thread.thread_by_user");
				topic_id = rs.getInt("thread.topic_id");
			}
		}catch(SQLException e) {
			printSQLException(e);
		
		}
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String threadName = request.getParameter("thread-name");
		String threadMessage = request.getParameter("thread-message");
		Timestamp timestamp = new Timestamp(System.currentTimeMillis());
		String threadTimestamp = String.valueOf(timestamp);
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/coursework_final","root","");
			PreparedStatement ps = con.prepareStatement("insert into thread values(?,?,?,?,?,?)");
			ps.setInt(1, thread_id + 1);
			ps.setString(2,threadName);
			ps.setString(3, threadMessage);
			ps.setString(4, threadTimestamp);
			ps.setInt(5, user_id);
			ps.setInt(6, topic_id);
			
			int i = ps.executeUpdate();
			if (i>0)
				out.print("Thread added successfully!");
		}catch(Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}
		
	
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
	
	private void listThread(HttpServletRequest request, HttpServletResponse response)
	throws SQLException, IOException, ServletException{
		List<thread> threads = new ArrayList<>();
		try (
			Connection connection = getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_THREADS_USERNAME);) {
				System.out.println(preparedStatement);
				ResultSet rs = preparedStatement.executeQuery();
				while (rs.next()) {
					String thread_username = rs.getString("user.username");
					int thread_id = rs.getInt("thread_id");
					String thread_name = rs.getString("thread_name");
					String thread_message = rs.getString("thread_message");
					String thread_timestamp = String.valueOf(rs.getTimestamp("thread_timestamp", null));
					threads.add(new thread(thread_id,thread_name,thread_message,thread_timestamp,thread_username));
					System.out.println(thread_name);
					System.out.println(thread_username);
				}
			}catch(SQLException e) {
				printSQLException(e);
			}
		System.out.println("total thread is:" + threads.size());
		request.setAttribute("listThread", threads);
		RequestDispatcher dispatcher = request.getRequestDispatcher("thread.jsp");
		dispatcher.forward(request, response);
		}
	}


