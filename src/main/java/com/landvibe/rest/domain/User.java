package com.landvibe.rest.domain;

import javax.persistence.*;

/**
 * Created by user on 2017-05-01.
 */
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String name;

    @Column
    private String userId;

    public User(){

    }

    public User(String name, String userId){
        this.name=name;
        this.userId=userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}