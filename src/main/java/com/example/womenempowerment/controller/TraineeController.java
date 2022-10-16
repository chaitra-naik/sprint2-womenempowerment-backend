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
import org.springframework.web.bind.annotation.RestController;

import com.example.womenempowerment.entity.Trainee;
import com.example.womenempowerment.exception.TraineeAlreadyExistsException;
import com.example.womenempowerment.exception.TraineeDoesNotExistsException;
import com.example.womenempowerment.service.TraineeService;

@RestController
@CrossOrigin(origins="http://localhost:4200")
public class TraineeController {
	@Autowired
	private TraineeService traineeServ;
	
	@PostMapping("/trainee")
	public ResponseEntity<Trainee> addTrainee(@RequestBody Trainee course) throws TraineeAlreadyExistsException{
		Trainee ts = traineeServ.addTrainee(course);
		return new ResponseEntity<Trainee>(ts,HttpStatus.ACCEPTED);
	}
	
	@PutMapping("/trainee/{traineeId}")
	public ResponseEntity<Trainee> updateTrainee(@PathVariable int traineeId,@RequestBody Trainee trainee){
		Trainee updatetrainee = traineeServ.updateTrainee(traineeId,trainee);
		return new ResponseEntity<Trainee>(updatetrainee,HttpStatus.OK);
	}
	
	@GetMapping("/viewtraineebyid/{traineeId}")
	public ResponseEntity<Optional<Trainee>> viewTrainee(@PathVariable int traineeId) throws TraineeDoesNotExistsException{
		Optional<Trainee> viewById = traineeServ.viewTrainee(traineeId);
		if(!viewById.isEmpty()) {
			return new ResponseEntity<Optional<Trainee>>(viewById,HttpStatus.OK);
		}
		else {
			throw new TraineeDoesNotExistsException();
		}
	}
	
	@GetMapping("/trainees")
	public ResponseEntity<List<Trainee>> viewAllTrainee(){
		List<Trainee> viewAll = traineeServ.viewAllTrainee();
		return new ResponseEntity<List<Trainee>>(viewAll,HttpStatus.OK);
	}
	
	@DeleteMapping("/trainee/{traineeId}")
	public ResponseEntity<Map<String,Boolean>> deleteTrainee(@PathVariable int traineeId) throws TraineeDoesNotExistsException{
		Optional<Trainee> viewById = traineeServ.viewTrainee(traineeId);
		if(!viewById.isEmpty()) {
			traineeServ.deleteTrainee(traineeId);
			//return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
			Map<String, Boolean> response = new HashMap<>();
			//return new ResponseEntity<String>("Deleted successfully",HttpStatus.OK);
			return ResponseEntity.ok(response);
		}
		else{
			throw new TraineeDoesNotExistsException();
		}
	}
	
	@GetMapping("/viewtraineebyloc/{location}")
	public ResponseEntity<List<Trainee>> viewAllTraineesByLocation(@PathVariable String location) throws TraineeDoesNotExistsException{
		if(traineeServ.viewAllTraineesByLocation(location) != null) {
			List<Trainee> viewByLocation = traineeServ.viewAllTraineesByLocation(location);
			return new ResponseEntity<List<Trainee>>(viewByLocation,HttpStatus.OK);
		}
		else {
			throw new TraineeDoesNotExistsException();
		}
	}
		@GetMapping("/viewtraineebyaadhar/{aadharNo}")
		public ResponseEntity<Trainee> viewTraineeByAadhar(@PathVariable long aadharNo) throws TraineeDoesNotExistsException{
			if(traineeServ.viewTraineeByAadhar(aadharNo) != null) {
				Trainee viewByAadharNo = traineeServ.viewTraineeByAadhar(aadharNo);
				return new ResponseEntity<Trainee>(viewByAadharNo,HttpStatus.OK);
			}
			else {
				throw new TraineeDoesNotExistsException();
			}
	}
}
