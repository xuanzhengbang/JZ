package com.opopto.zdsetmealfood;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@SpringBootApplication
@EnableTransactionManagement
@MapperScan(basePackages = "com.opopto.zdsetmealfood.dao")
public class ZDSetmealFoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(ZDSetmealFoodApplication.class, args);
	}
}
