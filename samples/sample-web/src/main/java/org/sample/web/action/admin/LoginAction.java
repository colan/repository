package org.sample.web.action.admin;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.convention.annotation.Results;
import org.sample.web.action.BaseAction;
import org.sample.web.persistence.model.Student;
import org.sample.web.service.StudentService;


@Namespace("/admin/login")
@Results({
	@Result(name="sina", type="redirect", location="http://www.sina.com.cn")
})
public class LoginAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	
	@Resource StudentService studentService;
	Student student;
	
	@Action("loginPage")
	public String loginPage() {
		return "login_page";
	}
	
	@Action("ajaxJsonLogin")
	public String ajaxJsonLogin() {
		Student s = studentService.findStudent(student.getUsername(), student.getPasswordMd5());
		if(s == null) {
			setJsonMsg("用户名不存在或密码错误", false, null);
		} else {
			setJsonMsg("登录成功 正在加载数据...", true, null);
		}
		
		return JSON_PAGE;
	}
	
	@Action("main")
	public String main() {
		studentService.queryStudentProc();
		
		return "sina";
	}

	public void setStudent(Student student) {
		this.student = student;
	}

	public Student getStudent() {
		return student;
	}
}
