package models;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class Distances {
    @XmlElementWrapper(name = "distanceList")
    @XmlElement(name = "distance")
    private ArrayList<Distance> distances;

    public void addDistance(Distance distance) {
        try {
            if (distances == null) {
                distances = new ArrayList<Distance>();
            }
            distances.add(distance);

        } catch (Exception e) {
        }
    }

    public ArrayList<Distance> getDistances() {
        return distances;
    }
}
