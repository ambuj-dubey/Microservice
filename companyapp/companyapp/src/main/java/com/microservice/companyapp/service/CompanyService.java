package com.microservice.companyapp.service;


import com.microservice.companyapp.DTO.CompanytDto;
import com.microservice.companyapp.companyException.CompanyNotFoundException;
import com.microservice.companyapp.entity.BranchEntity;
import com.microservice.companyapp.entity.CompanyEntity;
import com.microservice.companyapp.repository.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    private CompanyRepo companyRepo;

    public CompanyEntity getCompanyById(Long id) {
        return companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
    }

    public void createCompany(CompanytDto companytDto) {
        CompanyEntity companyEntity = new CompanyEntity();
        companyEntity.setCompanyName(companytDto.getCompanyName());
        companyEntity.setRole(companytDto.getRole());

        List<BranchEntity> branchList = new java.util.ArrayList<>();

        if (companytDto.getBranch() != null) {
            branchList = companytDto.getBranch().stream().map(branchDto -> {
                BranchEntity branch = new BranchEntity();
                branch.setBranchName(branchDto.getBranchName());
                branch.setCompanyEntity(companyEntity); // set owning side
                return branch;
            }).toList();
        }

        companyEntity.setBranches(branchList); // set inverse side

        companyRepo.save(companyEntity);
    }

    public List<BranchEntity> getAllBranchByCompanyId(Long id) {
        CompanyEntity company = companyRepo.findById(id)
                .orElseThrow(() -> new CompanyNotFoundException("Company not found with id: " + id));
        return company.getBranches();
    }
}

