package com.example.CRUDapplication.models;

import jakarta.persistence.*;


// we will create, update and delete a user
// here a basic model with all its fields is defined
@Entity //since it is our table also
@Table(name = "Users")// name of our table
public class Users {
    //fields
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private String name;

    // Constructors
    public Users() {
        // Default constructor
    }

    public Users(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    // Getter for id
    public Long getId() {
        return id;
    }

    // Setter for id
    public void setId(Long id) {
        this.id = id;
    }

    // Getter for name
    public String getName() {
        return name;
    }

    // Setter for name
    public void setName(String name) {
        this.name = name;
    }

}
