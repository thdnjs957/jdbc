package test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionTest {

	public static void main(String[] args) {

		Connection conn = null;
		try {
			// 1. JDBC Driver(MariaDB) 로딩 (직접 코드로)
			Class.forName("org.mariadb.jdbc.Driver");

			// 2. 연결하기 url 필요함
			String url = "jdbc:mariadb://192.168.1.2:3307/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");

		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패" + e);
		} catch (SQLException e) {
			System.out.println("error" + e);
		} finally {
			try {
				if(conn != null) {
					conn.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
