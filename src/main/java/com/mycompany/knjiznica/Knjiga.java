package com.mycompany.knjiznica;

public class Knjiga {

    private int id;
    private int ISBN;
    private String name;
    private String pisac;

    public Knjiga(int id, int ISBN, String name, String writer) {
        this.ISBN = ISBN;
        this.id = id;
        this.name = name;
        this.pisac = writer;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getWriter() {
        return pisac;
    }

    public int getISBN() {
        return ISBN;
    }

    public String toString() {
        return getId() + "| " + getISBN() + " " + getName() + " - " + getWriter();
    }
}
