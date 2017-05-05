package com.tomtom.gradsoundcloud.util;


/**
 * IntegerUtil class for simple operations on Integer & Conversions on them
 */
public class IntegerUtil {

    public static Integer getIntFromString(String s){
        try {
            return Integer.valueOf(s);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
