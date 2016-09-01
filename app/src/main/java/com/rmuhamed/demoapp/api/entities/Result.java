
package com.rmuhamed.demoapp.api.entities;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rmuhamed.demoapp.model.Entity;

public class Result {

    @SerializedName("entity")
    @Expose
    private Entity entity;
    @SerializedName("seed")
    @Expose
    private String seed;

    /**
     * 
     * @return
     *     The entity
     */
    public Entity getEntity() {
        return entity;
    }

    /**
     * 
     * @param entity
     *     The entity
     */
    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    /**
     * 
     * @return
     *     The seed
     */
    public String getSeed() {
        return seed;
    }

    /**
     * 
     * @param seed
     *     The seed
     */
    public void setSeed(String seed) {
        this.seed = seed;
    }

}
