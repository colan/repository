package org.sample.web.persistence.dao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.SQLQuery;
import org.sample.web.persistence.model.Student;
import org.springframework.stereotype.Repository;

@Repository
public class StudentDao extends BaseHibernateDao<Student, Long> {

	public Student findStudent(String username, String passwordMd5) {
			
		String hql = "from Student t where t.username = :username and t.passwordMd5 = :passwordMd5";
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("username", username);
		paramMap.put("passwordMd5", passwordMd5);
		
		return (Student) createQuery(hql, paramMap).setMaxResults(1).uniqueResult();
	}
	
	public void queryStudentProc() {
		SQLQuery query = getSession().createSQLQuery("{Call student_proc(:username, :passwordMd5)}");
		query.setParameter("username", "admin");
		query.setParameter("passwordMd5", "admin");
		
		List<Object[]> list = query.list();
		for(Object[] obj : list) {
			System.out.println(obj[0] + "|" + obj[1]);
		}
	}
	
}
