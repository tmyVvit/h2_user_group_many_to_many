package com.oocl.userGroup.controllers;

import com.oocl.userGroup.controllers.DTO.GroupDTO;
import com.oocl.userGroup.entities.Group;
import com.oocl.userGroup.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.transaction.Transactional;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private GroupRepository groupRepository;

    @Autowired
    public GroupController(GroupRepository groupRepository){
        this.groupRepository = groupRepository;
    }

    @Transactional
    @PostMapping(path="", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO addGroup(@RequestBody Group group){
        groupRepository.save(group);
        return new GroupDTO(group);
    }
}
