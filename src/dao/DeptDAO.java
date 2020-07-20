package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Constants;
import bean.Dept;

public class DeptDAO {
	public List<Dept> findAllDept() {
		List<Dept> deptList = new ArrayList<Dept>();
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("SELECT dept_id,dept_name,FROM dept ORDER BY dept_id");
				ResultSet rs = pstmt.executeQuery();) {
			while (rs.next()) {
				int dept_id = Integer.parseInt(rs.getString("dept_id"));
				String dept_name = rs.getString("dept_name");
				Dept sw = new Dept(dept_id, dept_name);
				deptList.add(sw);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return deptList;
	}

	public boolean addDept(String deptID, String deptName) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("INSERT INTO DEPT (dept_id,dept_name) VALUES (?,?)");
				) {
			pstmt.setString(1, deptID);
			pstmt.setString(2, deptName);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean updateDept(String deptID, String deptName) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("UPDATE DEPT SET DEPT_NAME = ? WHERE DEPT_ID = ?");) {
			pstmt.setString(1, deptName);
			pstmt.setString(2, deptID);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public boolean deleteDept(String deptID) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM DEPT WHERE DEPT_ID IS ?");) {
			pstmt.setString(1, deptID);
			int result = pstmt.executeUpdate();
			if (result != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}
}