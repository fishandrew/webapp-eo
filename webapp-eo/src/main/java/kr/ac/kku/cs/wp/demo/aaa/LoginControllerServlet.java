package kr.ac.kku.cs.wp.demo.aaa;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.demo.tools.UserData;
import kr.ac.kku.cs.wp.demo.user.User;

import java.io.IOException;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Servlet implementation class LoginControllerServlet
 */
@WebServlet(urlPatterns = { "/login", "/logout" })
public class LoginControllerServlet extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(LoginControllerServlet.class);

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
	
		
		String context = req.getServletContext().getContextPath();
		String uriStr = req.getRequestURI().replaceAll(context, "");

//		log("in post :" + uriStr);
		
		logger.entry(); //왜 안나옴?
		
		if (uriStr.equals("/login")) { // login
			Map<String, User> users = UserData.getInstance().getData();

			String id = req.getParameter("username");
			String password = req.getParameter("password");
			User user = users.get(id);
			if (user == null) {
				req.setAttribute("error", "login_fail");
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
			} else {
				log("Received password: " + password);
				log("Stored password: " + user.getPassword());
				if (!password.equals(user.getPassword())) {
					req.setAttribute("error", "login_fail");
					req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
				} else {
					
					// Account 객체 생성 후 세션에 저장
	                Account account = new Account();
	                account.setId(user.getId());
	                account.setRole(user.getRole());
	                account.setEmail(user.getEmail());
	                account.setName(user.getName());

	                HttpSession session = req.getSession();
	                session.setAttribute("user", account); // Account 객체를 세션에 저장

//					HttpSession session = req.getSession();
//					session.setAttribute("user", user); // User 객체를 세션에 저장
					logger.info("User {} logged in successfully", user.getId()); // 로그인 성공 로그 추가
					resp.sendRedirect(context);
				}
			}
		} else if (uriStr.equals("/logout")) { // logout
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

		log("in service: " + uriStr);

		if (uriStr.equals("/login")) {
			req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
		}
	}

}
