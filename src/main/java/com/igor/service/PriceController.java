package com.igor.service;

import com.gargoylesoftware.htmlunit.WebClient;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

public abstract class PriceController {

    protected WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }

    protected Float parsePrice(String priceString) throws ParseException {

        final NumberFormat numberFormat = NumberFormat.getCurrencyInstance(getLocale());

        return numberFormat.parse(priceString.trim()).floatValue();
    }

    protected abstract String getURL();

    protected abstract Locale getLocale();
}
