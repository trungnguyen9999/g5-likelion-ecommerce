package com.likelion.ecommerce;

import java.util.HashMap;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.cloudinary.Cloudinary;



@SpringBootApplication
@EnableWebMvc
public class EcommerceApplication {
	
	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	public static void main(String[] args) {
		SpringApplication.run(EcommerceApplication.class, args);
	}

    @Bean
    public Cloudinary cloudinaryConfig() {
        Cloudinary cloudinary = null;
        Map config = new HashMap();
        config.put("cloud_name", "dxw7fyej4");
        config.put("api_key", "432441635429229");
        config.put("api_secret", "PMMqpbEib9AM5O9dlT7UAj44F8I");
        cloudinary = new Cloudinary(config);
        return cloudinary;
    }
  
}
