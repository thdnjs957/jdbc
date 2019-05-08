package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class UpdateTest {

	public static void main(String[] args) {
		boolean result = update(1L, "경영지원팀");
		if(result) {
			System.out.println("업데이트 성공!");
		}
	}
	
	public static boolean update(Long no, String name) {
		boolean result = false;
		Connection conn = null;
		Statement stmt = null;
		
		try {
			// 1. JDBC Driver(MariaDB) 클래스 로딩 (직접 코드로)
			Class.forName("org.mariadb.jdbc.Driver");
			
			// 2. 연결하기 url 필요함
			String url = "jdbc:mariadb://192.168.1.2:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
			
			//3. statement 객체 생성 - 쿼리 날리는
			stmt = conn.createStatement();
			
			//4. SQL문 실행
			String sql = "update department"+
						  " set name='"+name + "'"+ " where no="+no;
			int count = stmt.executeUpdate(sql);
			
			result = count == 1;
			
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if(stmt != null) {
					stmt.close();
				}
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
}
