package com.sp.oauth2Demo;

import com.sp.oauth2Demo.entity.User;
import com.sp.oauth2Demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.GlobalAuthenticationConfigurerAdapter;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@SpringBootApplication
@EnableResourceServer
@EnableWebSecurity
public class Oauth2DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(Oauth2DemoApplication.class, args);
    }

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;


    @Override
    public void run(String... args) throws Exception {
        User user = new User(12L, "abc", this.passwordEncoder.encode("2000"), "abc@gmail.com", "ROLE_NORMAL");
        User user1 = new User(13L, "def", this.passwordEncoder.encode("2002"), "def@gmail.com", "ROLE_NORMAL");
        this.userRepository.save(user);
        this.userRepository.save(user1);
    }
}
