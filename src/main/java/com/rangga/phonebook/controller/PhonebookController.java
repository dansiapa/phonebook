package com.rangga.phonebook.controller;

import com.rangga.phonebook.dto.GroupDTO;
import com.rangga.phonebook.dto.PhonebookFullDTO;
import com.rangga.phonebook.model.GroupModel;
import com.rangga.phonebook.model.PhoneBookModel;
import com.rangga.phonebook.response.DataResponse;
import com.rangga.phonebook.response.HandlerResponse;
import com.rangga.phonebook.service.GroupService;
import com.rangga.phonebook.service.PhonebookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Slice;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/phonebook/", produces = {"application/json"})
public class PhonebookController {

    @Autowired
    private PhonebookService phonebookService;

    @Autowired
    private GroupService groupService;

    @PostMapping("create")
    public void createPhonebook(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody PhoneBookModel model) {


        PhoneBookModel phoneBookModel = phonebookService.createPhonebook(model);

        if (Objects.nonNull(phoneBookModel)) {
            HandlerResponse.responseSuccessOK(response, "Success Create PhoneBook");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Failed to add PhoneBook");
        }
    }

    @PostMapping("update")
    public void updatePhonebook(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody PhoneBookModel model) {


        PhoneBookModel phoneBookModel = phonebookService.updatePhonebook(model);

        if (Objects.nonNull(phoneBookModel)) {
            HandlerResponse.responseSuccessOK(response, "Success Update PhoneBook");
        } else {
            HandlerResponse.responseBadRequest(response, "02", "Failed to update PhoneBook");
        }
    }

    @GetMapping("all")
    public void getAll(HttpServletRequest request, HttpServletResponse response) {
        List<PhonebookFullDTO> phoneBookModel = phonebookService.getAll();

        if (Objects.nonNull(phoneBookModel)) {
            DataResponse<List<PhonebookFullDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(phoneBookModel);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "03", "Phonebook is empty");
        }
    }

    @GetMapping("all-asc")
    public void getAllAsc(HttpServletRequest request, HttpServletResponse response) {
        List<PhonebookFullDTO> phoneBookModel = phonebookService.getAllAsc();

        if (Objects.nonNull(phoneBookModel)) {
            DataResponse<List<PhonebookFullDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(phoneBookModel);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "03", "Phonebook is empty");
        }
    }

    @GetMapping("detail")
    public void getDetail(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("id")Integer phoneBookId) {
        PhonebookFullDTO phoneBookModel = phonebookService.getDetail(phoneBookId);

        if (Objects.nonNull(phoneBookModel)) {
            DataResponse<PhonebookFullDTO> dataResponse = new DataResponse<>();
            dataResponse.setData(phoneBookModel);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "04", "Phonebook not found");
        }
    }

    @GetMapping("search")
    public void searchPhoneBookByName(HttpServletRequest request, HttpServletResponse response,
                          @RequestParam("name")String name) {
        Slice<PhonebookFullDTO> phoneBookModel = phonebookService.searchPhoneBookByName(name,0);

        if (Objects.nonNull(phoneBookModel)) {
            DataResponse<Slice<PhonebookFullDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(phoneBookModel);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "05", "Name on Phonebook not found");
        }
    }

    @GetMapping("search-number")
    public void searchPhoneBookByNumber(HttpServletRequest request, HttpServletResponse response,
                                      @RequestParam("phoneNumber")String phoneNumber) {
        Slice<PhonebookFullDTO> phoneBookModel = phonebookService.searchPhoneBookByPhoneNumber(phoneNumber,0);

        if (Objects.nonNull(phoneBookModel)) {
            DataResponse<Slice<PhonebookFullDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(phoneBookModel);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "05", "Name on Phonebook not found");
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deletePhonebook(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {

        boolean phonebookModel = phonebookService.deletePhonebook(id);
        if (phonebookModel) {
            HandlerResponse.responseSuccessOK(response, "Success delete phonebook");
        } else {
            HandlerResponse.responseBadRequest(response, "04", "Phonebook not found");
        }
    }


    @PostMapping("group/create")
    public void createGroup(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody GroupModel model) {


        GroupModel groupModel = groupService.createGroup(model);

        if (Objects.nonNull(groupModel)) {
            HandlerResponse.responseSuccessOK(response, "Success Create Group");
        } else {
            HandlerResponse.responseBadRequest(response, "01", "Failed to add Group");
        }
    }

    @PostMapping("group/update")
    public void updateGroup(HttpServletRequest request, HttpServletResponse response,
                                @RequestBody GroupModel model) {


        GroupModel groupModel = groupService.updateGroup(model);

        if (Objects.nonNull(groupModel)) {
            HandlerResponse.responseSuccessOK(response, "Success Update Group");
        } else {
            HandlerResponse.responseBadRequest(response, "02", "Failed to update Group");
        }
    }

    @GetMapping("group/all")
    public void getAllGroup(HttpServletRequest request, HttpServletResponse response) {
        List<GroupDTO> groupDTOS = groupService.getAllGroup();

        if (Objects.nonNull(groupDTOS)) {
            DataResponse<List<GroupDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(groupDTOS);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "03", "Group is empty");
        }
    }

    @GetMapping("group/search")
    public void searchGroup(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam("name")String name) {
        Slice<GroupDTO> groupDTOS = groupService.searchGroup(name,0);

        if (Objects.nonNull(groupDTOS)) {
            DataResponse<Slice<GroupDTO>> dataResponse = new DataResponse<>();
            dataResponse.setData(groupDTOS);
            HandlerResponse.responseSuccessWithData(response, dataResponse);
        } else {
            HandlerResponse.responseBadRequest(response, "05", "Name on Group not found");
        }
    }

    @DeleteMapping("group/delete/{id}")
    public void deleteGroup(HttpServletRequest request, HttpServletResponse response, @PathVariable int id) {

        boolean groupModel = groupService.deleteGroup(id);
        if (groupModel) {
            HandlerResponse.responseSuccessOK(response, "Success delete Group");
        } else {
            HandlerResponse.responseBadRequest(response, "04", "Group not found");
        }
    }
}
