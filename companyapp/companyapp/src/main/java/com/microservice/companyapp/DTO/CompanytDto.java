package com.microservice.companyapp.DTO;

import java.util.List;

public class CompanytDto {

    private Long companyId;              // match entity type
    private String companyName;
    private String role;                 // company role
    private List<BranchDto> branch;      // list of branches

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public List<BranchDto> getBranch() {
        return branch;
    }

    public void setBranch(List<BranchDto> branch) {
        this.branch = branch;
    }
}

