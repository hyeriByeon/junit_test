package org.study.junit_test;

import org.junit.Assert;
import org.junit.Test;
import org.study.junit_test.dao.DataServer;
import org.study.junit_test.service.TodoService;

public class TodoTest {
	
	@Test
	public void testMakeConnect() {
		DataServer ds = new DataServer();
		
		Assert.assertNotNull(ds.getConn());
	}
	
	@Test
	public void testLogin() {
		String userId = "admin";
		String password = "admin11";
		
		TodoService todoServ = new TodoService();
		Assert.assertTrue(todoServ.login(userId, password));
		Assert.assertFalse(todoServ.login("babo", "aa"));
	}
	
	@Test
	public void testTodo() {
		TodoService todoServ = new TodoService();
//		todoServ.addTodo(2, "hyeri", "blah");
		
		Assert.assertEquals("blah", todoServ.retrieveTodo("hyeri"));
	}
	
}
