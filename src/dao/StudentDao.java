package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Student;

public class StudentDao {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String ADDRESS = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "9527";
	
	//判断该学生是否存在
	public boolean isExist(int sno) {
		return queryStudentBySno(sno) == null ? false:true;
	}
	
	//增加学生
	public boolean addStudent(Student stu) {
		Connection conn = null;
		PreparedStatement pstmt =null;
	//	ResultSet rs = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String sql = "insert into studenttest values(?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, stu.getSno());
			pstmt.setString(2, stu.getName());
			pstmt.setInt(3, stu.getAge());
			int result = pstmt.executeUpdate();
			return result > 0 ? true:false;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally{
			
				try {
					if(pstmt != null)
						pstmt.close();
					if(conn != null)
						conn.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			

		}
	}
	
	
	//删除学生
	public boolean deleteStudentBySno(int sno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String sql = "delete from studenttest where sno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(sno, sno);
			int result = pstmt.executeUpdate();
			return result>0 ? true:false; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//修改学生
	public boolean updateStudentBySno(Student stu) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String sql = "update studenttest set name = ?,age = ?,where sno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,stu.getName());
			pstmt.setInt(2, stu.getAge());
			pstmt.setInt(3, stu.getSno());
			int result = pstmt.executeUpdate();
			return result>0 ? true:false; 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//查询全部学生
	public List<Student> queryAllStudents(){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Student> stus = new ArrayList<Student>();
		Student stu = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String sql = "select * from studenttest";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();      //获取数据库返回的数据
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				stu = new Student(no,name,age);
				stus.add(stu);
			}
			
			return stus;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//根据学号查询学生
	public Student queryStudentBySno(int sno) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Student stu = null;
		try {
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(ADDRESS, USER, PASSWORD);
			String sql = "select * from studenttest where sno = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sno);
			rs = pstmt.executeQuery();      //获取数据库返回的数据
			if(rs.next()) {
				int no = rs.getInt("sno");
				String name = rs.getString("name");
				int age = rs.getInt("age");
				stu = new Student(no,name,age);
			}
			
			return stu;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				if(rs != null)
					rs.close();
				if(pstmt != null)
					pstmt.close();
				if(conn != null)
					conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	

}
