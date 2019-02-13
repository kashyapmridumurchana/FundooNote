package com.bridgelabz.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Validator;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.spring.model.UserDetails;
import com.bridgelabz.spring.service.UserService;

@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@Autowired
	@Qualifier("userValidator")
	private Validator validator;

	@InitBinder
	private void initBinder(WebDataBinder binder) {
		binder.setValidator(validator);
	}

	@RequestMapping(value = "/registeruser", method = RequestMethod.POST)
	public ResponseEntity<?> registerUser(@Validated @RequestBody UserDetails user, BindingResult bindingResult,
			HttpServletRequest request) {
		if (bindingResult.hasErrors()) {
			return new ResponseEntity<String>("Please enter valid details", HttpStatus.CONFLICT);
		} else {
			try {
				if (userService.register(user, request))
					return new ResponseEntity<Void>(HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<Void>(HttpStatus.CONFLICT);
			}
			return new ResponseEntity<Void>(HttpStatus.CONFLICT);
		}
	}

	@RequestMapping(value = "/loginuser", method = RequestMethod.POST)
	public ResponseEntity<?> loginUser(@RequestBody UserDetails user, HttpServletRequest request,
			HttpServletResponse response) {

		UserDetails newUser = userService.loginUser(user, request, response);
		if (newUser != null) {
			return new ResponseEntity<UserDetails>(newUser, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Incorrect emailId or password", HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/updateuser", method = RequestMethod.PUT)
	public ResponseEntity<?> updateUser(@RequestHeader("token") String token, @RequestBody UserDetails user,
			HttpServletRequest request) {

		UserDetails newUser = userService.updateUser(token, user, request);
		if (newUser != null) {
			return new ResponseEntity<UserDetails>(newUser, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/deleteuser", method = RequestMethod.DELETE)
	public ResponseEntity<?> deleteUser(@RequestHeader("token") String token, HttpServletRequest request) {

		UserDetails user = userService.deleteUser(token, request);
		if (user != null) {
			return new ResponseEntity<UserDetails>(user, HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/activationstatus/{token:.+}", method = RequestMethod.GET)
	public ResponseEntity<?> activateUser(@PathVariable("token") String token, HttpServletRequest request) {

		UserDetails user = userService.activateUser(token, request);
		if (user != null) {
			return new ResponseEntity<String>("Activated", HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/forgotpassword", method = RequestMethod.POST)
	public ResponseEntity<?> forgotpassword(@RequestParam("emailId") String emailId, HttpServletRequest request) {
		if (userService.forgotPassword(emailId, request)) {
			return new ResponseEntity<String>("Link sent to your emailId reset your password",
					HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("Email incorrect. Please enter valid email address present in database",
					HttpStatus.NOT_FOUND);
		}
	}

	@RequestMapping(value = "/resetpassword/{token:.+}", method = RequestMethod.PUT)
	public ResponseEntity<?> resetpassword(@RequestBody UserDetails user, @PathVariable("token") String token,
			HttpServletRequest request) {
		UserDetails newUser = userService.resetPassword(user, token, request);
		if (newUser != null) {
			return new ResponseEntity<String>("Password reset", HttpStatus.FOUND);
		} else {
			return new ResponseEntity<String>("password couldn't be reset", HttpStatus.NOT_FOUND);
		}
	}

}