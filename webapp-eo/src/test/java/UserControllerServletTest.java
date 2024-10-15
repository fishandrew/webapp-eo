import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kku.cs.wp.demo.user.UserControllerServlet;

public class UserControllerServletTest {
	private static final Logger logger = LogManager.getLogger(UserControllerServletTest.class);
// 이하 mock field 선언 및 테스트 전처리, 테스트 함수 구현

	@InjectMocks
	private UserControllerServlet userControllerServlet;
	@Mock
	RequestDispatcher dispatcher;
	@Mock
	private HttpServletRequest request;
	@Mock
	private HttpServletResponse response;
	private StringWriter responseWriter;
	@Mock
	private ServletConfig config;
	@Mock
	private ServletContext context;

	@BeforeEach
	void setUp() throws IOException, ServletException {

// Mockito 어노테이션 초기화
		MockitoAnnotations.openMocks(this);
// PrintWriter 99
		responseWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
		when(config.getServletContext()).thenReturn(context); // 서블릿 초기화
		userControllerServlet.init(config);
		logger.trace("초기화 완료 ");
	}

	@Test
	void testDoPost() throws ServletException, IOException {
		when(request.getRequestDispatcher("/WEB-INF/view/user/userList.jsp")).thenReturn(dispatcher);
		userControllerServlet.doPost(request, response);
		verify(request).getRequestDispatcher("/WEB-INF/view/user/userList.jsp");
		verify(dispatcher).forward(request, response);
	}
}