package com.example.whb_demo.utils;

import com.example.whb_demo.exeception.BusinessException;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Map;

/**
 * @author zpw
 * @Description <p>参数断言</p>
 * @date 2021/10/24
 */
@NoArgsConstructor
public class Asserts {

    //--业务断言
    private static void assertArgument(boolean value, String message) {
        if (!value) {
            throw new BusinessException(message);
        }
    }

    public static void assertNotNull(Object t, String message) {
        assertArgument(t != null, message);
    }

    public static void assertNull(Object t, String message) {
        assertArgument(t == null, message);
    }

    public static void assertTrue(Boolean value, String message) {
        assertArgument(Boolean.TRUE.equals(value), message);
    }

    public static void assertFalse(Boolean value, String message) {
        assertArgument(Boolean.FALSE.equals(value), message);
    }

    public static <T extends Map<?, ?>> T assertNotEmpty(T map, String message) {
        assertArgument(map != null && !map.isEmpty(), message);
        return map;
    }

    public static <T extends Collection<?>> T assertNotEmpty(T collection, String message) {
        assertArgument(collection != null && !collection.isEmpty(), message);
        return collection;
    }

    public static String assertNotEmpty(String str, String message) {
        assertArgument(str != null && !str.isEmpty(), message);
        return str;
    }

    public static String assertNotBlank(String str, String message) {
        assertArgument(str != null && !str.isEmpty() && !str.trim().isEmpty(), message);
        return str;
    }

    public static int[] assertNotEmpty(int[] array, String message) {
        assertArgument(array != null && array.length > 0, message);
        return array;
    }

    public static <T> T[] assertNotEmpty(T[] array, String message) {
        assertArgument(array != null && array.length > 0, message);
        return array;
    }
}
