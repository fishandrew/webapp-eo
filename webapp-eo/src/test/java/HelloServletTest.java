
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kku.cs.wp.demo.HelloServlet;

public class HelloServletTest {
	private static final Logger logger = LogManager.getLogger(HelloServletTest.class);
// 이하 mock field 선언 및 테스트 전처리, 테스트 함수 구현

	@InjectMocks
	private HelloServlet helloServlet;
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
// PrintWriter
		responseWriter = new StringWriter();
		when(response.getWriter()).thenReturn(new PrintWriter(responseWriter));
// init 파라미터 모의
		when(config.getInitParameter("greeting")).thenReturn("Hello, World from ServletConfig!");
		when(config.getServletContext()).thenReturn(context);
// 서블릿 초기화
		helloServlet.init(config);
		logger.debug("초기화 완료 ");
	}
	@Test
	void testDoService() throws ServletException, IOException {
		helloServlet.service(request, response);
		verify(config).getInitParameter("greeting");
		assert (responseWriter.toString().contains("Hello, World from ServletConfig!"));
	}
}