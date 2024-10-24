package kr.ac.kku.cs.wp.demo.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolUtil {
	private static HikariDataSource dataSource;
//static 영역에서 Datasource 초기화
	static {
		try {
			HikariConfig config = new HikariConfig();
			config.setJdbcUrl("jdbc:mysql://localhost:3306/cswp_202020950?serverTimezone=UTC");
			config.setUsername("fishandrew");
			config.setPassword("202020950");
			config.setAutoCommit(false); // autocommit false config.setMinimumIdle(3); // 최소 3개의 유휴 커넥션 유지
			config.setMinimumIdle(3);
			config.setMaximumPoolSize(10);// config.setMaximumPoolSize(10); //
			config.setConnectionTimeout(30000); //
			config.setIdleTimeout(600000); // 10 $ 744 82
			config.setMaxLifetime(1800000); // 30
			

			dataSource = new HikariDataSource(config);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static final Logger logger = LogManager.getLogger(ConnectionPoolUtil.class);
		//...
	public static Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
	
	public static void printConnectionPoolStatus () {
		logger.debug("Total connections: " + dataSource.getHikariPoolMXBean().getTotalConnections());

		logger.debug("Active connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());

		logger.debug("Idle connections: " + dataSource.getHikariPoolMXBean().getIdleConnections());
	}
	
	public static void closeDataSource() {
		if (dataSource != null && !dataSource.isClosed()) {
			dataSource.close();
		}
	}
	
	
}