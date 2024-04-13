package com.dailychecklist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;

@SpringBootApplication
@EnableEncryptableProperties
public class ChecklistOnlineApplication {

	public static void main(String[] args) {
		SpringApplication.run(ChecklistOnlineApplication.class, args);
	}

}
