package com.rangga.phonebook.service;

import com.rangga.phonebook.dto.GroupDTO;
import com.rangga.phonebook.dto.PhonebookFullDTO;
import com.rangga.phonebook.model.GroupModel;
import com.rangga.phonebook.model.PhoneBookModel;
import com.rangga.phonebook.repository.GroupRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public GroupModel createGroup(GroupModel model) {
        GroupModel groupModel = new GroupModel();
        groupModel.setNameGroup(model.getNameGroup());
        groupModel.setCreatedAt(LocalDateTime.now());
        return groupRepository.save(groupModel);
    }

    public GroupModel updateGroup(GroupModel model) {
        Optional<GroupModel> findById = groupRepository.findById(model.getGroupId());
        if (findById.isEmpty()) {
            GroupModel groupModel = new GroupModel();
            groupModel.setNameGroup(model.getNameGroup());
            groupModel.setUpdatedAt(LocalDateTime.now());
            return groupRepository.save(groupModel);
        }
        else{
            return null;
        }
    }

    public List<GroupDTO> getAllGroup(){
        return groupRepository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public Slice<GroupDTO> searchGroup(String name, int page) {
        Pageable pagination = PageRequest.of(page, 5, Sort.by("createdBy").ascending());
        return groupRepository.searchGroup(name, pagination).map(this::convertDTO);
    }

    public boolean deleteGroup(int id) {
        try {
            Optional<GroupModel> groupModel = groupRepository.findById(id);
            groupModel.ifPresent(groupModels -> {
                groupRepository.delete(groupModels);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private GroupDTO convertDTO(GroupModel item) {
        GroupDTO groupDTO = new GroupDTO();
        groupDTO.setGroupName(item.getNameGroup());
        return groupDTO;
    }
}
