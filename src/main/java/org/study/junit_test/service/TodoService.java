package org.study.junit_test.service;

import org.study.junit_test.dao.DataServer;

public class TodoService {
	private DataServer ds;
	
	public TodoService(){
		ds = new DataServer();// 생성됨.
	}
	
	public boolean login(String userId, String password) {
		// id, password 체크해서 로그인.
		return ds.login(userId, password);
	}
	
	public boolean addTodo(int no, String userId, String todo) {
		return ds.updateTodo(no, userId, todo);
	}
	
	public String retrieveTodo(String userId) {
		//유저아이디의 일정을.
		return ds.readTodo(userId);
	}
}
