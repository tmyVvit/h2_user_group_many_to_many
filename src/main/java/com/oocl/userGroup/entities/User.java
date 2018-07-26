package com.oocl.userGroup.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<Group> groups = new HashSet<>();

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public User() {
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
