package com.igor.xml;

import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.Locale;

@XmlRootElement
public class Country {

    @XmlElement
    private String name;

    @XmlElement
    private String appleCode;

    @XmlElement
    private String language;

    @XmlElement
    private String countryCode;

    @XmlElement
    private String customSymbol;

    @XmlElement
    private String groupingSeparator;

    @XmlElement
    private String monetaryDecimalSeparator;

    @XmlElement
    private String pattern;

    private Locale locale;
    private DecimalFormat numberFormat;

    void afterUnmarshal(Unmarshaller unmarshaller, Object parent) {

        locale = new Locale(language, countryCode);
        numberFormat = (DecimalFormat) NumberFormat.getCurrencyInstance(locale);

        configureCustomSymbol();

        configureGroupingSeparator();

        configureMonetaryDecimalSeparator();

        configurePattern();
    }

    private void configurePattern() {
        if (pattern != null) {
            numberFormat.applyPattern(pattern);
        }
    }

    private void configureMonetaryDecimalSeparator() {

        if (monetaryDecimalSeparator != null) {

            final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

            decimalFormatSymbols.setMonetaryDecimalSeparator(monetaryDecimalSeparator.charAt(0));

            numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        }
    }

    private void configureGroupingSeparator() {

        if (groupingSeparator != null) {

            final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

            decimalFormatSymbols.setGroupingSeparator(groupingSeparator.charAt(0));

            numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        }
    }

    private void configureCustomSymbol() {

        if (customSymbol != null) {

            final DecimalFormatSymbols decimalFormatSymbols = numberFormat.getDecimalFormatSymbols();

            decimalFormatSymbols.setCurrencySymbol(customSymbol);

            numberFormat.setDecimalFormatSymbols(decimalFormatSymbols);
        }
    }

    public String getName() {
        return name;
    }

    public String getAppleCode() {
        return appleCode;
    }

    public Currency getCurrency() {
        return Currency.getInstance(locale);
    }

    public NumberFormat getNumberFormat() {
        return numberFormat;
    }
}
