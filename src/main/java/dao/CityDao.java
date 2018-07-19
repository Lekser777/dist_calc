package dao;

import models.City;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
@Stateless
public class CityDao {

    @PersistenceContext(unitName = "distance-calculator")
    private EntityManager em;

    public List<City> findAll(){
        return  em.createQuery("select c from City c").getResultList();
    }
    public Object findByName(String name){
        return em.createQuery("select c from City c where c.name= :c_name").setParameter("c_name",name).getSingleResult();
    }

    public void saveCity(City city){
        em.persist(city);
    }
}
