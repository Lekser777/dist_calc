package controller;



import dao.CityDao;
import dao.DistanceDao;
import models.City;
import models.Distance;


import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.List;

@Path("/calc")
public class CalcService {
    @EJB
    private CityDao cityDao;
    @EJB
    private Calculator calculator;
    @EJB
    private DistanceDao distanceDao;
    @EJB
    private Converter converter;

    @GET
    @Path("/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public List<City> getAllCities(){
        List<City> cities= cityDao.findAll();
        return cities;
    }

    private List<String> getCrowMethod(List<String> fromList, List<String> toList){
        List<String> resultList=new ArrayList<String>();
        for (String aFromList : fromList) {
            for (int j = 0; j < toList.size(); j++) {
                try {
                    double dist = calculator.crowFlight((City) cityDao.findByName(aFromList), (City) cityDao.findByName(fromList.get(j)));
                    resultList.add("Type = CrowFlight From " + aFromList + " To " + toList.get(j) + " Distance = " + dist);
                } catch (Exception e) {
                    resultList.add("Type = CrowFlight From " + aFromList + " To " + toList.get(j) + " Distance is not found");
                }
            }
        }
        return resultList;
    }

    private List<String>  getMatrixMethod(List<String> fromList,List<String> toList){
        List<String> resultList=new ArrayList<String>();
        for (String aFromList : fromList) {
            for (String aToList : toList) {
                try {
                    Distance distance=(Distance) distanceDao.findDist(aFromList, aToList);
                    resultList.add("Type = Matrix From " + aFromList + " To " + aToList + " Distance = " + String.valueOf(distance.getDistance()));
                } catch (Exception e) {
                    try {
                        Distance distance=(Distance) distanceDao.findDist(aToList, aFromList);
                        resultList.add("Type = Matrix From " + aFromList + " To " + aToList + " Distance = " +String.valueOf(distance.getDistance()));
                    }catch (Exception ee) {
                        resultList.add("Type = Matrix From " + aFromList + " To " + aToList + " Distance is not found");
                    }
                }
            }
        }
        return resultList;
    }

    @GET
    @Path("/dists")
    @Produces(MediaType.TEXT_PLAIN)
    public List<String>  getAllDistances(@QueryParam("crow") boolean crow,@QueryParam("matrix") boolean matrix,
                                         @QueryParam("from") List<String> fromList,
                                         @QueryParam("to") List<String> toList){
        List<String>resultList=new ArrayList<String>();

        if(crow){
           resultList.addAll(getCrowMethod(fromList,toList));
        }

        if(matrix){
           resultList.addAll(getMatrixMethod(fromList,toList));
        }
        return resultList;
    }

    @POST
    @Path("/upload/cities")
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadCities(@QueryParam("path") String path){
        //C:\\Users\\Глеб\\IdeaProjects\\untitled2\\dist_calc\\src\\main\\resources\\xmltest\\xmltest.xml
        List<City> cities=converter.convertXmlToCity(path);
        for (City city : cities) {
            try {
                cityDao.saveCity(city);
            } catch (Exception e) {
                return "Error";
            }
        }
        return "Saved";
    }

    @POST
    @Path("/upload/dists")
    @Produces(MediaType.APPLICATION_JSON)
    public String uploadDistances(@QueryParam("path") String path){
        //C:\\Users\\Глеб\\IdeaProjects\\untitled2\\dist_calc\\src\\main\\resources\\xmltest\\xmltest2.xml
        List<Distance> distances=converter.convertXmlToDistance(path);
        for (Distance distance : distances) {
            try {
                distanceDao.saveDistance(distance);
            } catch (Exception e) {
                return "Error";
            }
        }
        return "Saved";
    }



}
