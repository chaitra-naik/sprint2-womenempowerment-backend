package com.example.womenempowerment.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.womenempowerment.entity.NGO;
import com.example.womenempowerment.exception.NGOAlreadyExistsException;
import com.example.womenempowerment.exception.NGODoesNotExistsException;
import com.example.womenempowerment.service.NGOService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class NGOController {
	
	@Autowired
	private NGOService ngoServ;
	
	@PostMapping("/ngo")
	public ResponseEntity<NGO> addNGO(@RequestBody NGO ngo) throws NGOAlreadyExistsException{
		NGO addngo = ngoServ.addNGO(ngo);
		return new ResponseEntity<NGO>(addngo,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/ngo/{ngoid}")
	public ResponseEntity<NGO> updateNGO(@PathVariable int ngoid, @RequestBody NGO ngo){
		NGO updatengo = ngoServ.updateNGO(ngoid,ngo);
		return new ResponseEntity<NGO>(updatengo,HttpStatus.ACCEPTED);
	}
	
	@GetMapping("/ngo/{ngoid}")
	public ResponseEntity<Optional<NGO>> viewNGO(@PathVariable int ngoid) throws NGODoesNotExistsException{
		Optional<NGO> viewById = ngoServ.viewNGO(ngoid);
		if(!viewById.isEmpty()) {
			return new ResponseEntity<Optional<NGO>>(viewById,HttpStatus.OK);
		}
		else {
			throw new NGODoesNotExistsException();
		}
	}
	
	@GetMapping("/ngos")
	public ResponseEntity<List<NGO>> viewAllNGO(){
		List<NGO> viewallngo = ngoServ.viewAllNGO();
		return new ResponseEntity<List<NGO>>(viewallngo,HttpStatus.OK);
	}
	
	@DeleteMapping("/deletengo/{ngoId}")
	public ResponseEntity<Map<String,Boolean>> deleteNGO(@PathVariable int ngoId) throws NGODoesNotExistsException{
		Optional<NGO> view = ngoServ.viewNGO(ngoId);
		if(!view.isEmpty()) {
			ngoServ.deleteNGO(ngoId);
			Map<String, Boolean> response = new HashMap<>();
			//return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
			return ResponseEntity.ok(response);
		}
		else {
			throw new NGODoesNotExistsException();
		}
	}
	
	@GetMapping("/viewngobymotive/{motive}")
	public ResponseEntity<List<NGO>> viewNGOByMotive(@PathVariable String motive) throws NGODoesNotExistsException{
		List<NGO> viewbymotive = ngoServ.viewNGOByMotive(motive);
		if(!viewbymotive.isEmpty()) {
			return new ResponseEntity<List<NGO>>(viewbymotive,HttpStatus.OK);
		}
		else {
			throw new NGODoesNotExistsException();
		}
	}
	
	@GetMapping("/viewngobyloc/{location}")
	public ResponseEntity<List<NGO>> viewNGOByLocation(@PathVariable String location) throws NGODoesNotExistsException{
		List<NGO> viewbylocation = ngoServ.viewNGOByLocation(location);
		if(!viewbylocation.isEmpty()) {
			return new ResponseEntity<List<NGO>>(viewbylocation,HttpStatus.OK);
		}
		else {
			throw new NGODoesNotExistsException();
		}
	}
}
