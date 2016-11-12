
package com.rmuhamed.demoapp.api.wsresponsemodel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.rmuhamed.demoapp.model.User;

import java.util.List;

public class Result {

    @SerializedName("users")
    @Expose
    private List<User> users;
    @SerializedName("indo")
    @Expose
    private Info info;

    /**
     * 
     * @return
     *     The users
     */
    public List<User> getUsers() {
        return users;
    }

    /**
     * 
     * @param users
     *     The users list
     */
    public void setUsers(List<User> users) {
        this.users = users;
    }

}
