package com.csi;

import com.csi.model.User;
import com.csi.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.text.SimpleDateFormat;

@SpringBootApplication
public class JwtSecutityApplication {

	@Autowired
	private UserRepo userRepo;

	public static void main(String[] args) {
		SpringApplication.run(JwtSecutityApplication.class, args);
	}

	@PostConstruct
	public void saveUserData() throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		userRepo.save(new User(101,"amol",45000,dateFormat.parse("13-12-1997"),"beamolpatil@gmail.com","amol123"));
	}
}
