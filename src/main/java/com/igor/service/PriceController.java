package com.igor.service;

import com.gargoylesoftware.htmlunit.WebClient;

public abstract class PriceController {

    protected WebClient getClient() {

        final WebClient client = new WebClient();

        client.getOptions().setCssEnabled(false);
        client.getOptions().setJavaScriptEnabled(false);

        return client;
    }


}
