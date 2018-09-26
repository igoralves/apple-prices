package com.igor.service;

import com.igor.xml.Countries;
import com.igor.xml.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

@Repository
public class CountryRepository {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryRepository.class);

    private static final String COUNTRIES_FILE = "countries.xml";

    private final Map<String, Country> countriesMap = new HashMap<>();

    public CountryRepository() {

        final ClassLoader classLoader = CountryRepository.class.getClassLoader();
        final InputStream inputStream = classLoader.getResourceAsStream(COUNTRIES_FILE);

        try {

            final JAXBContext jaxbContext = JAXBContext.newInstance(Countries.class);
            final Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            final Countries countries = (Countries) jaxbUnmarshaller.unmarshal(inputStream);

            for (Country country : countries.getCountries()) {

                LOGGER.info("Adding " + country.getName());

                countriesMap.put(country.getAppleCode(), country);
            }

        } catch (JAXBException e) {
            throw new RuntimeException(e);
        }
    }


    public Country getCountry(String countryCode) {
        return countriesMap.get(countryCode);
    }
}
