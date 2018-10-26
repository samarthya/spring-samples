package com.samarthya.external.controller;

import com.samarthya.external.model.Friend;
import com.samarthya.external.service.FriendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.xml.bind.ValidationException;

@RestController
public class FriendController {
    @Autowired
    FriendService friendService;

    @GetMapping("/friend")
    Iterable<Friend> read() {
        return friendService.findAll();
    }

    @PostMapping("/friend")
    Friend create(@RequestBody Friend friend) throws ValidationException {
        if(friend != null && friend.getFirstName() != null && friend.getLastName() != null && friend.getId() != 0)
            return friendService.save(friend);
        else
            throw new ValidationException(" Cannot create the friend");
    }

    @PutMapping("/friend")
    ResponseEntity<Friend> update(@RequestBody Friend friend) {
        if (friendService.findById(friend.getId()).isPresent())
            return new ResponseEntity(friendService.save(friend), HttpStatus.OK);
        else
            return new ResponseEntity<>(friend, HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/friend/{id}")
    void delete(@PathVariable Integer id) {
        friendService.deleteById(id);
    }
}
