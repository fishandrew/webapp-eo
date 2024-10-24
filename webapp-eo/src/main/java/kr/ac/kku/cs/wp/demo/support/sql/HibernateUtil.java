package kr.ac.kku.cs.wp.demo.support.sql;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class HibernateUtil {
	// Stack 영역에서 Hibernate 설정 로딩 SessionFactory 생성
	private static final SessionFactory sessionFactory = buildSessionFactory();

	private static SessionFactory buildSessionFactory() {
		try {
			return new Configuration().configure().buildSessionFactory();
		} catch (Throwable ex) {

			throw new ExceptionInInitializerError(ex);
		}
	}
	public static SessionFactory getSessionFactory() {
		return sessionFactory;
	}
	public static void shutdown() {
		getSessionFactory().close();
	}
	
}
