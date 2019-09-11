package Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DBUtil {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String ADDRESS = "jdbc:mysql://localhost:3306/test?useSSL=false&serverTimezone=UTC";
	private static final String USER = "root";
	private static final String PASSWORD = "9527";
	
	//private static Connection conn=null;
	//private static PreparedStatement pstmt=null;
	//private ResultSet rs =null;
	
	
	//获取数据库连接
	public static Connection createConnection(String address,String user,String password) throws ClassNotFoundException, SQLException{
		Class.forName(DRIVER);
		return DriverManager.getConnection(address, user, password);
	}
	
	//关闭数据库连接
	public static void closeAll(ResultSet rs,Statement stmt,Connection conn) throws SQLException {
		if(rs != null) {
			rs.close();
		}
		if(stmt != null) {
			stmt.close();
		}
		if(conn != null) {
			conn.close();
		}
	}
	
	//创建PrepareStatement对象
	public static PreparedStatement createPrepareStatement(Connection conn,String sql,Object[] params) throws SQLException {
		PreparedStatement pstmt = conn.prepareStatement(sql);
		if(params != null) {
			for(int i=0;i<params.length;i++) {
				pstmt.setObject(i+1, params[i]);
			}
		}
		return pstmt;
	}
	
	//增删改
	public static boolean executeUpdate(String sql,Object[] params) {
		Connection conn = null;	
		PreparedStatement pstmt =null;
		try {
			conn = createConnection(ADDRESS,USER,PASSWORD);
			pstmt = createPrepareStatement(conn,sql,params);
			int result = pstmt.executeUpdate();
			return result > 0 ? true:false;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
		finally {
			try {
				closeAll(null,pstmt,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	//查询
	public static ResultSet executeQuery(String sql,Object[] params) {
		Connection conn = null;	
		PreparedStatement pstmt =null;
		ResultSet rs = null;
		try {
			conn = createConnection(ADDRESS,USER,PASSWORD);
			pstmt = createPrepareStatement(conn,sql,params);
			rs = pstmt.executeQuery();
			return rs;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
		finally {
			try {
				closeAll(null,pstmt,conn);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
