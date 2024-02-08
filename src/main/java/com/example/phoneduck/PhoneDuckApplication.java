package com.example.phoneduck;

import com.example.phoneduck.model.ChannelModel;
import com.example.phoneduck.repository.PhoneDuckRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class PhoneDuckApplication {

	public static void main(String[] args) {
		SpringApplication.run(PhoneDuckApplication.class, args);
	}

	@Bean
	public CommandLineRunner run(PhoneDuckRepository phoneDuckRepository) {
		return args -> phoneDuckRepository.save(new ChannelModel(1L, "Chatroom 1", null));
	}
}
