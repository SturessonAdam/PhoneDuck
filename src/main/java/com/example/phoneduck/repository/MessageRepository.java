package com.example.phoneduck.repository;

import com.example.phoneduck.model.MessageModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<MessageModel, Long> {
    List<MessageModel> findByChannel_Id(Long channelId);
}
