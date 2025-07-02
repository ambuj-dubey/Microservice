package com.microservice.employeeapp.service;


import com.microservice.employeeapp.DTO.EmployeeDto;
import com.microservice.employeeapp.EmpoyeeException.EmployeeNotFoundException;
import com.microservice.employeeapp.entity.EmployeeEntity;
import com.microservice.employeeapp.repository.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmpService {
    @Autowired //this is used to implement Dependency Injection
    EmployeeRepo employeeRepo;

    public EmployeeEntity getEmployeeById(Long id) {
        return employeeRepo.findById(id).
                orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " +id));
    }

    public void createEmployee(EmployeeDto employeeDto) {
        EmployeeEntity entity = new EmployeeEntity();
        /*System.out.println("name "+employeeDto.getName() );
        System.out.println("Mobile "+employeeDto.getMobileNo() );
        System.out.println("Address "+employeeDto.getAddress() );*/
        entity.setName(employeeDto.getName());
        entity.setMobileNo(employeeDto.getMobileNo());
        entity.setAddress(employeeDto.getAddress());
        //CompanyEntity company = companyRepo.findById(employeeDto.getCompanyId())
         //       .orElseThrow(() -> new RuntimeException( "Company not found"));

        //entity.setCompany(company);
        //employeeRepo.save(entity);        //It will wait to complete the transactions
        employeeRepo.saveAndFlush(entity); //Immediatly save data to DB

    }



    public void updateEmployeeById(EmployeeDto employeeDto, Long id) {
        EmployeeEntity existing = employeeRepo.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("Employee not found with id " + id));
        existing.setName(employeeDto.getName());
        existing.setMobileNo(employeeDto.getMobileNo());
        existing.setAddress(employeeDto.getAddress());
        employeeRepo.save(existing);
    }


    public void deleteEmployee(Long id) {
        if (!employeeRepo.existsById(id)) {
            throw new EmployeeNotFoundException("Employee not found with id " + id);
        }
        employeeRepo.deleteById(id);
    }

    public List<EmployeeEntity> getAll() {
        return employeeRepo.findAll();
    }
}
