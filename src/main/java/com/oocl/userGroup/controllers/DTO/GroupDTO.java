package com.oocl.userGroup.controllers.DTO;

import com.oocl.userGroup.entities.Group;
import com.oocl.userGroup.entities.User;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class GroupDTO {
    private final Long id;
    private final String name;
    private final ZonedDateTime create_date;
    private final List<Long> usersId;

    public GroupDTO(Group group){
        this.id = group.getId();
        this.name = group.getName();
        this.create_date = group.getCreate_date();
        this.usersId = group.getUsers().stream().map(user -> user.getId()).collect(Collectors.toList());
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

    public List<Long> getUsersId() {
        return usersId;
    }
}
