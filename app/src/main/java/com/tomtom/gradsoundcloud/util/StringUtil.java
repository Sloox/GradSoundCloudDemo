package com.tomtom.gradsoundcloud.util;


/**
 * StringUtil class for simple operations on Strings
 */
public class StringUtil {

    public static String checkNull(String s) {
        if (StringUtil.isNotNullAndNotEmpty(s)) {
            if (s.equals("null")) {
                return null;
            }
        }
        return s;
    }

    /**
     * Is null or empty check.
     *
     * @param line the line
     * @return the boolean
     */
    public static boolean isNullorEmpty(String line) {
        boolean flag = false;
        if (line == null || line.trim().length() == 0) {
            flag = true;
        }

        return flag;
    }

    /**
     * Is not null and not empty check.
     *
     * @param line the line
     * @return the boolean
     */
    public static boolean isNotNullAndNotEmpty(String line) {
        boolean flag = false;
        if (line != null && line.trim().length() > 0) {
            flag = true;
        }

        return flag;
    }

    /**
     * Is numeric boolean.
     *
     * @param line the line
     * @return the boolean
     */
    public static boolean isNumeric(String line) {
        boolean flag = true;
        if (isNullorEmpty(line)) {
            return false;
        } else {
            char[] arr$ = line.toCharArray();
            int len$ = arr$.length;

            for (int i$ = 0; i$ < len$; ++i$) {
                char c = arr$[i$];
                if (!Character.isDigit(c)) {
                    flag = false;
                }
            }

            return flag;
        }
    }
}
