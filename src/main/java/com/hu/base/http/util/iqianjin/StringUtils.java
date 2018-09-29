package com.hu.base.http.util.iqianjin;


public final class StringUtils {
	private StringUtils() {
	}

	/** 鏄┖ */
	public static boolean isEmpty(String input) {
		return StringCompareUtils.isAllEmpty(input);
	}

	/** 涓嶆槸绌� */
	public static boolean isNotEmpty(String input) {
		return StringCompareUtils.isNotEmpty(input);
	}

	/** null to empty, trim鍓嶅悗绌烘牸 */
	public static String trim(String input) {
		return input == null ? ConstString.EMPTY : input.trim();
	}

	/** 涓簄ull灏辫浆鎹㈡垚empty */
	public static String nullToEmpty(String input) {
		return input == null ? ConstString.EMPTY : input;
	}

	/** 涓篹mpty灏辫浆鎹㈡垚null */
	public static String emptyToNull(String input) {
		return StringCompareUtils.isNullOrEmpty(input) ? null : input;
	}
}
