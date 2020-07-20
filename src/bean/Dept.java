package bean;

import java.io.Serializable;

public class Dept implements Serializable {
	/**
	 * フィールド定義
	 */
	private static final long serialVersionUID = 1L;
	private int deptID;
	private String deptName;

	/**
	 * コンストラクタ
	 */
	public Dept() {};
	public Dept(int deptID,String deptName) {
		this.deptID = deptID;
		this.deptName = deptName;
	}

	/**
	 * getter/setterメソッド
	 */
	public int getDeptID() {return deptID;}
	public void setDeptID(int deptID) {this.deptID = deptID;}

	public String getDeptName() {return deptName;}
	public void setDeptName(String deptName) {this.deptName = deptName;}

}
