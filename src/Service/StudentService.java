package Service;

import java.util.List;

import Bean.Student;
import dao.StudentDao;

public class StudentService {
	StudentDao stuDao = new StudentDao();
	
	//��ѯȫ��ѧ��
	public List<Student> queryAllStudent() {
		return stuDao.queryAllStudents();
	}

	//����ѧ��
	public boolean addStudent(Student stu) {
		if(stuDao.isExist(stu.getSno())) 
			return false;
		return stuDao.addStudent(stu);
	}
	
	//ɾ��ѧ��
	public boolean deleteStudentBySno(int sno) {
		if(stuDao.isExist(sno)) 
			return false;
		return stuDao.deleteStudentBySno(sno);
	}
	
	//�޸�ѧ��
	public boolean updateStudentBySno(Student stu) {
		if(stuDao.isExist(stu.getSno())) {
			return false;
		}
		return stuDao.updateStudentBySno(stu);
	}
	
	//��ѯ����ѧ��
	public Student queryStudentBySno(int sno) {
		return stuDao.queryStudentBySno(sno);
	}
}
