package com.aula.oop.app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sun.tools.javac.util.List;

import jdk.internal.org.jline.utils.ShutdownHooks.Task;

@RestController
@RequestMapping("/api/task")
public class TaskController {
	private List<Task>taks = new ArrayList<>()
			
@GetMapping
Public List<Task> ListAll(){
		
		

}
			@GetMapping

	public ResponseEntity<List<Task>> getAllTasks() {
		return ResponseEntity.ok(tasks);

	}
	
	@PostMapping
	public ResponseEntity<Task> createTask(
			@RequestBody Task task) {	
		
	}
	
	
	
	
	
	
	@PostMapping
	public ResponseEntity<Task> criarTask(@ResquestBody Task taskRecebida
			tasks.add(taskRecebida)
			return ResponseEntity.status);
}
}


