package bean;

import java.io.Serializable;

public class Picture implements Serializable {
	/**
	 * フィールド定義
	 */
	private static final long serialVersionUID = 1L;
	private int pictID;
	private String pictImage;

	/**
	 * コンストラクタ
	 */
	public Picture() {}
	public Picture(int pictID, String pictImage) {
		this.pictID = pictID;
		this.pictImage = pictImage;
	}

	/**
	 * getter/setterメソッド
	 */
	public int getPictID() {return pictID;}
	public void setPictID(int pictID) {this.pictID = pictID;}

	public String getPictImage() {return pictImage;}
	public void setPictImage(String pictImage) {this.pictImage = pictImage;}

}