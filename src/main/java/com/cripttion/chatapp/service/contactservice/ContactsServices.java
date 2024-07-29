package com.cripttion.chatapp.service.contactservice;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.model.entity.Contact;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.repository.ContactRepo;
import com.cripttion.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ContactsServices {

    @Autowired
    private ContactRepo contactRepo;

    @Autowired
    private UserRepo userRepo;

    private User getUserByMobileNumber(String mobileNumber) {
        return userRepo.findByphoneNumber(mobileNumber)
                .orElse(null); // Handle case where user is not found
    }

    @Transactional
    public ApiResonseDto<Contact> creatingContactListOfUser(UUID userID, List<String> contactList) {
        User owner = userRepo.findAllByuserId(userID);

        Set<User> existingContacts = contactRepo.findExistingContacts(owner, contactList);

        List<User> newContacts = contactList.stream()
                .map(this::getUserByMobileNumber)
                .filter(contactUser -> contactUser != null && !existingContacts.contains(contactUser)) // Handle null values
                .collect(Collectors.toList());

        List<Contact> contactsToAdd = newContacts.stream()
                .map(contactUser -> {
                    Contact contact = new Contact();
                    contact.setOwner(owner);
                    contact.setContact(contactUser);
                    contact.setAlias(contactUser.getUsername());
                    return contact;
                })
                .collect(Collectors.toList());
        contactRepo.saveAll(contactsToAdd);

        ApiResonseDto <Contact> apiResponse = new ApiResonseDto<>();
        apiResponse.setStatus("201");
        apiResponse.setMessage("Contacts added successfully");
        apiResponse.setData(contactsToAdd); // Return the added contacts

        return apiResponse;
    }
}
