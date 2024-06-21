package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.Insurance;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InsuranceRepository extends CrudRepository<Insurance,Long> {

    public List<Insurance> findByCompanyName(String name);
    public List<Insurance> findByCompanyNameAndPrice(String companyName,double price);
}
