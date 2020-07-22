package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Constants;
import bean.Employee;
import bean.Gender;
import bean.Prefecture;

public class EmployeeDAO {
	public List<Employee> findAllEmp() {
		List<Employee> empAllList = new ArrayList<Employee>();
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "SELECT emp_id,emp_name,age,gender,image_id,"
				+ "zipcode,prefecture,address,dept_id,date_entering,"
				+ "date_retired FROM employee ORDER BY emp_id";
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery();
			) {
			while (rs.next()) {
				int empID = Integer.parseInt(rs.getString("emp_id"));
				String empName = rs.getString("emp_name");
				String ageSTR = rs.getString("age");
					if(ageSTR.equals("")) {ageSTR = "0";}
					int age = Integer.parseInt(ageSTR);
				String genderSTR = rs.getString("gender");
					Gender gender = Gender.getByText(genderSTR);
				String imageIDSTR = rs.getString("image_ID");
					if(imageIDSTR == null || imageIDSTR.equals("")) {imageIDSTR = "0";}
					int imageID = Integer.parseInt(imageIDSTR);
				String zipcode = rs.getString("zipcode");
				String prefSTR = rs.getString("prefecture");
					Prefecture pref = Prefecture.getByFullText(prefSTR);
				String address = rs.getString("address");
				String deptIDSTR = rs.getString("dept_ID");
					if(deptIDSTR.equals("")) {deptIDSTR = "0";}
					int deptID = Integer.parseInt(deptIDSTR);
				String dateEntering = rs.getString("date_entering");
				String dateRetired = rs.getString("date_retired");
				Employee emp = new Employee(empID, empName, age, gender, imageID,
						zipcode, pref, address, deptID, dateEntering, dateRetired);
				empAllList.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return empAllList;
	}

	public boolean updateEmp(List<String> empParams) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "UPDATE EMPLOYEE SET ("
				+ "emp_name,age,gender,image_id,zipcode,"
				+ "prefecture,address,dept_id,date_entering,date_retired"
				+ ") = ("
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?"
				+ ") WHERE EMP_iD = ?";
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			for(int i = 0; i < empParams.size(); i++) {
				pstmt.setString(i + 1, empParams.get(i));
			}
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

	public boolean addEmp(List<String> empParams) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		String sql = "INSERT INTO EMPLOYEE ("
				+ "emp_id,emp_name,age,gender,image_id,"
				+ "zipcode,prefecture,address,dept_id,date_entering,"
				+ "date_retired"
				+ ") VALUES ("
				+ "?,?,?,?,?,"
				+ "?,?,?,?,?,"
				+ "?)";
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement(sql);
			) {
			for(int i = 0; i < empParams.size(); i++) {
				pstmt.setString(i + 1, empParams.get(i));
			}
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

	public boolean deleteEmp(String empID) {
		try {
			Class.forName(Constants.DRIVER_NAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		try (
				Connection conn = DriverManager.getConnection(Constants.JDBC_URL, Constants.DB_USER, Constants.DB_PASS);
				PreparedStatement pstmt = conn.prepareStatement("DELETE FROM EMPLOYEE WHERE EMP_ID = ?");
			) {
			pstmt.setString(1, empID);
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