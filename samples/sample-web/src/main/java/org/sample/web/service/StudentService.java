package org.sample.web.service;

import javax.annotation.Resource;

import org.sample.web.persistence.dao.StudentDao;
import org.sample.web.persistence.model.Student;
import org.springframework.stereotype.Service;

import com.easyssh.framework.service.EntityService;

@Service
public class StudentService extends EntityService<Student, StudentDao> {

	@Resource StudentDao studentDao;
	
	@Override
	public StudentDao getEntityDao() {
		return studentDao;
	}
	
	public Student findStudent(String username, String passwordMd5) {
		return studentDao.findStudent(username, passwordMd5);
	}
	
	public void queryStudentProc() {
		studentDao.queryStudentProc();
	}
}
