package com.mycompany.knjiznica;

public class Osoba {

    private int id;
    private String lastName;
    private String firstName;

    public Osoba(int id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public int getID() {
        return id;
    }

    public String toStringLists() {
        return firstName + " " + lastName;
    }

    public String toString() {
        return getID() + "| " + firstName + " " + lastName;
    }
}
