<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="kr.ac.kku.cs.wp.demo.user.entity.User"%>

<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags/template"%>

<%@ taglib prefix="custom" uri="kr.ac.kku.cs.wp.demo.tools.tags.custom"%>

<h2>Custom 사용자 목록</h2>

<!-- 필터 입력 필드 -->
<div class="filter-container">
	<input type="text" id="user-filter"
		placeholder="이름, 이메일, 역할 또는 사번으로 검색">
	<button type="button" onclick="filterUsers()">검색하기</button>
</div>

<div id="refresh">
	<div id="user-count" style="margin-bottom: 20px;">
		전체: <strong>${fn:length(requestScope.users)}</strong>
	</div>
	<div class="user-card-container" id="user-list">
		<c:forEach var="user" items="${users}">
			<!-- 사용자 카드 1 (여러 역할 및 사번 추가) -->
			<custom:userCard status="${user.status}" email="${user.email}"
				name="${user.name}"
				roles="${user.userRoles[0].role.role}, ${user.userRoles[1].role.role}"
				id="${user.id}" />
		</c:forEach>
	</div>
</div>

<!-- 
<div class="user-card-container" id="user-list"> -->
<%-- 
<c:forEach var="user" items="${requestScope.users }">
<custom:userCard status="${user.status }" email="${user.email }"
			name="${user.name }" roles="${user.roles[0] },${user.roles[1] }"
			id="${user.id }" />
	</c:forEach>

</div> --%>

<script>
	// 사용자 필터링 함수
	function filterUsers() {
		const filterValue = document.getElementById('user-filter').value
				.toLowerCase();
		filter({
			'id' : filterValue,
			'name' : filterValue,
			'email' : filterValue,
			'status' : filterValue,
			'userRoles' : [ {
				'roleName' : filterValue
			} ]
		});
	}

	// 페이지를 로드하고 카드를 동적으로 생성하는 함수
	async function filter(params) {
		const mainContent = document.getElementById('refresh');

		// POST 요청을 보내면서 파라미터를 전달
		const response = await
		fetch('user/userlist', {
			method : 'POST',
			headers : {
				'Content-Type' : 'application/json', // JSON 형식으로 전달
			},
			body : JSON.stringify(params)
		// 파라미터를 JSON으로 변환하여 body에 추가
		});

		const isOk = response.ok;
		const data = await
		response.text();

		if (!isOk) {
			openModalFetch(data); // 오류 발생 시 모달창 호출
		} else {
			mainContent.innerHTML = data;
		}
	}
</script>

<style>
.filter-container {
	margin-bottom: 20px;
}

.filter-container input {
	width: 100%;
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.user-card-container {
	display: grid;
	grid-template-columns: repeat(auto-fit, minmax(180px, 1fr));
	/* 카드 크기를 좁게 설정 */
	gap: 20px;
}

.user-card {
	background-color: white;
	border-radius: 10px;
	box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
	overflow: hidden;
	text-align: center;
	transition: transform 0.3s ease;
	width: 180px; /* 카드 폭을 좁게 */
	height: 300px; /* 카드 높이를 길게 */
}

.user-card:hover {
	transform: scale(1.05);
}

.user-card img {
	width: 100%;
	height: 150px; /* 이미지 높이 늘림 */
	object-fit: cover;
}

.user-info {
	padding: 10px;
	font-size: 14px;
}

.user-info h3 {
	font-size: 16px;
	margin-bottom: 10px;
	color: #007bff;
}

.user-info p {
	font-size: 12px;
	color: #333;
	line-height: 0;
	margin-bottom: 15px;
}

.user-info button {
	background-color: #007bff;
	color: white;
	border: none;
	padding: 5px 10px;
	border-radius: 5px;
	cursor: pointer;
	font-size: 12px;
	margin-top: 10px;
}

.user-info button:hover {
	background-color: #0056b3;
}

/* 반응형 디자인 */
@media ( max-width : 768px) {
	.user-card-container {
		grid-template-columns: repeat(auto-fit, minmax(120px, 1fr));
		/* 작은 화면에서 더 좁게 */
	}
	.user-info h3 {
		font-size: 14px;
	}
	.user-info p {
		font-size: 11px;
	}
	.user-info button {
		font-size: 10px;
	}
}

.filter-container {
	display: flex;
	gap: 10px; /* 검색창과 버튼 사이의 간격 */
	margin-bottom: 20px;
}

.filter-container input {
	flex: 1; /* 입력 창이 가능한 공간을 차지하도록 설정 */
	padding: 10px;
	font-size: 16px;
	border: 1px solid #ccc;
	border-radius: 5px;
}

.filter-container button {
	padding: 10px 20px; /* 버튼 크기 조정 */
	font-size: 16px;
	background-color: #007bff;
	color: white;
	border: none;
	border-radius: 5px;
	cursor: pointer;
}

.filter-container button:hover {
	background-color: #0056b3;
}
</style>
