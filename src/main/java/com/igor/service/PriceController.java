package com.igor.service;

import com.gargoylesoftware.htmlunit.WebClient;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class PriceController {

    private CountryRepository countryRepository;

    protected WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }

    @Autowired
    public CountryRepository getCountryRepository() {
        return countryRepository;
    }
}
