package com.cripttion.chatapp.repository;

import com.cripttion.chatapp.Dto.Contacts.ContactListDTO;
import com.cripttion.chatapp.model.entity.Contact;
import com.cripttion.chatapp.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;
import java.util.UUID;
@Repository
public interface ContactRepo extends JpaRepository<Contact, UUID> {

    @Query("SELECT c.contact FROM Contact c WHERE c.owner = :owner AND c.contact.phoneNumber IN :mobileNumbers")
    Set<User> findExistingContacts(@Param("owner") User owner, @Param("mobileNumbers") List<String> mobileNumbers);
    @Query("SELECT new com.cripttion.chatapp.Dto.Contacts.ContactListDTO(c.alias, cu.userId, cu.username, cu.email, cu.phoneNumber,cu.status,cu.profilePicture) " +
            "FROM Contact c JOIN c.contact cu WHERE c.owner.userId = :ownerId")
    List<ContactListDTO> findContactsByOwnerId(@Param("ownerId") UUID ownerId);
}
