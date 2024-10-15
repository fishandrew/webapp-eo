package kr.ac.kku.cs.wp.demo.aaa;

import java.io.IOException;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import kr.ac.kku.cs.wp.demo.support.servlet.BaseFilter;
import kr.ac.kku.cs.wp.demo.user.User;

@WebFilter("/jsp/admin/*")
public class AuthorityFilter extends BaseFilter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		// 관리자만 접근할수 있도록 하는 필터링 logic 구현

		HttpServletRequest req = (HttpServletRequest) request;
		HttpSession hs = req.getSession(false);

		User user = (User) hs.getAttribute("user");
		String role = user.getRole();

		if (!role.equals("admin")) {
			HttpServletResponse res= (HttpServletResponse) request;
			res.getWriter().print("Admin acess only");
		} else {
			chain.doFilter(request, response);
		}
	}

}