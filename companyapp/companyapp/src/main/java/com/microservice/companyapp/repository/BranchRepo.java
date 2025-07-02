package com.microservice.companyapp.repository;

import com.microservice.companyapp.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchRepo extends JpaRepository<BranchEntity, Long> {
}
