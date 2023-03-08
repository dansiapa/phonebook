package com.rangga.phonebook.repository;

import com.rangga.phonebook.model.GroupModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface GroupRepository extends JpaRepository<GroupModel,Integer> {

    @Query("select n from GroupModel n "
            + "where n.nameGroup like %:name% "
            + "order by n.createdAt asc ")
    Slice<GroupModel> searchGroup(String name, Pageable pagination);
}
