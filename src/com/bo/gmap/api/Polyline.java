
package com.bo.gmap.api;

import javax.annotation.Generated;
import com.google.gson.annotations.Expose;

@Generated("org.jsonschema2pojo")
public class Polyline {

    @Expose
    private String points;

    /**
     * 
     * @return
     *     The points
     */
    public String getPoints() {
        return points;
    }

    /**
     * 
     * @param points
     *     The points
     */
    public void setPoints(String points) {
        this.points = points;
    }

}
