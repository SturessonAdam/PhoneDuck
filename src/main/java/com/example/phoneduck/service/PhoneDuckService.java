package com.example.phoneduck.service;

import com.example.phoneduck.model.ChannelModel;
import com.example.phoneduck.model.MessageModel;
import com.example.phoneduck.repository.ChannelAnnouncement;
import com.example.phoneduck.repository.MessageRepository;
import com.example.phoneduck.repository.PhoneDuckRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhoneDuckService {

    @Autowired
    private PhoneDuckRepository phoneDuckRepository;

    @Autowired
    private MessageRepository messageRepository;

    public List<ChannelAnnouncement> getAllChatRooms(){
        return phoneDuckRepository.findAllBy();
    }

    public void createChannel(ChannelModel channelModel){
        phoneDuckRepository.save(channelModel);
    }


    public void deleteChannel(Long id){
        ChannelModel channelModel = phoneDuckRepository.findChannelModelById(id);
        phoneDuckRepository.delete(channelModel);
    }


    public ChannelModel updateChannel(Long id, ChannelModel newChannel){
        ChannelModel channelModel = phoneDuckRepository.findChannelModelById(id);

        if (newChannel.getTitle() != null) channelModel.setTitle(newChannel.getTitle());

        return phoneDuckRepository.save(channelModel);
    }


    public MessageModel createMessage(Long channelId, MessageModel messageModel) {
        ChannelModel channel = phoneDuckRepository.findChannelModelById(channelId);
        messageModel.setChannel(channel);
        return messageRepository.save(messageModel);
    }

    public List<MessageModel> getMessagesFromChannel(Long channelId) {
        return messageRepository.findByChannel_Id(channelId);
    }

    public MessageModel updateMessage(Long messageId, MessageModel messageModel){
        MessageModel existingMessage = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("id " + messageId + " not found"));
         existingMessage.setContent(messageModel.getContent());

         return messageRepository.save(existingMessage);
    }

    public void deleteMessage(Long messageId){
        MessageModel message = messageRepository.findById(messageId)
                .orElseThrow(() -> new RuntimeException("id " + messageId + " not found"));
        messageRepository.delete(message);
    }
}
