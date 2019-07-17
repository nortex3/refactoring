package com.celfocus.training.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class Utils {

    private static MessageDigest SHA256;
    
    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }
    
    public static String toHexStringSHA256(String source, Charset charset) {
        return Hex.encodeHexString(toSHA256(source.getBytes(charset)));
    }

    private static byte[] toSHA256(byte[] bytes) {
        return SHA256.digest(bytes);
    }

    public static boolean isNullOrEmpty(String str) {
		return str == null || str.isEmpty();
    }
    
    public static Map<String, String> parseHTTPHeaderMap(String headers) {
        String value = headers.substring(1, headers.length() - 1);
        String[] keyValuePairs = value.split(",");              //split the string to creat key-value pairs
        Map<String, String> map = new HashMap<>();

        for (String pair : keyValuePairs)                        //iterate over the pairs
        {
            String[] entry = pair.split("=", 2);                   //split the pairs to get key and value

            if (entry.length > 1) {

                map.put(entry[0].trim(), entry[1].trim());          //add them to the hashmap and trim whitespaces
            }

        }
        return map;
    }

    public static LocalDate toDate(String date, DateTimeFormatter format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);
        try {
            return LocalDate.parse(date, format);
        } catch (DateTimeParseException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(Date date, String format) {
        return toString(date, new SimpleDateFormat(format));
    }

    private static String toString(Date date, DateFormat format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);
        return format.format(date);
    }

    public static <T> boolean isNullOrEmpty(List<T> list) {
        return list == null || list.isEmpty();
    }

    @SafeVarargs
    public static <T> java.util.List<T> createListFromArray(T... ts) {
        if (ts == null) {
            return new ArrayList<>(0);
        }
        java.util.List<T> list = new ArrayList<>(ts.length);
        for (T t : ts) {
            list.add(t);
        }
        return list;
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> createMapFromArray(Object... ts) {
        if (ts == null) {
            return new HashMap<>(0);
        }
        if (ts.length % 2 != 0) {
            throw new IllegalArgumentException("Length should be pair");
        }
        int length = ts.length / 2;
        Map<K, V> map = new HashMap<K, V>(length);
        for (int index = 0; index <= length; index+=2) {
            map.put((K) ts[index], (V) ts[index + 1]);
        }
        return map;
    }


}