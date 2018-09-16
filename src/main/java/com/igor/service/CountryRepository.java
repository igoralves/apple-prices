package com.igor.service;

import com.igor.xml.Countries;
import com.igor.xml.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class CountryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepository.class);

    private static final String COUNTRIES_FILE = "countries.xml";

    private static final Map<String, Country> COUNTRIES_MAP = new HashMap<>();

    static {

        final ClassLoader classLoader = CountryRepository.class.getClassLoader();
        final File file = new File(classLoader.getResource(COUNTRIES_FILE).getFile());

        try {

            final JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final Countries countries = (Countries) jaxbUnmarshaller.unmarshal(file);

            for (Country country : countries.getCountries()) {

                LOGGER.info("Adding " + country.getName());

                COUNTRIES_MAP.put(country.getAppleCode(), country);
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }

    public Country getCountry(String countryCode) {
        return COUNTRIES_MAP.get(countryCode);
    }
}
