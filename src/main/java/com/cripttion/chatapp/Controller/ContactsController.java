package com.cripttion.chatapp.Controller;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.Dto.Contacts.ContactInputDto;
import com.cripttion.chatapp.Dto.Contacts.ContactListDTO;
import com.cripttion.chatapp.model.entity.Contact;
import com.cripttion.chatapp.model.entity.User;
import com.cripttion.chatapp.service.contactservice.ContactsServices;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.UUID;


@RestController
@RequestMapping(path = "/v1/api")
public class ContactsController {

    @Data
    private static class ContactData {
        private List<ContactInputDto> contacts;
    }
    @Autowired
    private final ContactsServices contactsServices;

    public ContactsController(ContactsServices contactsServices){
        this.contactsServices = contactsServices;
    }
    @PostMapping(path = "/contact/{id}")
    public ResponseEntity<ApiResonseDto<Contact>> createContactListOfUser(@PathVariable("id")UUID id, @RequestBody ContactData contactData)
    {
        return new ResponseEntity<>(
                contactsServices.creatingContactListOfUser(id,contactData.getContacts()),
                HttpStatus.OK
            );
    }
    @GetMapping(path="/contact/{id}")
    public ResponseEntity<ApiResonseDto<ContactListDTO>> getContactsOfAUser(@PathVariable("id") UUID id)
    {
         return new ResponseEntity<>(
                 contactsServices.getContactsListOfUser(id),
                 HttpStatus.OK

         );
    }

}
