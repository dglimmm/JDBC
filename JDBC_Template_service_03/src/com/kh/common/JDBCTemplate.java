package com.kh.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

//공통 탬플릿(매번 반복적으로 작성될 코드를 메소드로 정의해둠)
public class JDBCTemplate {
	// 모든 메소드 싹 다 static 메소드
	// 싱글톤 패턴 : 메모리영역에 단 한번만 매번 재사용하는 개념
	
	//1. Connection 객체 생성(db접속)한 후 해당 Connection 반환해주는 메소드
	public static Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
		
	}
	
	//2. commit 처리해주는 메소드(Connection 을 전달받아서 처리)
	public static void commit(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed()) 
				conn.commit();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	//3. rollback 처리해주는 메소드(Connection 을 전달받아서 처리)
	public static void rollback(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed()) 
				conn.rollback();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//JDBC용 객체들 전달받아서 반납처리해주는 메소드
	//4. Statement관련 객체 전달받아서 반납시켜주는 메소드
	public static void close(Statement stmt) {
		try {
			if(stmt !=null && !stmt.isClosed()) 
			stmt.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//5. Connection 객체 전달받아서 반납시켜주는 메소드
	public static void close(Connection conn) {
		try {
			if(conn !=null && !conn.isClosed()) 
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//6. ResultSet 객체 전달받아서 반납시켜주는 메소드
	public static void close(ResultSet rset) {
		try {
			if(rset !=null && !rset.isClosed()) 
			rset.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
