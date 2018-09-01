package com.igor.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

public class Country {

    private Locale locale;
    private DecimalFormat numberFormat;

    public static Country create() {
        return new Country();
    }

    private Country() {
        // default constructor
    }

    public Country setLocale(Locale locale) {

        this.locale = locale;
        this.numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);

        return this;
    }

    public Country setCustomSymbol(String customSymbol) {

        if (numberFormat == null) {
            throw new NullPointerException("The attribute Country.numberFormat is null. Please use the method " +
                    "'setLocale(Locale)' before invoking this method.");
        }

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setCurrencySymbol(customSymbol);

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        return this;
    }

    public Country setGroupingSeparator(Character groupingSeparator) {

        if (numberFormat == null) {
            throw new NullPointerException("The attribute Country.numberFormat is null. Please use the method " +
                    "'setLocale(Locale)' before invoking this method.");
        }

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setGroupingSeparator(groupingSeparator);

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        return this;
    }

    public Country setMonetaryDecimalSeparator(Character decimalSeparator) {

        if (numberFormat == null) {
            throw new NullPointerException("The attribute Country.numberFormat is null. Please use the method " +
                    "'setLocale(Locale)' before invoking this method.");
        }

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setMonetaryDecimalSeparator(decimalSeparator);

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        return this;
    }

    public Country setPattern(String pattern) {

        if (numberFormat == null) {
            throw new NullPointerException("The attribute Country.numberFormat is null. Please use the method " +
                    "'setLocale(Locale)' before invoking this method.");
        }

        numberFormat.applyPattern(pattern);

        return this;
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
