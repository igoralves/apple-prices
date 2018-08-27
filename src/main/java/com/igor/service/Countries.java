package com.igor.service;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Countries {

    private static final Map<String, Locale> COUNTRIES_MAP = new HashMap<>();

    static {
        COUNTRIES_MAP.put("be-fr", new Locale("fr", "BE")); // Belgium
        COUNTRIES_MAP.put("br", new Locale("pt", "BR")); // Brazil
        COUNTRIES_MAP.put("cz", new Locale("cs", "CZ")); // Czech Republic
        COUNTRIES_MAP.put("hu", new Locale("hu", "HU")); // Hungary
        COUNTRIES_MAP.put("nl", new Locale("nl", "nl")); // Netherlands
        COUNTRIES_MAP.put("pl", new Locale("pl", "PL")); // Poland
        COUNTRIES_MAP.put("us", Locale.US); // United States
    }

    public Locale getLocale(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }
}
