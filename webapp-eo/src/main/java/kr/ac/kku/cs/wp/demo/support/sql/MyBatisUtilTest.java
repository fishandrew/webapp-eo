package kr.ac.kku.cs.wp.demo.support.sql;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;

import kr.ac.kku.cs.wp.demo.user.entity.*;
import kr.ac.kku.cs.wp.demo.user.mapper.UserMapper;

public class MyBatisUtilTest {
	private static final Logger logger = LogManager.getLogger(MyBatisUtilTest.class);

	@Test
	void test() {
		SqlSessionFactory sqlSessionFactory = MyBatisUtil.getSqlSessionFactory();
		try (SqlSession session = sqlSessionFactory.openSession()) {
			UserMapper mapper = session.getMapper(UserMapper.class);

			User user = mapper.getUserById("kku_1001");
			logger.debug(user.getName());

		}
	}
}
