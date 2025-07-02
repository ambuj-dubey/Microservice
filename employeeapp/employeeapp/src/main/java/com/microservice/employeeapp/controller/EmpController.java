package com.microservice.employeeapp.controller;

import com.microservice.employeeapp.DTO.EmployeeDto;
import com.microservice.employeeapp.EmpoyeeException.EmployeeNotFoundException;
import com.microservice.employeeapp.entity.EmployeeEntity;
import com.microservice.employeeapp.service.EmpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employeeDetils")
public class EmpController {

    @Autowired
    private EmpService empService;

    /*@GetMapping("/{id}")
    public EmployeeEntity getEmployeeById(@PathVariable Long id){
        return empService.getEmployeeById(id);
    }*/
    @GetMapping("/{id}")
    public ResponseEntity<?> getEmployeeById(@PathVariable Long id) {
        try {
            EmployeeEntity employee = empService.getEmployeeById(id);
            return ResponseEntity.ok(employee);
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    /*@PostMapping("/CreateUser")
    public void addEmployee(@RequestBody EmployeeDto employeeDto){
        empService.createEmployee(employeeDto);
    }*/
    @PostMapping("/CreateUser")
    public ResponseEntity<String> addEmployee(@RequestBody EmployeeDto employeeDto) {
        empService.createEmployee(employeeDto);
        return ResponseEntity.status(201).body("Employee created successfully");
    }

    /*@PutMapping("/update/{id}")
    public void  updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto){
         empService.updateEmployeeById(employeeDto, id);
    }*/
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateEmployeeById(@PathVariable Long id, @RequestBody EmployeeDto employeeDto) {
        try {
            empService.updateEmployeeById(employeeDto, id);
            return ResponseEntity.ok("Employee updated successfully");
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    /*@DeleteMapping("/delete/{id}")
    public void deleteEmployeeById(@PathVariable Long id){
        empService.deleteEmployee(id);
    }*/
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEmployeeById(@PathVariable Long id) {
        try {
            empService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (EmployeeNotFoundException ex) {
            return ResponseEntity.status(404).body(ex.getMessage());
        }
    }

    /*@GetMapping("/fetchAll")
    public List<EmployeeEntity> getAll(){
        return empService.getAll();
    }*/
    @GetMapping("/fetchAll")
    public ResponseEntity<List<EmployeeEntity>> getAll() {
        return ResponseEntity.ok(empService.getAll());
    }
}
