package com.celfocus.training.util;

import org.apache.commons.codec.binary.Hex;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class Util {
    private static final Logger logger = Logger.getLogger(Util.class.getName());

    private Util() {
    }

    private static MessageDigest SHA256;

    static {
        try {
            SHA256 = MessageDigest.getInstance("SHA-256");
        } catch (NoSuchAlgorithmException e) {
            logger.log(Level.SEVERE, "Error getting SHA-256 instance", e);
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
        String[] keyValuePairs = value.split(",");
        Map<String, String> map = new HashMap<>();

        for (String pair : keyValuePairs) {
            String[] entry = pair.split("=", 2);

            if (entry.length > 1) {
                map.put(entry[0].trim(), entry[1].trim());
            }
        }

        return map;
    }

    public static Date toDate(String date, DateFormat format) {
        Objects.requireNonNull(date);
        Objects.requireNonNull(format);

        Date result = null;
        try {
            result = format.parse(date);
        } catch (ParseException e) {
            logger.log(Level.SEVERE, "Error parsing date", e);
        }

        return result;
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
    public static <T> List<T> createListFromArray(T... arr) {
        if (arr == null) {
            return new ArrayList<>(0);
        }

        List<T> list = new ArrayList<>(arr.length);
        Collections.addAll(list, arr);

        return list;
    }

    @SafeVarargs
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> createMapFromArray(Object... arr) {
        if (arr == null) {
            return new HashMap<>(0);
        }

        if (arr.length % 2 != 0) {
            throw new IllegalArgumentException("Length should be pair");
        }

        int length = arr.length / 2;
        Map<K, V> map = new HashMap<>(length);
        for (int index = 0; index <= length; index += 2) {
            map.put((K) arr[index], (V) arr[index + 1]);
        }

        return map;
    }
}