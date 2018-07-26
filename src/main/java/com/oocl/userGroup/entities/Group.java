package com.oocl.userGroup.entities;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;


@Table(name = "ooclgroup")
@Entity
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @CreatedDate
    private ZonedDateTime create_date = ZonedDateTime.now();

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_group",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "group_id")})
    private Set<User> users = new HashSet<>();

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Group(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Group(){}


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

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(ZonedDateTime create_date) {
        this.create_date = create_date;
    }

}
