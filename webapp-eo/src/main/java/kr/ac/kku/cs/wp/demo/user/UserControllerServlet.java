package kr.ac.kku.cs.wp.demo.user;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kku.cs.wp.demo.tools.UserData;
import kr.ac.kku.cs.wp.demo.tools.json.ReflectionUtil;
import kr.ac.kku.cs.wp.demo.tools.message.MessageException;
import kr.ac.kku.cs.wp.demo.user.entity.User;
import kr.ac.kku.cs.wp.demo.user.service.UserServiceImple;

@WebServlet("/user/userlist")
public class UserControllerServlet extends HttpServlet {

	private static final Logger logger = LogManager.getLogger(UserControllerServlet.class);
	private UserServiceImple userService = new UserServiceImple();

	@Override
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
		super.init(config);
	}

//	@Override
//	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//	    // 사용자 데이터 가져오기
//	    Map<String, User> usersMap = UserData.getInstance().getData();
//	    List<User> users = new ArrayList<>(usersMap.values());
//
//	    req.setAttribute("users", users);
//	    
//	    // JSP로 포워드
//	    RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userList.jsp");
//	    rd.forward(req, resp);
//	}
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException { // protected
		// TODO Auto-generated method stub
//		super.doPost(req, resp);
		logger.trace("Test");
		User paramUser = null;
		String contentType = req.getContentType();
		if (contentType != null && contentType.equals("application/json")) {
			logger.trace("json");
			// Readtheincomingrequestbody(JSON)
			StringBuilder jsonBuffer = new StringBuilder();
			String line;
			BufferedReader reader = req.getReader();
			while ((line = reader.readLine()) != null) {
				jsonBuffer.append(line);
			} // ConvertthestringintoaJSONObject
			JSONObject jsonParams = new JSONObject(jsonBuffer.toString());
			try {
				paramUser = ReflectionUtil.createObjectFromJson(User.class, jsonParams);
			} catch (Exception e) {
				// TODOAuto-generatedcatchblock
				e.printStackTrace();
				throw new MessageException(e.getMessage());
			}
			List<User> users = userService.getUsers(paramUser);
			req.setAttribute("users", users);

			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userCards.jsp");
			rd.forward(req, resp);
		} else {
			List<User> users = userService.getUsers(paramUser);
			req.setAttribute("users", users);
			RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/view/user/userList.jsp");
			rd.forward(req, resp);
		}
	}

}
