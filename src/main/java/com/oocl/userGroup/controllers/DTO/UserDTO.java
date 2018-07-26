package com.oocl.userGroup.controllers.DTO;

import com.oocl.userGroup.entities.User;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class UserDTO {
    private final Long id;
    private final String name;
    private final List<Long> groupsId;

    public UserDTO(User user){
        this.id = user.getId();
        this.name = user.getName();
        this.groupsId = user.getGroups().stream().map(group -> group.getId()).collect(Collectors.toList());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Long> getGroupsId() {
        return groupsId;
    }
}
