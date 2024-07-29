package com.cripttion.chatapp.repository;

import com.cripttion.chatapp.model.entity.Contact;
import com.cripttion.chatapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Set;
import java.util.UUID;

public interface ContactRepo extends JpaRepository<Contact, UUID> {

    @Query("SELECT c.contact FROM Contact c WHERE c.owner = :owner AND c.contact.phoneNumber IN :mobileNumbers")
    Set<User> findExistingContacts(@Param("owner") User owner, @Param("mobileNumbers") List<String> mobileNumbers);
}
