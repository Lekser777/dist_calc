package controller;

import models.Cities;
import models.City;
import models.Distance;
import models.Distances;

import javax.ejb.Stateless;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;


@Stateless
public class Converter {


    public void convertCityToXml(Cities cities, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Cities.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(cities, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }

    public List<City> convertXmlToCity(String filePath){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Cities.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            Cities cities=(Cities)un.unmarshal(new File(filePath));
            return  cities.getCities();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }

    public List<Distance> convertXmlToDistance(String filePath){
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Distances.class);
            Unmarshaller un = jaxbContext.createUnmarshaller();
            Distances distances=(Distances) un.unmarshal(new File(filePath));
            return  distances.getDistances();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
    }
    public void convertDistanceToXml(Distances distances, String filePath) {
        try {
            JAXBContext context = JAXBContext.newInstance(Distances.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            marshaller.marshal(distances, new File(filePath));
        } catch (JAXBException e) {
            e.printStackTrace();
        }
    }




}
