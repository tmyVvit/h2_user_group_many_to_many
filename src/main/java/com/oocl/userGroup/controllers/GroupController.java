package com.oocl.userGroup.controllers;

import com.oocl.userGroup.controllers.DTO.GroupDTO;
import com.oocl.userGroup.entities.Group;
import com.oocl.userGroup.entities.User;
import com.oocl.userGroup.exceptions.ResourceNotFoundException;
import com.oocl.userGroup.repositories.GroupRepository;
import com.oocl.userGroup.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.handler.UserRoleAuthorizationInterceptor;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/groups")
public class GroupController {
    private GroupRepository groupRepository;

    @Autowired
    private UserRepository userRepository;
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

    @Transactional
    @PutMapping(path = "/{groupID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO updateGroupById(@PathVariable Long groupID, @RequestBody Group group){
        Group groupFound = groupRepository.findById(groupID).orElseThrow(()->new ResourceNotFoundException("group not found"));
        groupFound.setName(group.getName());
        groupRepository.save(groupFound);
        return new GroupDTO(groupFound);
    }

    @Transactional
    @PatchMapping(path = "/{groupID}/users/{userID}", produces = MediaType.APPLICATION_JSON_VALUE)
    public GroupDTO addUserToGroup(@PathVariable Long groupID, @PathVariable Long userID) {
        Group group = groupRepository.findById(groupID).orElseThrow(()->new ResourceNotFoundException("group not found"));
        User user = userRepository.findById(userID).orElseThrow(()->new ResourceNotFoundException("user not exist"));
        group.addUser(user);

        return new GroupDTO(group);
    }
}
