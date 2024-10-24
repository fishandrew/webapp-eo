package kr.ac.kku.cs.wp.demo.tools.tags.custom;

import java.io.IOException;

import jakarta.servlet.jsp.JspException;
import jakarta.servlet.jsp.tagext.SimpleTagSupport;

public class UserCardTag extends SimpleTagSupport {
    // attribute
    private String name;
    private String email;
    private String id;
    private String roles;
    private String status;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoles() {
        return roles;
    }

    public void setRoles(String roles) {
        this.roles = roles;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public void doTag() throws JspException, IOException {
        // Create user card HTML string
        String userCard = "<div class=\"user-card\" data-name=\"" + name + "\" data-email=\"" + email 
            + "\" data-role=\"" + roles + "\" data-id=\"" + id + "\">\n"
            + "<img src=\"https://via.placeholder.com/150\" alt=\"" + name + " 사진\">\n"
            + "<div class=\"user-info\">\n"
            + "<h3>" + name + "</h3>\n"
            + "<p><strong>이메일:</strong> " + email + "</p>\n"
            + "<p><strong>역할:</strong> " + roles + "</p>\n"
            + "<p><strong>사번:</strong> " + id + "</p>\n"
            + "<p><strong>상태:</strong> " + status + "</p>\n"
            + "<button onclick=\"alert('" + name + "의 상세 정보')\">상세 보기</button>\n"
            + "</div>\n"
            + "</div>";
        
        // Output the generated user card HTML
        getJspContext().getOut().print(userCard);
    }
}
