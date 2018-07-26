package com.oocl.userGroup.controllers;

import com.oocl.userGroup.controllers.DTO.GroupDTO;
import com.oocl.userGroup.entities.Group;
import com.oocl.userGroup.exceptions.ResourceNotFoundException;
import com.oocl.userGroup.repositories.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

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

    @Transactional
    @GetMapping(path = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<GroupDTO> getAllGroups(){
        List<GroupDTO> groupDTOS = new ArrayList<>();
        groupRepository.findAll().forEach(group -> groupDTOS.add(new GroupDTO(group)));
        return groupDTOS;
    }

    @Transactional
    @GetMapping(path = "/{groupID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO getGroupById(@PathVariable Long groupID){
        Group group = groupRepository.findById(groupID).orElseThrow(()->new ResourceNotFoundException("group not found"));
        return new GroupDTO(group);
    }
}
