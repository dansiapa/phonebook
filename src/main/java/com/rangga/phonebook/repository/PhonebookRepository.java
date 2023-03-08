package com.rangga.phonebook.repository;

import com.rangga.phonebook.model.PhoneBookModel;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PhonebookRepository extends JpaRepository<PhoneBookModel,Integer> {

    @Query("select n from PhoneBookModel n "
            + "where n.name like %:name% "
            + "order by n.createdAt asc ")
    Slice<PhoneBookModel> getPhoneBookByName(String name, Pageable pagination);

    @Query("select n from PhoneBookModel n "
            + "where n.phoneNumber like %:phoneNumber% "
            + "order by n.createdAt asc ")
    Slice<PhoneBookModel> getPhoneBookByPhoneNumber(String phoneNumber, Pageable pagination);

    @Query("select n from PhoneBookModel n "
            + "order by n.createdAt asc ")
    Slice<PhoneBookModel> getPhoneBookAsc(Pageable pagination);
}
