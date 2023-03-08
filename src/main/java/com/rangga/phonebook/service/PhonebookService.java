package com.rangga.phonebook.service;

import com.rangga.phonebook.dto.GroupDTO;
import com.rangga.phonebook.dto.PhonebookFullDTO;
import com.rangga.phonebook.model.GroupModel;
import com.rangga.phonebook.model.PhoneBookModel;
import com.rangga.phonebook.repository.GroupRepository;
import com.rangga.phonebook.repository.PhonebookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PhonebookService {
    @Autowired
    private PhonebookRepository phonebookRepository;

    @Autowired
    private GroupRepository groupRepository;

    public static final int PHONEBOOK_COUNT_PAGE = 10;

    public PhoneBookModel createPhonebook(PhoneBookModel model) {
        PhoneBookModel phoneBookModel = new PhoneBookModel();
        phoneBookModel.setName(model.getName());
        phoneBookModel.setPhoneNumber(model.getPhoneNumber());
        phoneBookModel.setGroupId(model.getGroupId());
        phoneBookModel.setCreatedAt(LocalDateTime.now());
        return phonebookRepository.save(phoneBookModel);
    }

    public PhoneBookModel updatePhonebook(PhoneBookModel model) {
        Optional<PhoneBookModel> findById = phonebookRepository.findById(model.getPhonebookId());
        if (findById.isEmpty()) {
            PhoneBookModel phoneBookModel = new PhoneBookModel();
            phoneBookModel.setName(model.getName());
            phoneBookModel.setPhoneNumber(model.getPhoneNumber());
            phoneBookModel.setGroupId(model.getGroupId());
            phoneBookModel.setUpdatedAt(LocalDateTime.now());
            return phonebookRepository.save(phoneBookModel);
        }
        else{
            return null;
        }
    }

    public List<PhonebookFullDTO> getAll(){
        return phonebookRepository.findAll().stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public List<PhonebookFullDTO> getAllAsc(){
        Pageable pagination = PageRequest.of(0, PHONEBOOK_COUNT_PAGE, Sort.by("createdAt").ascending());
        return phonebookRepository.getPhoneBookAsc(pagination).stream().map(this::convertDTO).collect(Collectors.toList());
    }

    public PhonebookFullDTO getDetail(Integer phoneBookId){
        Optional<PhoneBookModel> getDetail = phonebookRepository.findById(phoneBookId);
        if (getDetail.isPresent()){
            return convertDTO(getDetail.get());
        } else {
            return null;
        }
    }

    public Slice<PhonebookFullDTO> searchPhoneBookByName(String name, int page) {
        Pageable pagination = PageRequest.of(page, PHONEBOOK_COUNT_PAGE, Sort.by("createdBy").ascending());
        return phonebookRepository.getPhoneBookByName(name, pagination).map(this::convertDTO);
    }

    public Slice<PhonebookFullDTO> searchPhoneBookByPhoneNumber(String phoneNumber, int page) {
        Pageable pagination = PageRequest.of(page, PHONEBOOK_COUNT_PAGE, Sort.by("createdBy").ascending());
        return phonebookRepository.getPhoneBookByPhoneNumber(phoneNumber, pagination).map(this::convertDTO);
    }

    public boolean deletePhonebook(int id) {
        try {
            Optional<PhoneBookModel> phoneBookModel = phonebookRepository.findById(id);
            phoneBookModel.ifPresent(phoneBookModels -> {
                phonebookRepository.delete(phoneBookModels);
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private PhonebookFullDTO convertDTO(PhoneBookModel item) {
        PhonebookFullDTO phonebookFullDTO = new PhonebookFullDTO();
        GroupModel getGroupName = groupRepository.getById(item.getGroupId());
        phonebookFullDTO.setName(item.getName());
        phonebookFullDTO.setPhoneNumber(item.getPhoneNumber());
        phonebookFullDTO.setGroupName(getGroupName.getNameGroup());
        return phonebookFullDTO;
    }
}
