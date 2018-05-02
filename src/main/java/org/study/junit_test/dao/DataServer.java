package org.study.junit_test.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DataServer {
	private static final String DB_URL = "jdbc:mysql://192.168.0.211/todo_db"
			+ "?useSSL=no&characterEncoding=utf8";
	private static final String USER_NAME = "hyeri";
	private static final String PASSWORD = "bha11530";
	
	private Connection conn;
	
	public DataServer() {
		makeConnect();
	}
	
	public Connection getConn() {
		return conn;
	}

	public void makeConnect(){
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = DriverManager.getConnection(DB_URL, USER_NAME, PASSWORD);
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public boolean login(String userId, String password) {
		try {
			Statement state = conn.createStatement();
			
			String sql = "select userId, password from todo_user";
			
			ResultSet rs = state.executeQuery(sql);
			
			while(rs.next()) {
				if(rs.getString("userId").equals(userId) 
						&& rs.getString("password").equals(password)) {
					System.out.println(userId + " Login");
					return true;
				}
				
			}
			state.close();
			rs.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean updateTodo(int no, String userId, String todo) {
		PreparedStatement state = null;
		ResultSet set = null;
		PreparedStatement prepState = null;
		try {
			
			String sql = "select userId from todo_user where userId = ?";
			state = conn.prepareStatement(sql);
			state.setString(1, userId);
			
//			state.executeUpdate();
			
			set = state.executeQuery();
			if(!(set.next())) {
				return false;
			}

			sql = "insert into todo_list_test values(?, ?, ?, ?, ?)";
			prepState = conn.prepareStatement(sql);
			prepState.setInt(1, no);
			prepState.setString(2, todo);
			prepState.setDate(3,  new Date(System.currentTimeMillis()));
			prepState.setDate(4,  new Date(118, 04, 03));
			prepState.setString(5, userId);
			
			prepState.executeUpdate();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				prepState.close();
				set.close();
				state.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return true;
	}
	
	public String readTodo(String userId) {
		String todo = "";
		try {
			String sql = "select * from todo_list_test where userId = ?";
			PreparedStatement prepState = conn.prepareStatement(sql);
			
			prepState.setString(1, userId);
			
			ResultSet rs = prepState.executeQuery();
			
			if(rs.next()) {
				todo = rs.getString("todo");
			}
			
			rs.close();
			prepState.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return todo;
	}
}
