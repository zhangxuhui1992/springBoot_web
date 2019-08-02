package com;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/**
 * #启动器存放的位置。启动器可以和 controller 位于同一个包下，
 * #或者位于 controller 的上一级包中，
 * #但是不能放到 controller 的平级以及子包下。
 * @MapperScan扫描MyBatis的Mapper接口
 * @author Administrator 张旭辉
 */
@SpringBootApplication
@MapperScan("com.ybjt.*.mapper")
public class App {
	public static void main(String[] args) {
		SpringApplication.run(App.class, args);
		System.out.println("SpringBoot 项目启动成功!");
	}
}
