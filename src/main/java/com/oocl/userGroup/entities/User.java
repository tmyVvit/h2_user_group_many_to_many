package com.oocl.userGroup.entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Table(name = "user")
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @JsonIgnore
    @ManyToMany(mappedBy = "users", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private List<Group> groups = new ArrayList<>();

    public User(Long id, String name) {
        this.id = id;
        this.name = name;
    }


    public User() {
    }

    public void setGroups(List<Group> groups) {
        this.groups = groups;
    }

    public List<Group> getGroups() {
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

    public void deleteGroup(Long groupID) {
        for(Group group: groups){
            if(group.getId().equals(groupID)){
                groups.remove(group);
                break;
            }
        }
    }
}
