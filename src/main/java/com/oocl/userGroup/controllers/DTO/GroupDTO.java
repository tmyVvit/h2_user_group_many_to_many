package com.oocl.userGroup.controllers.DTO;

import com.oocl.userGroup.entities.Group;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GroupDTO {
    private final Long id;
    private final String name;
    private final ZonedDateTime create_date;
    private final List<UserDTO> users;

    public GroupDTO(Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.create_date = group.getCreate_date();
        this.users = group.getUsers().stream().map(user -> new UserDTO(user)).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public ZonedDateTime getCreate_date() {
        return create_date;
    }

    public List<UserDTO> getUsers() {
        return users;
    }
}
