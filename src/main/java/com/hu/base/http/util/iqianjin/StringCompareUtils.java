package com.hu.base.http.util.iqianjin;


public class StringCompareUtils {
	//
	// ========================== 绌烘垨NULL鍒ゆ柇 ========================= //
	//

	/** 鏄惁涓簄ull鎴杄mpty */
	public static boolean isNullOrEmpty(String input) {
		return input == null || input.equals(ConstString.EMPTY);
	}

	/**
	 * 鏄惁涓篘ULL鎴栧涓┖鏍兼垨""锛屼笌empty鐨勫尯鍒湪浜庡涓┖鏍间篃绠梑lank
	 * isBlank(null) = true;
	 * isBlank(&quot;&quot;) = true;
	 * isBlank(&quot; &quot;) = true;
	 * isBlank(&quot;a&quot;) = false;
	 * isBlank(&quot;a &quot;) = false;
	 * isBlank(&quot; a&quot;) = false;
	 * isBlank(&quot;a b&quot;) = false;
	 * </pre>
	 */
	public static boolean isBlank(final String... textArray) {
		for (String s : textArray) {
			if (s != null && s.trim().length() > 0) {
				return false;
			}
		}
		return true;
	}

	/** 鏄惁浠绘剰鏈変竴涓负NULL鎴栧涓┖鏍� */
	public static boolean isAnyBlank(final String... textArray) {
		for (String s : textArray) {
			if (isBlank(s)) {
				return true;
			}
		}
		return false;
	}

	/** 鏄惁閮戒笉涓虹┖鏍� */
	public static boolean isNotBlank(final String... textArray) {
		return !isBlank(textArray);
	}

	/**
	 * 鏄惁閮戒负null鎴�""
	 * <pre>
	 * isEmpty(null) = true;
	 * isEmpty(&quot;&quot;) = true;
	 * isEmpty(&quot; &quot;) = false;
	 * </pre>
	 */
	public static boolean isAllEmpty(final CharSequence... textArray) {
		for (CharSequence s : textArray) {
			if (s != null && s.length() > 0) {
				return false;
			}
		}
		return true;
	}

	/** 鏄惁浠绘剰鏈変竴涓负绌� */
	public static boolean isAnyEmpty(final CharSequence... textArray) {
		for (CharSequence s : textArray) {
			if (isAllEmpty(s)) {
				return true;
			}
		}
		return false;
	}

	/** 鏄惁閮戒笉涓簄ull鍜�"" */
	public static boolean isNotEmpty(final CharSequence... textArray) {
		for (CharSequence s : textArray) {
			if (s == null || s.length() == 0) {
				return false;
			}
		}
		return true;
	}

	/** 鏄惁閮戒负null */
	public static boolean isAllNull(final CharSequence... textArray) {
		for (CharSequence s : textArray) {
			if (s != null) {
				return false;
			}
		}
		return true;
	}

	/** 浠绘剰涓�涓猲ull鍒欎负鐪� */
	public static boolean isAnyNull(final CharSequence... textArray) {
		for (CharSequence s : textArray) {
			if (s == null) {
				return true;
			}
		}
		return false;
	}

	//
	// ========================== 鏄惁绌烘牸 ========================= //
	//

	/** 鏄惁绌烘牸鎴栬繛缁殑绌烘牸 */
	public static boolean isWhitespace(String input) {
		if (input == null) { return false; }
		int sz = input.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isWhitespace(input.charAt(i))) { return false; }
		}
		return true;
	}

	/** 鏄惁鍏ㄦ槸鏁板瓧 */
	public static boolean isNumeric(String input) {
		if (input == null) { return false; }
		int sz = input.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isDigit(input.charAt(i))) { return false; }
		}
		return true;
	}

	/** 鏄惁鍏ㄦ槸瀛楁瘝 */
	public static boolean isLetter(String input) {
		if (input == null) { return false; }
		int sz = input.length();
		for (int i = 0; i < sz; i++) {
			if (!Character.isLetter(input.charAt(i))) { return false; }
		}
		return true;
	}

	//
	// ========================== 鐩哥瓑鍒ゆ柇 ========================= //
	//

	/** 鏄惁鐩哥瓑锛屽尯鍒嗗ぇ灏忓啓 */
	public static boolean isEquals(final String str1, final String str2) {
		return str1 == null ? str2 == null : str1.equals(str2);
	}

	/** 鏄惁鐩哥瓑锛屼笉鍖哄垎澶у皬鍐� */
	public static boolean isEqualsIgnoreCase(final String str1, final String str2) {
		return str1 == null ? str2 == null : str1.equalsIgnoreCase(str2);
	}

	/** 绗竴涓弬鏁颁笌鍚庨潰鍙傛暟鐩告瘮杈冿紝鍙浠绘剰鐩哥瓑灏辫繑鍥炵湡 */
	public static boolean isAnyEqualsIgnoreCase(final String sourceStr, final String... values) {
		if (sourceStr == null || values == null) {
			return false;
		}
		for (String value : values) {
			if (isEqualsIgnoreCase(sourceStr, value)) {
				return true;
			}
		}
		return false;
	}

	//
	// ========================== 鍖呭惈鍒ゆ柇 ========================= //
	//

	/** 瀛楃涓插紑濮嬬浉绛� */
	public static boolean startsWith(String sourceText, String prefix) {
		sourceText = StringUtils.trim(sourceText);
		return sourceText.startsWith(prefix);
	}

	/** 瀛楃涓茬粨灏剧浉绛� */
	public static boolean endsWith(String sourceText, String suffix) {
		sourceText = StringUtils.trim(sourceText);
		return sourceText.endsWith(suffix);
	}

	/** 鏄惁鍏ㄩ儴鍖呭惈 */
	public static boolean isContains(String sourceText, String... textArray) {
		sourceText = StringUtils.trim(sourceText);
		for (CharSequence s : textArray) {
			if (!sourceText.contains(s)) {
				return false;
			}
		}
		return true;
	}

	/** 鏄惁浠绘剰涓�涓寘鍚� */
	public static boolean isAnyContains(String sourceText, String... textArray) {
		sourceText = StringUtils.trim(sourceText);
		for (CharSequence s : textArray) {
			if (sourceText.contains(s)) {
				return true;
			}
		}
		return false;
	}

	/** 鏄惁閮戒笉鍖呭惈 */
	public static boolean isNotContains(String sourceText, String... textArray) {
		return isContains(sourceText, textArray);
	}
}
