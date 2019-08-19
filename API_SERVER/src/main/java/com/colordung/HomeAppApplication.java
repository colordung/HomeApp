package com.colordung;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import lombok.extern.slf4j.Slf4j;

@SpringBootApplication
@Slf4j
@MapperScan("com.colordung.dao")
public class HomeAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(HomeAppApplication.class, args);
	}

}
