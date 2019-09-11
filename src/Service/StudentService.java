package Service;

import java.util.List;

import Bean.Student;
import dao.StudentDao;

public class StudentService {
	StudentDao stuDao = new StudentDao();
	
	//查询全部学生
	public List<Student> queryAllStudent() {
		return stuDao.queryAllStudents();
	}

	//增加学生
	public boolean addStudent(Student stu) {
		if(stuDao.isExist(stu.getSno())) 
			return false;
		return stuDao.addStudent(stu);
	}
	
	//删除学生
	public boolean deleteStudentBySno(int sno) {
		if(stuDao.isExist(sno)) 
			return false;
		return stuDao.deleteStudentBySno(sno);
	}
	
	//修改学生
	public boolean updateStudentBySno(Student stu) {
		if(stuDao.isExist(stu.getSno())) {
			return false;
		}
		return stuDao.updateStudentBySno(stu);
	}
	
	//查询单个学生
	public Student queryStudentBySno(int sno) {
		return stuDao.queryStudentBySno(sno);
	}
}
