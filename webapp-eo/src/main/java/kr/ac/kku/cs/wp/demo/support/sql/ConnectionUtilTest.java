package kr.ac.kku.cs.wp.demo.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.Test;

public class ConnectionUtilTest{
	@Test
	void testConnection() throws SQLException{
		try (Connection conn = ConnectionUtil.getConnection()){
			conn.commit();
		}catch(Exception e) {
			throw e;
		}
	}
}
