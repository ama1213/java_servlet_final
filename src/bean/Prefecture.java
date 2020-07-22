package bean;

public enum Prefecture {
	UNKNOWN	(0, ""),
	HOKKAIDO(1, "�k�C��"),
	AOMORI	(2, "�X"),
	IWATE	(3, "���"),
	MIYAGI	(4, "�{��"),
	AKITA	(5, "�H�c"),
	YAMAGATA(6, "�R�`"),
	FUKUSHIMA(7, "����"),
	IBARAKI	(8, "���"),
	TOCHIGI	(9, "�Ȗ�"),
	GUNMA	(10, "�Q�n"),
	SAITAMA	(11, "���"),
	CHIBA	(12, "��t"),
	TOKYO	(13, "����"),
	KANAGAWA(14, "�_�ސ�"),
	NIIGATA	(15, "�V��"),
	TOYAMA	(16, "�x�R"),
	ISHIKAWA(17, "�ΐ�"),
	FUKUI	(18, "����"),
	YAMANASHI(19, "�R��"),
	NAGANO	(20, "����"),
	GIFU	(21, "��"),
	SHIZUOKA(22, "�É�"),
	AICHI	(23, "���m"),
	MIE		(24, "�O�d"),
	SHIGA	(25, "����"),
	KYOTO	(26, "���s"),
	OSAKA	(27, "���"),
	HYOGO	(28, "����"),
	NARA	(29, "�ޗ�"),
	WAKAYAMA(30, "�a�̎R"),
	TOTTORI	(31, "����"),
	SHIMANE	(32,"����"),
	OKAYAMA	(33, "���R"),
	HIROSHIMA(34, "�L��"),
	YAMAGUCHI(35, "�R��"),
	TOKUSHIMA(36, "����"),
	KAGAWA	(37, "����"),
	EHIME	(38, "���Q"),
	KOCHI	(39, "���m"),
	FUKUOKA	(40, "����"),
	SAGA	(41,"����"),
	NAGASAKI(42, "����"),
	KUMAMOTO(43, "�F�{"),
	OITA	(44, "�啪"),
	MIYAZAKI(45, "�{��"),
	KAGOSHIMA(46, "������"),
	OKINAWA	(47, "����");

	private final int code;
	private final String text;

	private Prefecture(int code, String text) {
		this.code = code;
		this.text = text;
	}

	public int getCode() {
		return code;
	}

	public String getText() {
		return text;
	}

	public String getFullText() {
		switch (this) {
		case UNKNOWN:
		case HOKKAIDO:
			return getText();
		case TOKYO:
			return getText() + "�s";
		case KYOTO:
		case OSAKA:
			return getText() + "�{";
		default:
			return getText() + "��";
		}
	}

	public static Prefecture getByCode(int code) {
		for (Prefecture p : Prefecture.values()) {
			if (p.getCode() == code)
				return p;
		}
		return null;
	}

	public static Prefecture getByText(String text) {
		for (Prefecture p : Prefecture.values()) {
			if (p.getText().equals(text))
				return p;
		}
		return null;
	}

	public static Prefecture getByFullText(String fullText) {
		for (Prefecture p : Prefecture.values()) {
			if (p.getFullText().equals(fullText))
				return p;
		}
		return null;
	}
}