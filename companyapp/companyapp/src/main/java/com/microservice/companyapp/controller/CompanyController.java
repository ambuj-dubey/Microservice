package com.microservice.companyapp.controller;

import com.microservice.companyapp.DTO.CompanytDto;
import com.microservice.companyapp.companyException.CompanyNotFoundException;
import com.microservice.companyapp.entity.BranchEntity;
import com.microservice.companyapp.entity.CompanyEntity;
import com.microservice.companyapp.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/companyDetails")
public class CompanyController {

    @Autowired
    private CompanyService companyService;
    @GetMapping("/{id}")
    public ResponseEntity<?> getCompanyById(@PathVariable Long id){
        try{
            CompanyEntity companyEntity = companyService.getCompanyById(id);
            return ResponseEntity.ok(companyEntity);
        }catch (CompanyNotFoundException exception){
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

    @PostMapping("/createCompany")
    public ResponseEntity<String> addCompany(@RequestBody CompanytDto companytDto){
        companyService.createCompany(companytDto);
        return ResponseEntity.status(201).body("Company Created");
    }
    @GetMapping("/branchByCompanyId/{id}")
    public ResponseEntity<?> getBranchByCompanyId(@PathVariable Long id){
        try {
            List<BranchEntity> branches = companyService.getAllBranchByCompanyId(id);
            return ResponseEntity.ok(branches);
        } catch (CompanyNotFoundException exception) {
            return ResponseEntity.status(404).body(exception.getMessage());
        }
    }

}
