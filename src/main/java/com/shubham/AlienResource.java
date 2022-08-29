package com.shubham;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlienResource {

	@Autowired
	AlienRepository repo;
	
	@GetMapping("aliens")
	public ResponseEntity<List<Alien>> getAliens() {
		
		List<Alien> aliens = (List<Alien>) repo.findAll();
		return new ResponseEntity<>(aliens, HttpStatus.OK);
	}
	
	@GetMapping("alien/{id}")
	public ResponseEntity<Alien> getAlien(@PathVariable int id) {
		
		Optional<Alien> a = repo.findById(id);
		if(a.isPresent()) {
			return new ResponseEntity<>(a.get(), HttpStatus.OK);
		} else {
			return null;
		}
	}
	
	@DeleteMapping("alien/{id}")
	public void removeAlien(@PathVariable int id) {
		repo.deleteById(id);
	}
	
	@PostMapping(path="alien", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alien> createAlien(@RequestBody Alien a) {
		System.out.println(a);
		if(a == null) {
			return null;
		} else {
			return new ResponseEntity<>(repo.save(a), HttpStatus.CREATED);
		}
	}

	@PutMapping(path="alien", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Alien> updateAlien(@RequestBody Alien a) {
		if(a == null) {
			return null;
		} else {
			return new ResponseEntity<>(repo.save(a), HttpStatus.CREATED);
		}
	}
}
