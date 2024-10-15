package kr.ac.kku.cs.wp.demo;

import java.io.IOException;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

// 하나더 import 해야함
public class HelloServlet extends HttpServlet {
	
	private String greeting = null;
	@Override
	public void init(ServletConfig config) throws ServletException {
		greeting = config.getInitParameter("greeting");
		// TODO Auto-generated method stub
		super.init(config);
	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		log("Hello Servlet ");
		String html="""
		<html>
		<head></head>
		<body>
		""" + greeting + """
		<body>
		<html>
		""";
		
		res.getWriter().println(html);
	}
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		log("Hello was destoryed");
		super.destroy();
	}


}
