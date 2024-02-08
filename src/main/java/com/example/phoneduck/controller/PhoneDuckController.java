package com.example.phoneduck.controller;

import com.example.phoneduck.model.ChannelModel;
import com.example.phoneduck.model.MessageModel;
import com.example.phoneduck.repository.ChannelAnnouncement;
import com.example.phoneduck.service.PhoneDuckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/channels")
public class PhoneDuckController {

    @Autowired
    private PhoneDuckService phoneDuckService;

    @GetMapping
    public ResponseEntity<List<ChannelAnnouncement>> getAllChannels(){
        List<ChannelAnnouncement> channels = phoneDuckService.getAllChatRooms();

        if(channels.isEmpty()) {
            return ResponseEntity.status(204).body(channels);
        } else {
            return ResponseEntity.status(200).body(channels);
        }
    }

    @PostMapping
    public ResponseEntity<ChannelModel> addChannel(@RequestBody ChannelModel channelModel) {
        if (channelModel.getTitle() == null) {
            return ResponseEntity.status(400).body(channelModel);
        } else {
            phoneDuckService.createChannel(channelModel);
            return ResponseEntity.status(201).body(channelModel);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChannel(@PathVariable Long id){
        phoneDuckService.deleteChannel(id);
        return ResponseEntity.ok("Channel with id " + id + " removed");
    }

    @PatchMapping("/{id}")
    public ResponseEntity<ChannelModel> updateChannel(@PathVariable Long id, @RequestBody ChannelModel channelModel){
        if (channelModel.getTitle() == null) {
            return ResponseEntity.status(400).build();
        } else {
            ChannelModel updateChannel = phoneDuckService.updateChannel(id, channelModel);
            return ResponseEntity.status(200).body(updateChannel);
        }
    }

    @PutMapping("/{id}/messages")
    public ResponseEntity<MessageModel> createMessage(@PathVariable Long id, @RequestBody MessageModel messageModel) {
        MessageModel newMessage = phoneDuckService.createMessage(id, messageModel);
        return ResponseEntity.status(201).body(newMessage);
    }

    @GetMapping("/{id}/messages")
    public ResponseEntity<List<MessageModel>> getMessagesFromChannel(@PathVariable Long id) {
        List<MessageModel> messages = phoneDuckService.getMessagesFromChannel(id);

        if (messages.isEmpty()) {
            return ResponseEntity.status(204).body(messages);
        } else {
            return ResponseEntity.status(200).body(messages);
        }
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity<MessageModel> updateMessage(@PathVariable Long id, @RequestBody MessageModel messageModel){
        MessageModel updatedMessage = phoneDuckService.updateMessage(id, messageModel);
        return ResponseEntity.status(200).body(updatedMessage);
    }

    @DeleteMapping("/messages/{id}")
    public ResponseEntity<String> deleteMessage(@PathVariable Long id){
        phoneDuckService.deleteMessage(id);
        return ResponseEntity.ok("Message with id " + id + " deleted");
    }
}
