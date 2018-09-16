package com.igor.xml;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "countries")
public class Countries {

    @XmlElement(name = "country")
    private List<Country> countries;

    public List<Country> getCountries() {
        return countries;
    }
}
