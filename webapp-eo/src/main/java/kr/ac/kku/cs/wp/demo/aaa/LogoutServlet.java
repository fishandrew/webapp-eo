package kr.ac.kku.cs.wp.demo.aaa;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

//@WebServlet("/logout")
public class LogoutServlet extends HttpServlet {

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		// logout logic

		HttpSession session = req.getSession(false);

		if (session != null) {
			session.invalidate();
		}
		res.getWriter().println("Logged out");

	}
}