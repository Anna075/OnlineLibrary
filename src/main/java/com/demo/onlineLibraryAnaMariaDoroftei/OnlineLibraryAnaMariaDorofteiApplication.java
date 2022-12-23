package com.demo.onlineLibraryAnaMariaDoroftei;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class OnlineLibraryAnaMariaDorofteiApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLibraryAnaMariaDorofteiApplication.class, args);
	}

	public Docket apis(){
		return new Docket(DocumentationType.SWAGGER_2)
				.select()
				.apis(RequestHandlerSelectors.basePackage("com.demo.onlineLibraryAnaMariaDoroftei"))
				.build();
	}


}
