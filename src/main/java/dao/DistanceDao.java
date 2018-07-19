package dao;

import models.Distance;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


@Stateless
public class DistanceDao {
    @PersistenceContext(unitName = "distance-calculator")
    private EntityManager em;

    public Object findDist(String from,String to){
        return em.createQuery("select d from Distance d where d.fromCity = :from_c " +
                "and d.toCity = :to_c").setParameter("from_c",from).setParameter("to_c",to).getSingleResult();
    }

    public void saveDistance(Distance distance){
        em.persist(distance);
    }

}
