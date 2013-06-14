package com.sample.domain;

import org.apache.solr.client.solrj.beans.Field;

public class Core0 {

    @Field
    private String id;
    @Field
    private String name;
    
    public Core0() {
    }
    public Core0(String id, String name) {
        super();
        this.id = id;
        this.name = name;
    }
    
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
}
