package kr.ac.kku.cs.wp.demo.aaa;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.demo.user.User;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

//로그인 로직 구현   
		HttpSession hs = req.getSession();
		User sUser = (User) hs.getAttribute("user");
		if (sUser == null) {
			String id = req.getParameter("id");
			String role = req.getParameter("role");

			User user = new User();

			user.setId(id);
			user.setRole(role);
			hs.setAttribute("user", user);
			
			hs.setMaxInactiveInterval(900); //15분 (초단위 )

			log(user.getId());
			log(user.getRole());

			res.getWriter().println("Login sucessful.");

		} else {
			res.getWriter().println(sUser.getId() + "!plz log out first");
		}
	}
}