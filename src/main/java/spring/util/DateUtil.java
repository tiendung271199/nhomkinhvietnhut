package spring.util;

import java.sql.Timestamp;

public class DateUtil {

	public static String formatDate(Timestamp date) {
		String[] array = date.toString().split("\\s")[0].split("-");
		StringBuilder sb = new StringBuilder();
		sb.append(array[2]).append("/").append(array[1]).append("/").append(array[0]);
		return sb.toString();
	}

}
