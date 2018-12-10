package com.postal.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.postal.bean.Etcar;

public interface CarRepository extends JpaRepository<Etcar, String> {

    @Query("from Etcar p where p.carNo =:carNo")
    public Etcar findByCarNo(@Param(value = "carNo") String carNo);
    
    @SuppressWarnings("unchecked")
	public Etcar save(Etcar car);

}