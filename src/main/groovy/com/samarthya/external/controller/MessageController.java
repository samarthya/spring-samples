package com.samarthya.external.controller;

import com.samarthya.external.model.Message;
import org.springframework.web.bind.annotation.*;

@RestController
public class MessageController {

    @ResponseBody
    @GetMapping("/message")
    Message send() {
        return new Message("first Message");
    }

    @GetMapping("/smessage")
    Message sSend() {
        return new Message("Secured Message");
    }

    @PostMapping("/message")
    Message echo(@RequestBody Message message){
        return message;
    }
}
