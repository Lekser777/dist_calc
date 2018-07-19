package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Cities {
    @XmlElementWrapper(name = "cityList")
    @XmlElement(name = "city")
    private ArrayList<City> cities;

    public void addCity(City city) {
        try {
            if (cities == null) {
                cities = new ArrayList<City>();
            }
            cities.add(city);

        } catch (Exception e) {
        }
    }

    public ArrayList<City> getCities() {
        return cities;
    }
}
