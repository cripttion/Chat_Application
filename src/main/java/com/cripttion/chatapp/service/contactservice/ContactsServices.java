package com.cripttion.chatapp.service.contactservice;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.Dto.Contacts.ContactInputDto;
import com.cripttion.chatapp.Dto.Contacts.ContactListDTO;
import com.cripttion.chatapp.model.entity.Contact;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.repository.ContactRepo;
import com.cripttion.chatapp.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class ContactsServices {

    @Autowired
    private final ContactRepo contactRepo;

    public ContactsServices(ContactRepo contactRepo) {
        this.contactRepo = contactRepo;
    }

    @Autowired
    private UserRepo userRepo;

    private User getUserByMobileNumber(String mobileNumber) {
        return userRepo.findByphoneNumber(mobileNumber)
                .orElse(null); // Handle case where user is not found
    }

    @Transactional
    public ApiResonseDto<Contact> creatingContactListOfUser(UUID userID, List<ContactInputDto> contactList) {

        User owner = userRepo.findAllByuserId(userID);

        List<String> phoneNumbers = contactList.stream()
                .map(ContactInputDto::getPhoneNumber)
                .collect(Collectors.toList());
        System.out.println(phoneNumbers);
        Set<User> existingContacts = contactRepo.findExistingContacts(owner, phoneNumbers);

        List<Contact> contactsToAdd = contactList.stream()
                .map(contactInput -> {
                    User contactUser = getUserByMobileNumber(contactInput.getPhoneNumber());
                    if (contactUser != null && !existingContacts.contains(contactUser)) {
                        Contact contact = new Contact();
                        contact.setOwner(owner);
                        contact.setContact(contactUser);
                        contact.setAlias(contactInput.getAlias());
                        return contact;
                    }
                    return null;
                })
                .filter(Objects::nonNull)
                .collect(Collectors.toList());

        contactRepo.saveAll(contactsToAdd);

        ApiResonseDto<Contact> apiResponse = new ApiResonseDto<>();
        apiResponse.setStatus("201");
        apiResponse.setMessage("Contacts added successfully");
        apiResponse.setData(contactsToAdd);

        return apiResponse;
    }

    @Transactional
    public ApiResonseDto<ContactListDTO> getContactsListOfUser(UUID userId)
    {

          List<ContactListDTO> userData = contactRepo.findContactsByOwnerId(userId);
          ApiResonseDto<ContactListDTO> apiResonseDto = new ApiResonseDto<>();
          apiResonseDto.setStatus("200");
          apiResonseDto.setMessage("The user in Contacts");

          apiResonseDto.setData(userData);

          return apiResonseDto;
    }
}
