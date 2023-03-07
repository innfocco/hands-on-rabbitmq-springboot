package jc.controller;

import jc.service.MessageSenderServiceWebApp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jc.entity.Message;
import jc.service.MessageSenderServiceMobile;
import lombok.extern.log4j.Log4j2;

@Log4j2
@RestController
@RequestMapping(path="/messages", produces=MediaType.APPLICATION_JSON_VALUE)
public class MessageController {
	
	@Autowired
	MessageSenderServiceMobile serviceMobile;

	@Autowired
	MessageSenderServiceWebApp serviceWebApp;

	@PostMapping(consumes=MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Message> create(@RequestBody Message msg) {
		if(msg.getDevice().equals("mobile")){
			serviceMobile.publish(msg);
		} else if(msg.getDevice().equals("webapp")){
			serviceWebApp.publish(msg);
		} else {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(msg);
	}
	
	@GetMapping
	public ResponseEntity<String> read() {
		log.error("GET not allowed");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("");
	}
	
	@PutMapping
	public ResponseEntity<String> update() {
		log.error("PUT not allowed");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("");
	}
	
	@DeleteMapping
	public ResponseEntity<String> delete() {
		log.error("DELETE not allowed");
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body("");
	}
}
