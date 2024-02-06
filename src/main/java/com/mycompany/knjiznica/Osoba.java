
package com.mycompany.knjiznica;


public class Osoba {
    private String lastName;
    private String firstName;
    
    public Osoba(String firstName, String lastName){
        this.firstName = firstName;
        this.lastName = lastName;
    }
    
    public String getFirstName(){
        return firstName;
    }
    public String getLastName(){
        return lastName;
    }
    public String toString(){
        return firstName + " " + lastName;
    }
}
