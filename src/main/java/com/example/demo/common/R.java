package com.example.demo.common;

import java.util.HashMap;

public class R extends HashMap<String,Object>{

    private static final String CODE = "code";
    private final static String MESSAGE = "message";
    private final static String VLAUE = "value";

    private static final String SUCCESS_CODE = "200";
    private static final String ERROR_CODE = "500";
    private final static String SUCCESS_MSG = "success";
    private final static String ERROR_MSG = "error";

    private R(boolean success) {
        if (success) {
            put(CODE, SUCCESS_CODE);
            put(MESSAGE, SUCCESS_MSG);
        } else {
            put(CODE, ERROR_CODE);
            put(MESSAGE, ERROR_MSG);
        }
    }

    public static R ok() {
        return new R(true);
    }
    public static R ok(Object value) {
        R r = new R(true);
        r.put(VLAUE, value);
        return r;
    }

    public static R ok(String msg ,Object value) {
        R r = new R(true);
        r.put(VLAUE, value);
        r.put(MESSAGE, msg);
        return r;
    }

    public static R error() {
        return new R(false);
    }

    public static R error(String msg) {
        R r = new R(false);
        r.put(MESSAGE, msg);
        return r;
    }

}
