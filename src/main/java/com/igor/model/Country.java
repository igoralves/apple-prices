package com.igor.model;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
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

    public Locale getLocale() {
        return locale;
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }

    public Currency getCurrency() {
        return Currency.getInstance(locale);
    }

    public static void main(String[] args) throws ParseException {

        final Country country = new Country(new Locale("en", "AU"));

        final DecimalFormat numberFormat = (DecimalFormat) country.getNumberFormat();

        final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

        decimalFormatSymbols.setCurrencySymbol("A$");

        numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);

        System.out.println(numberFormat.format(469));



        final float v = numberFormat.parse("A$469.00").floatValue();

        System.out.print(v);

    }


}
