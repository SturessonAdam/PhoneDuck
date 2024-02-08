package com.example.phoneduck.repository;

import com.example.phoneduck.model.ChannelModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneDuckRepository extends JpaRepository<ChannelModel, Long> {

   ChannelModel findChannelModelById(Long id);

   List<ChannelAnnouncement> findAllBy();

}
