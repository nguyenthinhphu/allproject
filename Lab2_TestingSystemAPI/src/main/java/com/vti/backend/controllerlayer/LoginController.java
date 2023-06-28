package com.vti.backend.controllerlayer;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/api/v1/login")
public class LoginController {

	private static final Logger log = LoggerFactory.getLogger(LoginController.class);

	@GetMapping()
	public ResponseEntity<?> login(Principal princ) {

		String loginName = princ.getName();

		log.info(loginName);

		return new ResponseEntity<>("Login Successfully!", HttpStatus.OK);
	}
}
