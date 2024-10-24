/*
 * 저작권 (c) 2024 어수혁 202020950 모든 권리 보유.
 * 
 * 이 소프트웨어는 고급웹로그래밍 중간고사 코딩 시험 제출용입니다.
 * 이 소프트웨어는 개인적, 교육적 또는 비상업적 목적으로 자유롭게 사용할 수 있습니다.
 * 상업적 사용을 위해서는 타인의 권리를 침해하지 않도록 주의해야 합니다.
 * 
 * 연락처: fishandrew01@naver.com
 * 
 * */
/*
 * LoginControllerServlet
 * 
 * @author 어수혁 202020950-1111
 * @since 2024.10.24
 * @version 1.0
 * */
package kr.ac.kku.cs.wp.demo.aaa;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.demo.user.dao.UserDAO;
import kr.ac.kku.cs.wp.demo.user.dao.UserDAOJdbcImpl;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.aaa.Account;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet(urlPatterns = { "/login", "/logout" })
public class LoginControllerServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(LoginControllerServlet.class);
	private UserDAO userDAO;

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		userDAO = new UserDAOJdbcImpl();  // DAO 객체 초기화
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String context = req.getServletContext().getContextPath();
		String uriStr = req.getRequestURI().replaceAll(context, "");

		logger.entry(); // 로그에 엔트리 남기기
		
		if (uriStr.equals("/login")) { // 로그인 처리
			String id = req.getParameter("username");
			String password = req.getParameter("password");
			
			User user = userDAO.getUserById(id); // UserDAO를 통해 사용자 정보 조회
			
			if (user == null) {
				// 사용자가 존재하지 않을 경우
				req.setAttribute("error", "login_fail");
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
			} else {
				// 비밀번호 비교
				if (!password.equals(user.getPassword())) {
					// 비밀번호가 일치하지 않음
					req.setAttribute("error", "login_fail");
					req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
				} else {
					// 로그인 성공 처리
					Account account = new Account();
	                account.setId(user.getId());
//	                account.setRole(user.getRole());
	                account.setEmail(user.getEmail());
	                account.setName(user.getName());

	                HttpSession session = req.getSession();
	                session.setAttribute("user", account); // Account 객체를 세션에 저장
					
					logger.info("User {} logged in successfully", user.getId()); // 로그인 성공 로그
					resp.sendRedirect(context);
				}
			}
		} else if (uriStr.equals("/logout")) { // 로그아웃 처리
			HttpSession session = req.getSession();
			if (session != null) {
				session.invalidate();
			}
			resp.sendRedirect(context + "/login");
		}
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String context = req.getServletContext().getContextPath();
		String uriStr = req.getRequestURI().replaceAll(context, "");

		if (uriStr.equals("/login")) {
			req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
		}
	}
}
