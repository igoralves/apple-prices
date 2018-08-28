package com.igor.service;

import com.igor.model.Country;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Countries {

    private static final Map<String, Country> COUNTRIES_MAP = new HashMap<>();

    static {
        COUNTRIES_MAP.put("au", new Country(new Locale("en", "AU"), "A$")); // Australia
        COUNTRIES_MAP.put("be-fr", new Country(new Locale("fr", "BE"))); // Belgium
        COUNTRIES_MAP.put("br", new Country(new Locale("pt", "BR"))); // Brazil
        COUNTRIES_MAP.put("cz", new Country(new Locale("cs", "CZ"))); // Czech Republic
        COUNTRIES_MAP.put("de", new Country(Locale.GERMANY)); // Germany
        COUNTRIES_MAP.put("dk", new Country(new Locale("da", "DK"), "kr.", "###,###.### ¤")); // Denmark
        COUNTRIES_MAP.put("es", new Country(new Locale("es", "es"))); // Spain
        COUNTRIES_MAP.put("fr", new Country(Locale.FRANCE)); // Hungary
        COUNTRIES_MAP.put("hu", new Country(new Locale("hu", "HU"))); // Hungary
        COUNTRIES_MAP.put("nl", new Country(new Locale("nl", "nl"))); // Netherlands
        COUNTRIES_MAP.put("pl", new Country(new Locale("pl", "PL"))); // Poland
        COUNTRIES_MAP.put("us", new Country(Locale.US)); // United States
    }

    public Country getCountry(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }
}
