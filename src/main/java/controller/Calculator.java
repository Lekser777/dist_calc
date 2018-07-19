package controller;


import models.City;
import javax.ejb.Stateless;

@Stateless
public class Calculator {


    public double crowFlight(City c1,City c2){
        double r=Math.toRadians(6371);
        double lat1=Math.toRadians(c1.getLatitude());
        double lon1=Math.toRadians(c1.getLongitude());
        double lat2=Math.toRadians(c2.getLatitude());
        double lon2=Math.toRadians(c2.getLongitude());
        double dLon=Math.toRadians(lon2-lon1);
        double a=Math.acos(Math.sin(lat1)*Math.sin(lat2)+Math.cos(lat1)*Math.cos(lat2)*Math.cos(dLon));
        return r*a;
    }




}
