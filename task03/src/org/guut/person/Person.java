package org.guut.person;


import java.io.Serializable;

/**
 * The person class contains the name and the birthday of a person
 */
public class Person implements Serializable{
    private String name;
    private String dob;

    /**
     * Creates a new person with name and birthday
     * @param name the name of the person
     * @param dob the date of birth of the person
     */
    public Person(String name, String dob) {
        this.name = name;
        if(dob != null)
            this.dob = dob;
        else
            this.dob = "01.01.70";
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return getName();
    }
}
