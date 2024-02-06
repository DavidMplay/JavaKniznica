package com.mycompany.knjiznica;

public class Knjiga {

    private int id;
    private String name;

    public Knjiga(int id, String name) {
        this.id = id;
        this.name = name;
    }
    
    public int getId(){
        return id;
    }
    public String getName(){
        return name;
    }
    
    public String toString(){
        return getId() + " " + getName();
    }
}
