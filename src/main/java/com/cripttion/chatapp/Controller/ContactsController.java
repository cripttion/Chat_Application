package com.cripttion.chatapp.Controller;

import com.cripttion.chatapp.Dto.ApiResonseDto;
import com.cripttion.chatapp.model.entity.Contact;
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
import java.util.UUID;


@RestController
@RequestMapping(path = "/v1/api")
public class ContactsController {

    @Data
    private static class ContactData {
        private List<String> contacts;
    }
    @Autowired
    private ContactsServices contactsServices;

    @PostMapping(path = "/contact/{id}")
    public ResponseEntity<ApiResonseDto<Contact>> createContactListOfUser(@PathVariable("id")UUID id, @RequestBody ContactData contactData)
    {
        return new ResponseEntity<>(
                contactsServices.creatingContactListOfUser(id,contactData.getContacts()),
                HttpStatus.OK
            );
    }

}
