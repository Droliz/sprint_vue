package com.qtw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@MapperScan(value="com.qtw.mapper")   // 扫描 Mapper
public class QtwApplication {

	public static void main(String[] args) {
		SpringApplication.run(QtwApplication.class, args);
	}

}
