package com.igor.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Country {

    private final Locale locale;
    private final NumberFormat numberFormat;

    public Country(Locale locale) {
        this.locale = locale;
        this.numberFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public Country(Locale locale, String customSymbol) {

        this.locale = locale;

        final DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setCurrencySymbol(customSymbol);

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        this.numberFormat = numberFormat;
    }

    public Country(Locale locale, String customSymbol, String pattern) {

        this.locale = locale;

        final DecimalFormat numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setCurrencySymbol(customSymbol);

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        numberFormat.applyPattern(pattern);

        this.numberFormat = numberFormat;
    }

    public Locale getLocale() {
        return locale;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public Currency getCurrency() {
        return Currency.getInstance(locale);
    }
}
