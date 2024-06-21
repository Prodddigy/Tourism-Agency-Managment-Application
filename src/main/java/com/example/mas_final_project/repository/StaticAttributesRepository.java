package com.example.mas_final_project.repository;


import com.example.mas_final_project.model.StaticAttributes;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface StaticAttributesRepository extends CrudRepository<StaticAttributes,Long> {

    @Query("select minPrice from StaticAttributes where id = :id")
    public int getMinPriceById(@Param("id")Long id);

    @Query("select premiumDiscount from StaticAttributes where id = :id")
    public int getPremiumDiscountById(@Param("id")Long id);
}
