package bean;

public enum Gender {
	UNKNOWN(""),
	FEMALE("女"),
	MALE("男");

	/**
	 * フィールド
	 */
	private String text;

	/**
	 * コンストラクタ
	 */
	private Gender(String text) {this.text = text;}

	/**
	 * getter/setter
	 */
	public String getText() {return text;}
	public String toString() {return this.text;}

	public static Gender getByText(String text) {
		for (Gender g : Gender.values()) {
			if (g.getText().equals(text))
				return g;
		}
		return null;
	}
}