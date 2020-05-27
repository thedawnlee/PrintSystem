package com.dawn;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.dawn.dao")
public class PrintApplication {

	public static void main(String[] args) {
		SpringApplication.run(PrintApplication.class, args);
	}

}
