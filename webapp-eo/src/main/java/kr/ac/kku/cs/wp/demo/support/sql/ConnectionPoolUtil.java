package kr.ac.kku.cs.wp.demo.support.sql;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class ConnectionPoolUtil {

    private static HikariDataSource dataSource;
    private static final Logger logger = LogManager.getLogger(ConnectionPoolUtil.class);

    // static 영역에서 Datasource 초기화
    static {
        try {
            logger.info("Loading MySQL JDBC Driver");
            Class.forName("com.mysql.cj.jdbc.Driver"); // MySQL JDBC 드라이버 로드
            logger.info("MySQL JDBC Driver loaded successfully.");

            HikariConfig config = new HikariConfig();
            config.setJdbcUrl("jdbc:mysql://localhost:3306/cswp_202020950?serverTimezone=UTC"); // MySQL 연결 정보
            config.setUsername("fishandrew");  // MySQL 사용자 이름
            config.setPassword("202020950");   // MySQL 비밀번호

            // Connection Pool 설정
            config.setAutoCommit(false);       // Auto-commit 비활성화
            config.setMinimumIdle(3);          // 최소 유휴 커넥션 3개
            config.setMaximumPoolSize(10);     // 최대 커넥션 수 10개
            config.setConnectionTimeout(30000); // 커넥션 타임아웃 30초
            config.setIdleTimeout(600000);     // 유휴 상태 타임아웃 10분
            config.setMaxLifetime(1800000);    // 커넥션 최대 수명 30분

            // HikariDataSource 초기화
            dataSource = new HikariDataSource(config);
            logger.info("HikariCP Connection Pool initialized successfully.");
        } catch (ClassNotFoundException e) {
            logger.error("MySQL JDBC Driver not found", e);
            throw new RuntimeException("MySQL JDBC Driver not found", e);
        } catch (Exception e) {
            logger.error("Error initializing HikariCP Connection Pool", e);
            throw new RuntimeException("Failed to initialize HikariCP connection pool", e);
        }
    }

    // Connection 객체 반환
    public static Connection getConnection() throws SQLException {
        if (dataSource == null) {
            throw new SQLException("DataSource is not initialized.");
        }
        return dataSource.getConnection();
    }

    // 커넥션 풀 상태 출력
    public static void printConnectionPoolStatus() {
        if (dataSource != null) {
            logger.debug("Total connections: " + dataSource.getHikariPoolMXBean().getTotalConnections());
            logger.debug("Active connections: " + dataSource.getHikariPoolMXBean().getActiveConnections());
            logger.debug("Idle connections: " + dataSource.getHikariPoolMXBean().getIdleConnections());
        } else {
            logger.warn("DataSource is not initialized, cannot print pool status.");
        }
    }

    // DataSource 종료
    public static void closeDataSource() {
        if (dataSource != null && !dataSource.isClosed()) {
            dataSource.close();
            logger.info("HikariCP DataSource closed successfully.");
        } else {
            logger.warn("DataSource is already closed or not initialized.");
        }
    }
}
