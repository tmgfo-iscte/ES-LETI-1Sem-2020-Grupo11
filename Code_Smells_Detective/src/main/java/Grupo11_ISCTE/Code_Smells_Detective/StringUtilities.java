package Grupo11_ISCTE.Code_Smells_Detective;

public class StringUtilities {

	public static String fitStringToLength(String string, int length) {

		return padLeft(truncate(string, length), length);

	}

	public static String truncate(String string, int length) {

		if (string.length() > length)
			return string.substring(0, length);
		else {
			return string;
		}

	}

	public static String padRight(String s, int n) {
		return String.format("%-" + n + "s", s);
	}

	public static String padLeft(String s, int n) {
		return String.format("%" + n + "s", s);
	}

}
