package com.camel.person.valueholders;

import java.util.Date;

import com.camel.common.object.BaseObject;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "person")
public class Person extends BaseObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, length = 10)
    private int id;
    @Column(name = "fname", nullable=false, length = 100)
    private String firstName;
    @Column(name = "lname", nullable=false, length = 100)
    private String lastName;
    @Column(name = "mname", nullable=false, length = 100)
    private String middleName;
    @Column(name = "dob")
    private Date dateOfBirty;
    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    private Gender gender;
    
    public Person(){}

    public int getId() {
        return id;
    }


    public void setId(int id) {
        this.id = id;
    }


    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }


    public String getMiddleName() {
        return middleName;
    }


    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }


    public Date getDateOfBirty() {
        return dateOfBirty;
    }


    public void setDateOfBirty(Date dateOfBirty) {
        this.dateOfBirty = dateOfBirty;
    }


    public Gender getGender() {
        return gender;
    }


    public void setGender(Gender gender) {
        this.gender = gender;
    }


    public enum Gender {

    FEMALE("female", "Female"),
    MALE("male", "Male"),
    OTHER("other", "Other");

    private final String name;
    private final String display;

    Gender(String name, String display) {
        this.name = name;
        this.display = display;
    }

    public String getName() {
        return name;
    }

    public String getDisplay() {
        return display;
    }

  
}

  public String getFullName() {

    StringBuilder fullName = new StringBuilder();

    if (firstName != null) {
        fullName.append(firstName);
    }

    if (middleName != null && !middleName.isBlank()) {
        if (fullName.length() > 0) {
            fullName.append(" ");
        }
        fullName.append(middleName);
    }

    if (lastName != null && !lastName.isBlank()) {
        if (fullName.length() > 0) {
            fullName.append(" ");
        }
        fullName.append(lastName);
    }

    return fullName.toString();
}

  @Override
  public String toString() {
    return "Person [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", middleName=" + middleName
            + ", dateOfBirty=" + dateOfBirty + ", gender=" + gender + "]";
  }


    
}
