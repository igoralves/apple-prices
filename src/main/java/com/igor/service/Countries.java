package com.igor.service;

import com.igor.model.Country;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class Countries {

    private static final Map<String, Country> COUNTRIES_MAP = new HashMap<>();

    private static final char SPACE_CHARACTER = ' ';
    private static final char NO_BREAK_SPACE_CHARACTER = ' ';

    static {
        COUNTRIES_MAP.put("at", Country.create().setLocale(new Locale("de", "AT")).setPattern("###,###.### ¤")); // Austria
        COUNTRIES_MAP.put("au", Country.create().setLocale(new Locale("en", "AU")).setCustomSymbol("A$")); // Australia
        COUNTRIES_MAP.put("be-fr", Country.create().setLocale(new Locale("fr", "BE"))); // Belgium
        COUNTRIES_MAP.put("br", Country.create().setLocale(new Locale("pt", "BR"))); // Brazil
        COUNTRIES_MAP.put("ch-de", Country.create().setLocale(new Locale("de", "CH")).setCustomSymbol("CHF")); // Switzerland
        COUNTRIES_MAP.put("cz", Country.create().setLocale(new Locale("cs", "CZ"))); // Czech Republic
        COUNTRIES_MAP.put("de", Country.create().setLocale(Locale.GERMANY)); // Germany
        COUNTRIES_MAP.put("dk", Country.create().setLocale(new Locale("da", "DK")).setCustomSymbol("kr.").setPattern("###,###.### ¤")); // Denmark
        COUNTRIES_MAP.put("es", Country.create().setLocale(new Locale("es", "es"))); // Spain
        COUNTRIES_MAP.put("fi", Country.create().setLocale(new Locale("fi", "FI"))); // Finland
        COUNTRIES_MAP.put("fr", Country.create().setLocale(Locale.FRANCE)); // France
        COUNTRIES_MAP.put("hu", Country.create().setLocale(new Locale("hu", "HU"))); // Hungary
        COUNTRIES_MAP.put("ie", Country.create().setLocale(new Locale("en", "IE"))); // Ireland
        COUNTRIES_MAP.put("it", Country.create().setLocale(Locale.ITALY)); // Italy
        COUNTRIES_MAP.put("lu", Country.create().setLocale(new Locale("fr", "LU"))); // Luxembourg
        COUNTRIES_MAP.put("nl", Country.create().setLocale(new Locale("nl", "nl"))); // Netherlands
        COUNTRIES_MAP.put("no", Country.create().setLocale(new Locale("no", "NO")).setGroupingSeparator(SPACE_CHARACTER)); // Norway
        COUNTRIES_MAP.put("pl", Country.create().setLocale(new Locale("pl", "PL"))); // Poland
        COUNTRIES_MAP.put("pt", Country.create().setLocale(new Locale("pt", "PT"))); // Portugal
        COUNTRIES_MAP.put("ru", Country.create().setLocale(new Locale("ru", "RU")).setCustomSymbol("pyб.").setGroupingSeparator(NO_BREAK_SPACE_CHARACTER).setMonetaryDecimalSeparator('.')); // Russia
        COUNTRIES_MAP.put("se", Country.create().setLocale(new Locale("sv", "SE")).setGroupingSeparator(SPACE_CHARACTER)); // Sweden
        COUNTRIES_MAP.put("tr", Country.create().setLocale(new Locale("tr", "TR"))); // Turkey
        COUNTRIES_MAP.put("uk", Country.create().setLocale(Locale.UK)); // United Kingdom
        COUNTRIES_MAP.put("us", Country.create().setLocale(Locale.US)); // United States
    }

    public Country getCountry(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }
}
