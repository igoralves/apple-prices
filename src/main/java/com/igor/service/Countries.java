package com.igor.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Countries {

    private static final Map<String, Locale> COUNTRIES_MAP = new HashMap<>();

    static {
        COUNTRIES_MAP.put("br", new Locale("pt", "BR"));
        COUNTRIES_MAP.put("pl", new Locale("pl", "PL"));
        COUNTRIES_MAP.put("us", Locale.US);
    }

    public Locale getLocale(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }
}
