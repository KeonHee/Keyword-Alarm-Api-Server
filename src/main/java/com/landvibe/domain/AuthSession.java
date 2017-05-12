package com.landvibe.domain;


import javax.persistence.*;

/**
 * Created by user on 2017-05-13.
 */
@Entity
public class AuthSession {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String session;

    public AuthSession(){}

    public AuthSession(String session){
        this.session=session;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSession() {
        return session;
    }

    public void setSession(String session) {
        this.session = session;
    }
}
