package kr.ac.kku.cs.wp.demo.aaa;

import java.io.IOException;
import java.util.Date;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kr.ac.kku.cs.wp.demo.tools.message.MessageException;
//import kr.ac.kku.cs.wp.demo.aaa.Account;

@WebFilter(urlPatterns = { "/jsp/user/", "/jsp/admin/*", "/logout" }, 
initParams = {@WebInitParam(name = "valve", value = "on") }) // value="on"

public class AuthenticationFilter implements Filter {
    private static final Logger logger = LogManager.getLogger(AuthenticationFilter.class);
    private boolean valve = true;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String sValve = filterConfig.getInitParameter("valve");

        if (sValve != null) {
            if (sValve.equals("on")) {
                valve = true;
            } else if (sValve.equals("off")) {
                valve = false;
            }
        }

        // 로그 추가
        logger.info("init AuthenticationFilter (" + sValve + ")");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        if (valve) {
            HttpServletRequest req = (HttpServletRequest) request;
            HttpSession session = req.getSession();
//           User user = (User) session.getAttribute("user");
            Account account =( Account ) session.getAttribute("user");

            if (account != null) {
                logger.info(account.getId() + " has logged in");

                // 추가 로그 - 사용자의 요청 URI와 액세스 시간 기록
                Date now = new Date();
                logger.info("{} accessed {} at {}", account, req.getRequestURI(), now); 

                chain.doFilter(request, response);
            } else {
                HttpServletResponse res = (HttpServletResponse) response;  
//                res.getWriter().println("plz log in");
//                logger.warn("Unauthorized access attempt to {}", req.getRequestURI());
                throw new MessageException("login_required");
            }
        } else {
            chain.doFilter(request, response);
        }
    }

    @Override
    public void destroy() {
        // 필터 종료 시 로그 추가
        logger.debug("destroy AuthenticationFilter");
    }
}
