package com.javaex.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.javaex.vo.MainVo;

@Repository
public class MainDao {

	// 0. import java.sql.*;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	private String driver = "oracle.jdbc.driver.OracleDriver";
	private String url = "jdbc:oracle:thin:@localhost:1521:xe";
	private String id = "webdb";
	private String pw = "webdb";

	private void getConnection() {
		try {
			// 1. JDBC 드라이버 (Oracle) 로딩
			Class.forName(driver);

			// 2. Connection 얻어오기
			conn = DriverManager.getConnection(url, id, pw);
			// System.out.println("접속성공");

		} catch (ClassNotFoundException e) {
			System.out.println("error: 드라이버 로딩 실패 - " + e);
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	private void close() {
		// 5. 자원정리
		try {
			if (rs != null) {
				rs.close();
			}
			if (pstmt != null) {
				pstmt.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			System.out.println("error:" + e);
		}
	}

	// Guestbook 출력
	public List<MainVo> getMainList() {
		List<MainVo> mainList = new ArrayList<MainVo>();
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			// SQL문 준비

			String query = "";
			query += " select no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') \"reg_date\" ";
			query += " from guestbook ";

			// 바인딩
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			System.out.println(query);

			// 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				// 전체출력
				mainList.add(new MainVo(no, name, password, content, reg_date));
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return mainList;
	}

	
	
	// Guestbook추가 => insert
	public int guestInsert(MainVo mainVo) { // delete, update도 이거 사용 가능 (대신 변수선언 변경)
		int count = -1;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			// SQL문 준비
			String query = "";
			query += " insert into guestbook ";
			query += " values(seq_guestbook_no.nextval, ?, ?, ?, sysdate) ";

			// 바인딩 쿼리로 만들기
			pstmt = conn.prepareStatement(query);

			pstmt.setString(1, mainVo.getName());
			pstmt.setString(2, mainVo.getPassword());
			pstmt.setString(3, mainVo.getContent());

			// 실행
			count = pstmt.executeUpdate();

			// 4.결과처리
			System.out.println("[" + count + " 건 추가되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return count;
	}

	// Guestbook삭제 => delete
	public int guestDelete(int no, String password) {
		int count = -1;

		getConnection();
		
		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			// SQL문 준비
			String query = "";
			query += " delete from guestbook ";
			query += " where no = ? ";
			query += " and password = ? ";

			// 바인딩 쿼리로 만들기
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setInt(1, no);
			pstmt.setString(2,password);

			// 실행
			count = pstmt.executeUpdate();

			// 결과처리
			System.out.println("[" + count + " 건 삭제되었습니다.]");

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		
		return count;
	}
	
	
	// Guestbook 1명 불러오기
	public MainVo getMainList(int nno) {
		MainVo mainVo = null;
		getConnection();

		try {

			// 3. SQL문 준비 / 바인딩 / 실행 --> 완성된 sql문을 가져와서 작성할것
			// SQL문 준비

			String query = "";
			query += " select no ";
			query += "         ,name ";
			query += "         ,password ";
			query += "         ,content ";
			query += "         ,to_char(reg_date, 'YYYY-MM-DD HH:MI:SS') \"reg_date\" ";
			query += " from guestbook ";
			query += " where no = ? ";

			// 바인딩
			pstmt = conn.prepareStatement(query); // 쿼리로 만들기
			pstmt.setInt(1, nno);
			System.out.println(query);

			// 실행
			rs = pstmt.executeQuery();

			while (rs.next()) {
				int no = rs.getInt("no");
				String name = rs.getString("name");
				String password = rs.getString("password");
				String content = rs.getString("content");
				String reg_date = rs.getString("reg_date");

				// 전체출력
				mainVo = new MainVo(no, name, password, content, reg_date);
			}

		} catch (SQLException e) {
			System.out.println("error: " + e);
		}

		close();
		return mainVo;
	}

}