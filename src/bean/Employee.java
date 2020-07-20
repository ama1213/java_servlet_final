package bean;

import java.io.Serializable;

public class Employee implements Serializable {
	/**
	 * フィールド定義
	 */
	private static final long serialVersionUID = 1L;
	private int empID;
	private String empName;
	private int age;
	private Gender gender;
	private int pictID;
	private String zipcode;
	private Prefecture pref;
	private String address;
	private int deptID;
	private String dateEntering;
	private String dateRetired;

	/**
	 * コンストラクタ
	 */
	public Employee() {}

	public Employee(int empID, String empName, int age, Gender gender,
			int pictID, String zipcode, Prefecture pref, String address,
			int deptID, String dateEntering, String dateRetired) {

		this.empID = empID;
		this.empName = empName;
		this.age = age;
		this.gender = gender;
		this.pictID = pictID;
		this.zipcode = zipcode;
		this.pref = pref;
		this.address = address;
		this.deptID = deptID;
		this.dateEntering = dateEntering;
		this.dateRetired = dateRetired;
	}

	/**
	 * getter/setterメソッド
	 */
	public int getEmpID() {return empID;}
	public void setEmpID(int empID) {this.empID = empID;}

	public String getEmpName() {return empName;}
	public void setEmpName(String empName) {this.empName = empName;}

	public int getAge() {return age;}
	public void setAge(int age) {this.age = age;}

	public Gender getGender() {return gender;}
	public void setGender(Gender gender) {this.gender = gender;}

	public int getPictID() {return pictID;}
	public void setPictID(int pictID) {this.pictID = pictID;}

	public String getZipcode() {return zipcode;}
	public void setZipcode(String zipcode) {this.zipcode = zipcode;}

	public Prefecture getPref() {return pref;}
	public void setPref(Prefecture pref) {this.pref = pref;}

	public String getAddress() {return address;}
	public void setAddress(String address) {this.address = address;}

	public int getDeptID() {return deptID;}
	public void setDeptID(int deptID) {this.deptID = deptID;}

	public String getDateEntering() {return dateEntering;}
	public void setDateEntering(String dateEntering) {this.dateEntering = dateEntering;}

	public String getDateRetired() {return dateRetired;}
	public void setDateRetired(String dateRetired) {this.dateRetired = dateRetired;}

}