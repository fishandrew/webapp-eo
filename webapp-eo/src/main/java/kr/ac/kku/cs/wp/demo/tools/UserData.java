package kr.ac.kku.cs.wp.demo.tools;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kr.ac.kku.cs.wp.demo.user.User;

public class UserData {
	private static UserData THIS;

	private UserData() {
		this.users = new HashMap<>(); // Map 초기화
		this.initData();

	}

	private Map<String, User> users;

	private void initData() {
		// TODO Auto-generated method stub
		// UserControllerServelet에 있는 내용을 복사하고 users 필드에 대입하도록 변경
//		List<User> users = new ArrayList<User>();

		String[] sNames = { "김구", "안창호", "안중근", "유관순", "홍범도", "김좌진", "남자현", "윤봉길", "이봉창", "김원봉" };
		String[] sEmails = { "kim1@kku.ac.kr", "an1@kku.ac.kr", "an2@kku.ac.kr", "yu@kku.ac.kr", "hong@kku.ac.kr",
				"kim2@kku.ac.kr", "nam@kku.ac.kr", "yun1@kku.ac.kr", "lee@kku.ac.kr", "kim3@kku.ac.kr" };
		String[] sRoles = { "관리자", "개발자", "시스템관리자" };

		for (int i = 0; i < 20; i++) {
			User user = new User();
			user.setPhotoSRC("https://via.placeholder.com/150");
			user.setName(sNames[(i + 1) % 10]);
			user.setEmail(sEmails[(i + 1) % 10]);
			user.setId("kku_" + (1000 + i));

			List<String> roles = new ArrayList<>();
			roles.add(sRoles[(i + 2) % 3]);
			roles.add(sRoles[(i + 1) % 3]);
			user.setRoles(roles);

			user.setStatus("재직중");
			users.put(user.getId(), user); // 사용자를 맵에 추가
		}
	}

	public Map<String, User> getData() {
		return users;
	}

	public static synchronized UserData getInstance() {
		if (THIS == null) {
			THIS = new UserData();
		}
		return THIS;
	}
}
